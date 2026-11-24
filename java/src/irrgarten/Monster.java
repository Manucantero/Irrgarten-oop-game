package irrgarten;

/**
 *
 * @author Manuel Cantero
 */
public class Monster extends LabyrinthCharacter {
    
    static private final int INITIAL_HEALTH = 5;
    
    public Monster(String name, float intelligence, float strength) {
        super(name,intelligence,strength, INITIAL_HEALTH);
    }
    
    @Override
    public boolean defend(float receivedAttack){
        boolean isDead = dead();
        if(!isDead){
            float defensiveEnergy = Dice.intensity(this.getIntelligence());
            
            if(defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
   
    @Override
    public float attack(){
        return Dice.intensity(getStrength());
    }

}
