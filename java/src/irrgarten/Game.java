
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author Manuel Cantero
 */
public class Game {

    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private String log;

    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    
    private boolean earth = false;

    public Game(int nplayers) {
        
        players = new ArrayList<Player>();
        for (int i = 0; i < nplayers; ++i) {
            players.add(new Player((char) ('0' + i), Dice.randomIntelligence(), Dice.randomStrength()));
        }

        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = players.get(currentPlayerIndex);

        monsters = new ArrayList<Monster>();

        // Los dos primeros las dimensiones y los otros dos la casilla de salida
        labyrinth = new Labyrinth(5, 6, 0, 1); 
       // Laberinto predefinido con casilla de salida aleatoria
        
        //labyrinth = new Labyrinth(nRows,nCols,Dice.randomPos(nRows)
        //                   ,Dice.randomPos(nCols));
        
       
        // Configuramos el laberinto
        configureLabyrinth();
        
        // Posicionamos a los jugadores de manera aleatoria
        labyrinth.spreadPlayers(players);
        
        // Iniciamos log
        log = "Game started";
    }

    public boolean finished() { // Es como si fuese have a winner
        return labyrinth.haveAWinner();
    }

    public GameState getGameState(){
        String players_s = "";
        String monsters_s = "";
        
        for(int i = 0; i < players.size(); i++){
            players_s += players.get(i).toString() +"\n";
        }
        
        for(int i = 0; i < monsters.size(); i++){
            monsters_s += monsters.get(i).toString()+"\n";
        }
        
        GameState gamestate = new GameState(labyrinth.toString(),
                players_s,monsters_s,currentPlayerIndex, finished(),log, earth);
        
        return gamestate;
    }

    private void configureLabyrinth() {
        
        /*
        labyrinth.addBlock(Orientation.VERTICAL, 0, 0, 8); // Left column
        labyrinth.addBlock(Orientation.VERTICAL, 0, 7, 8); // Right column
        labyrinth.addBlock(Orientation.HORIZONTAL, 0, 1, 8); // Top row
*/
        labyrinth.addBlock(Orientation.VERTICAL, 1, 1, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 2, 4, 1);
        
        
        // Creamos los monstruos, los añadimos a la matriz de mosntruos y al laberinto
        
        // Nombre, inteligencia, fuerza
        Monster m1 = new Monster("slime", 999, 999);
        Monster m2 = new Monster("miau", 2, 2);

        //Monster m2 = new Monster("Dos", Dice.randomIntelligence(), Dice.randomStrength());
        //Monster m3 = new Monster("Tres", Dice.randomIntelligence(), Dice.randomStrength());

        monsters.add(m1);
        monsters.add(m2);        
        //monsters.add(m3);

        // Fila, columna, monstruo
        labyrinth.addMonster(0, 3, m1);
        labyrinth.addMonster(3,1, m2);
       // labyrinth.addMonster(4,3, m3);

       
    }

    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    private void logPlayerWon() {
        log += "The player won the fight \n";
    }

    private void logMonsterWon() {
        log += "The monster won the fight \n"; 
    }

    private void logResurrected() {
        log+= "- Player "+this.currentPlayerIndex+" resurrected as fuzzy.\n";    }

    private void logPlayerSkipTurn() {
        log += "Player cannot move, they're dead \n";
    }

    private void logPlayerNoOrders() {
        log += "Player cannot go there\n";
    }

    private void logNoMonster() {
        log += "Player didn't find a monster \n";
    }

    private void logRounds(int rounds, int max) {
        log += rounds + " rounds of " + max + " took place\n";
    }

    public boolean nextStep(Directions preferredDirection){
        log = "";
        
        if(!currentPlayer.dead()){
            
            Directions direction = actualDirection(preferredDirection);
            
            if(direction != preferredDirection){
                logPlayerNoOrders();
            }

            Monster monster = labyrinth.putPlayer(direction,currentPlayer);

            if(monster == null){
                logNoMonster();
            }
            else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
            
        }
        else manageResurrection();
        
        boolean endGame = finished();
        
        if(!endGame){
            nextPlayer();
        }
        
        return endGame;
    }

    private Directions actualDirection(Directions preferredDirection){
        
        // Usamos los consultores de columna y fila de Player
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow,
                                                                currentCol);
        
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }

    private GameCharacter combat(Monster monster) {
                
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;

        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);

        while ((!lose) && (rounds < MAX_ROUNDS)) {
            winner = GameCharacter.MONSTER;
            rounds++;

            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);

            if (!lose) {
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        logRounds(rounds,MAX_ROUNDS);
        return winner;
        
    }

    private void manageReward(GameCharacter winner) {

        if (winner == GameCharacter.PLAYER) {
            currentPlayer.receiveReward();
            logPlayerWon();
        } else {
            logMonsterWon();
        }
        
    }

    private void manageResurrection(){
        
        // LLamada a la funcion del dado
        if(Dice.resurrectPlayer()){
            currentPlayer.resurrect(); 
            logResurrected();
            FuzzyPlayer fuzzy = new FuzzyPlayer(currentPlayer);
            players.set(currentPlayerIndex, fuzzy);
            labyrinth.ConviertePlayer(fuzzy);
        }
        else logPlayerSkipTurn();
    }

    public void EarthEvent() {
        if (!earth) {
            labyrinth.Earthquake(players);
            earth = true;
        }
    }
}