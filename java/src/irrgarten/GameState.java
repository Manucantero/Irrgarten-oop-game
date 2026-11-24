package irrgarten;

/**
 *
 * @author Manuel Cantero
 */
public class GameState {
    
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    private boolean earth;

    public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log, boolean earth) {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
        this.earth = earth;
    }

    public String getLabyrinth() {
        return labyrinth;
    }

    public String getPlayers() {
        return players;
    }

    public String getMonsters() {
        return monsters;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isWinner() {
        return winner;
    }

    public String getLog() {
        return log;
    }

    public boolean getEarth() {
        return earth;
    }
    
    
    
}
