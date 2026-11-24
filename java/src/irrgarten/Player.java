package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author Manuel Cantero
 */
public class Player extends LabyrinthCharacter {
    
    static private final int MAX_WEAPONS = 2;
    static private final int MAX_SHIELDS = 3;
    static private final int INITIAL_HEALTH = 10;
    static private final int HITS2LOSE = 3;
    static private final String id = "Player#";
    
    private char number;
    private int consecutiveHits;
    
    public ArrayList<Weapon> weapons;
    public ArrayList<Shield> shields;
    
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;

    public Player(char number, float intelligence, float strength){
        super(id + number,intelligence,strength,INITIAL_HEALTH);
        this.number = number;
        consecutiveHits = 0;
        
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        
        shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();
    }
    
    public Player(Player other){
        super(other);
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;
        
        this.weapons = new ArrayList<>(other.weapons);
        this.shields = new ArrayList<>(other.shields);
        
        this.weaponCardDeck = other.weaponCardDeck;
        this.shieldCardDeck = other.shieldCardDeck;
    }
    
    public void resurrect() {

        weapons.clear();
        shields.clear();
        this.setHealth(INITIAL_HEALTH);
        resetHits();
    }

     
    public char getNumber(){
        return number;
    }

    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        // Puede moverse, pero no en la direccion que queria
        if((size > 0) && (!contained)){ 
            return validMoves.get(0);
        }
        else return direction; // Puede moverse a donde queria
    }
    
    @Override
    public float attack() {
        return (this.getStrength() + sumWeapons());
    }
    
    @Override
    public boolean defend (float receivedAttack) {
        return (manageHit(receivedAttack));
    }
    
    public void receiveReward() {
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        Weapon wnew;
        Shield snew;
        
        for(int i = 0; i < wReward; i++){
            wnew = newWeapon();
            receiveWeapon(wnew);
        }
        
        for(int i = 0; i < sReward; i++){
            snew = newShield();
            receiveShield(snew);
        }
    }
    
    @Override
    public String toString() {
       return( super.toString() + ", W: " + weapons.toString() + 
               " , S: " + shields.toString() + " ]");
    }
    
    private void receiveWeapon(Weapon w){
        Weapon wi;
        boolean discard;
        
        for(int i = 0; i < weapons.size(); i++){
            wi = weapons.get(i);
            discard = wi.discard();
            
            if(discard) weapons.remove(wi);
        }
        
        if(weapons.size() < MAX_WEAPONS) weapons.add(w);
    }
    
    private void receiveShield(Shield s){
        Shield si;
        boolean discard;
        
        for(int i = 0; i < shields.size(); i++){
            si = shields.get(i);
            discard = si.discard();
            if(discard){
                shields.remove(si);
            }
        }
        
        if(shields.size() < MAX_SHIELDS) shields.add(s);
    }
    
    private Weapon newWeapon() {
        return weaponCardDeck.nextCard();
    }
    
    private Shield newShield() {
        return shieldCardDeck.nextCard();
    }
    
    protected float sumWeapons() {
        float sum = 0;
        
        for (int i = 0; i < weapons.size(); i++) {
            sum += weapons.get(i).attack();
        }
        
        return sum;
    }
    
    protected float sumShields() {
        float sum = 0;
        
        for (int i = 0; i < shields.size(); i++) {
            sum += shields.get(i).protect();
        }
        
        return sum;
    }
    
    protected float defensiveEnergy() {
        return (this.getIntelligence() + sumShields());
    }
    
    private boolean manageHit(float receivedAttack){
        float defense = defensiveEnergy();
        
        if(defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }
        else{
            resetHits();
        }
        
        boolean lose = false;
        
        if((consecutiveHits == HITS2LOSE) || (dead())){
            resetHits();
            lose = true;
        }
        
        return lose;
    }
    
    private void resetHits() {
        consecutiveHits=0;
    }


    private void incConsecutiveHits() {
        consecutiveHits++;
    }
}
