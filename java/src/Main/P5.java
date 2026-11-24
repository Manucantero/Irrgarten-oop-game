
package Main;

import irrgarten.Game;
import irrgarten.UI.GraphicUI;
import irrgarten.controller.Controller;
/**
 *
 * @author Manuel Cantero
 */
public class P5 {
    private static final int NUM_PLAYERS = 5;

    public static void main(String[] args){
        Game game = new Game(NUM_PLAYERS);
        GraphicUI ui = new GraphicUI();
        Controller control = new Controller(game, ui);
        control.play();
    }
}
