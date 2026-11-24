package Main;

import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;
import irrgarten.Game;

/**
 *
 * @author Manuel Cantero
 */
public class Main {
    
    private static final int NUM_PLAYERS = 2;
    
    public static void main(String[] args){
        
        // Primera Comprobacion manual hecha
        /**
        Game game = new Game(1);
        GameState gamestate1 = game.getGameState();
        System.out.println(gamestate1.getLabyrinth().toString());
        System.out.println(gamestate1.getLog());
        System.out.println(gamestate1.getPlayers());
        System.out.println(gamestate1.getMonsters());
        System.out.println(gamestate1.getCurrentPlayer());
        System.out.println(gamestate1.getWinner());
        
        
        game.nextStep(Directions.RIGHT);
        GameState gamestate2 = game.getGameState();
        System.out.println(gamestate2.getLabyrinth().toString());
        System.out.println(gamestate2.getLog());
        System.out.println(gamestate2.getPlayers());
        System.out.println(gamestate2.getMonsters());
        
        game.nextStep(Directions.RIGHT);
        GameState gamestate3 = game.getGameState();
        System.out.println(gamestate3.getLabyrinth().toString());
        System.out.println(gamestate3.getLog());
        System.out.println(gamestate3.getPlayers());
        System.out.println(gamestate3.getMonsters());
        
        game.nextStep(Directions.RIGHT);
        GameState gamestate4 = game.getGameState();
        System.out.println(gamestate4.getLabyrinth().toString());
        System.out.println(gamestate4.getLog());
        System.out.println(gamestate4.getPlayers());
        System.out.println(gamestate4.getMonsters());
        
        game.nextStep(Directions.RIGHT);
        GameState gamestate5 = game.getGameState();
        System.out.println(gamestate5.getLabyrinth().toString());
        System.out.println(gamestate5.getLog());
        System.out.println(gamestate5.getPlayers());
        System.out.println(gamestate5.getMonsters());
        
        
        game.nextStep(Directions.DOWN);
        GameState gamestate6 = game.getGameState();
        System.out.println(gamestate6.getLabyrinth().toString());
        System.out.println(gamestate6.getLog());
        System.out.println(gamestate6.getPlayers());
        System.out.println(gamestate6.getMonsters());
                
        game.nextStep(Directions.DOWN);
        GameState gamestate7 = game.getGameState();
        System.out.println(gamestate7.getLabyrinth().toString());
        System.out.println(gamestate7.getLog());
        System.out.println(gamestate7.getPlayers());
        System.out.println(gamestate7.getMonsters());
        
        game.nextStep(Directions.RIGHT);
        GameState gamestate8 = game.getGameState();
        System.out.println(gamestate8.getLabyrinth().toString());
        System.out.println(gamestate8.getLog());
        System.out.println(gamestate8.getPlayers());
        System.out.println(gamestate8.getMonsters());
        */
        
        // Comprobacion con Controller
        Game game = new Game(NUM_PLAYERS);
        TextUI ui = new TextUI();
        Controller control = new Controller(game, ui);
        control.play();
    }
    
}
