package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author Manuel Cantero
 */
public class Labyrinth {
    
    // Atributos de clase
    private static final char BLOCK_CHAR = 'X';
    static private final char EMPTY_CHAR = '-';
    static private final char MONSTER_CHAR = 'M';
    static private final char COMBAT_CHAR = 'C';
    static private final char EXIT_CHAR = 'E';
    static private final int INVALID = -1;
    
    // Estos dos son para las matrices, concretamente metodos dir2pos y
    // randomEmptyPos
    static private final int ROW = 0;
    static private final int COL = 1;
    
    // Atributos de dimensiones y salida
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    // Atributos referidos a funcionamiento
    private char[][] casillas;
    private Monster[][] monsters;
    private Player[][] players;

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        casillas = new char[nRows][nCols];
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols]; 
        
        // Inicializamos el laberinto a casillas vacias
        for(int i = 0; i < nRows; i++){
            for(int j = 0; j < nCols; j++){
                casillas[i][j] = EMPTY_CHAR;
            }
        }
        // Situamos la salida¡
        casillas[exitRow][exitCol] = EXIT_CHAR;
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        Player p;
        int [] pos;
        int oldRow = INVALID;
        int oldCol = INVALID;
        Monster monster;
        for(int i = 0; i < players.size(); i++){
            p = players.get(i);
            pos = randomEmptyPos();
            monster = putPlayer2D(oldRow,oldCol,pos[ROW],pos[COL],p);
        }
    }
    
    public boolean haveAWinner(){
        boolean haveaWinner = false;
        if(casillas[exitRow][exitCol] != EXIT_CHAR){
            haveaWinner = true;
        }
        return haveaWinner;
    }
    
    @Override
    public String toString(){
        String lab = "";
        for (int i = 0; i < nRows; i++){
            for(int j = 0; j < nCols; j++){
                lab += casillas[i][j] + " ";
            }
            lab += "\n";
        }
        return lab;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if((posOK(row,col)) && (emptyPos(row,col))){
            casillas[row][col] = MONSTER_CHAR;
            monster.setPos(row, col);
            monsters[row][col] = monster;
        }
    }
    
    /**
     * @brief Metodo de posicionamiento de jugador
     * @param direction
     * @param player
     * @return 
     */
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int [] newPos = dir2Pos(oldRow,oldCol, direction);
        Monster monster = putPlayer2D(oldRow,oldCol,newPos[ROW],newPos[COL],
                            player);
        
        return monster;
    }
    
    
    public void addBlock (Orientation orientation, int startRow, int startCol, 
                    int length){
        int incRow=0 ,incCol=0;
        
        if(orientation == Orientation.VERTICAL){
            incRow++;
        }
        else{
            incCol++;
        }
        
        int row = startRow;
        int col = startCol;
        
        while((posOK(row,col)) && (emptyPos(row,col)) && (length>0)){
            casillas[row][col] = BLOCK_CHAR;
            length--;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> output = new ArrayList<Directions>();
        
        if(canStepOn(row + 1, col)) 
            output.add(Directions.DOWN);
        if(canStepOn(row-1,col)) 
            output.add(Directions.UP);
        if(canStepOn(row,col + 1)) 
            output.add(Directions.RIGHT);
        if(canStepOn(row, col-1)) 
            output.add(Directions.LEFT);
        
        return output;
    }
    
    private boolean posOK(int row, int col){
        return (0<=row && (row < nRows) && 0<=col && (col < nCols)); 
    }
    
    private boolean emptyPos(int row, int col){
        return casillas[row][col] == EMPTY_CHAR;
    }
    
    private boolean monsterPos(int row, int col){
        return casillas[row][col] == MONSTER_CHAR;
    }
    
    private boolean exitPos(int row, int col){
        return casillas[row][col] == EXIT_CHAR;
    }
    
    private boolean combatPos(int row, int col){
        return casillas[row][col] == COMBAT_CHAR;
    }
    
    private boolean canStepOn(int row, int col){
        return (posOK(row, col) && (monsterPos(row,col) || exitPos(row,col)
                    || emptyPos(row,col)));
    }
    
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(combatPos(row, col)){ 
                casillas[row][col] = MONSTER_CHAR;
            }
            else{ 
                casillas[row][col] = EMPTY_CHAR;
            }   
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int[] pos = new int[2];
        pos[ROW] = row;
        pos[COL] = col;
        switch (direction){
            
                case RIGHT:
                    pos[COL]++;
                    break;
                    
                case LEFT:
                    pos[COL]--;
                    break;
                    
                case UP:
                    pos[ROW]--;
                    break;
                    
                case DOWN:
                    pos[ROW]++;
                    break;
                   
       }
       return pos;
    }
    
    private int[] randomEmptyPos(){
        int[] pos = new int[2];
        pos[ROW] = Dice.randomPos(nRows);
        pos[COL] = Dice.randomPos(nCols);
        
        while(!this.emptyPos(pos[ROW],pos[COL])){
            pos[ROW] = Dice.randomPos(nRows);
            pos[COL] = Dice.randomPos(nCols);
        }
        
        return pos;
    }
    
     public void ConviertePlayer(FuzzyPlayer other){
        int row = other.getRow();
        int col = other.getCol();
        
        if(players[row][col].getNumber() == other.getNumber()){
            players[row][col] = other;
        }
    }
        
    /**
     * @brief Metodo de posicionamiento de jugador
     * @param oldRow
     * @param oldCol
     * @param row
     * @param col
     * @param player
     * @return 
     */
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, 
                        Player player){

        Monster output = null;
        char number;
        
        if(canStepOn(row,col)){
            
            if(posOK(oldRow,oldCol)){
                
                if(players[oldRow][oldCol] == player){
                    updateOldPos(oldRow,oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            
            boolean monsterPos = monsterPos(row,col);
            
            if(monsterPos){
                casillas[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }
            else{
                number = player.getNumber();
                casillas[row][col] = number;
            }
            
            players[row][col] = player;
            player.setPos(row, col);
        }
        
        return output;
    }
    
    public void Earthquake(ArrayList<Player> players) {
        Player p;
        int [] pos;
        int oldRow;
        int oldCol;
        for(int i = 0; i < players.size(); i++){
            p = players.get(i);
             oldRow = p.getRow();
             oldCol = p.getCol();
            pos = randomEmptyPos();
            putPlayer2D(oldRow,oldCol,pos[ROW],pos[COL],p);
        }
    }
}
