package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Manuel Cantero
 */
public abstract class CardDeck<T extends CombatElement> {
    private ArrayList<T> cardDeck;
    protected static final int TAMANIO = 50;
    
    public CardDeck(){
        cardDeck = new ArrayList<T>();
    }
    
    protected abstract void addCards();
    
    protected void addCard(T card){
        cardDeck.add(card);
    }
    
    public T nextCard(){
        if(cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(this.cardDeck); 
        }
        T card = cardDeck.getFirst();
        cardDeck.remove(card);
        return card;
    }
}
