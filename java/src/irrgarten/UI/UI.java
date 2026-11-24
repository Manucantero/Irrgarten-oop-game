
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

/**
 *
 * @author Manuel Cantero
 */
public interface UI {
    
     public Directions nextMove();
     public void showGame(GameState gameState);
     public boolean Earthquake();
}
