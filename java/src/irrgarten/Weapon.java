package irrgarten;

/**
 *
 * @author Manuel Cantero
 */

public class Weapon extends CombatElement{
    
   
    public Weapon(float power, int uses) {
        super(power,uses);
    }
    
    public float attack() {
        return produceEffect();
    }
    
    @Override
    public String toString () {
        String s = "W";
        return (s + super.toString());
    }
    
}
