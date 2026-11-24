
package irrgarten;

/**
 *
 * @author Manuel Cantero
 */
public abstract class LabyrinthCharacter {
    
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    static private final int INVALID = -1;

    
    public LabyrinthCharacter(String name, float intelligence, float strength, float health) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        
        row = INVALID;
        col = INVALID;
    }

    public LabyrinthCharacter(LabyrinthCharacter other) {
        this(other.name, other.intelligence, other.strength, other.health);
        setPos(other.row,other.col);
    }
    
    public boolean dead () {
        return (health <=0);
    }

    protected float getIntelligence() {
        return intelligence;
    }

    protected float getStrength() {
        return strength;
    }

    protected float getHealth() {
        return health;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    protected void setHealth(float health) {
        this.health = health;
    }
    
    public void setPos (int row, int col) {
        
        this.row = row;
        this.col = col;
        
    }
    
    protected void gotWounded(){
        health--;
    }
    
    @Override
    public String toString() {
         return (name + "[S: " + String.format("%.2f", strength) + 
           ", I: " + String.format("%.2f", intelligence) + 
           ", H: " + String.format("%.2f", health)  + "]");
    
    }
    public abstract float attack();
    
    public abstract boolean defend(float attack);
}
