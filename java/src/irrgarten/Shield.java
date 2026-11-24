package irrgarten;

/**
 *
 * @author Manuel Cantero
 */
public class Shield extends CombatElement{
    

    public Shield(float protection, int uses) {
        super(protection,uses);
    }
    
    public float protect() {
        return produceEffect();
    }
    
    @Override
    public String toString () {
        String s = "S";
        return (s + super.toString());
    }
   
}
