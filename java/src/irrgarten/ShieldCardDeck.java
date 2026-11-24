
package irrgarten;

/**
 *
 * @author Manuel Cantero
 */
public class ShieldCardDeck extends CardDeck<Shield>{
    @Override
    protected void addCards(){
        for(int i = 0; i < TAMANIO; i++){
            addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
        }
    }
}
