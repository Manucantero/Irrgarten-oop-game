package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author Manuel Cantero
 */
public class FuzzyPlayer extends Player{
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        return (Dice.nextStep(super.move(direction, validMoves), validMoves,
                this.getIntelligence()));
    }
    
    @Override
    public float attack(){
        return(Dice.intensity(super.getStrength()) + super.sumWeapons());
    }
    
    @Override
    protected float defensiveEnergy(){
        return(Dice.intensity(super.getIntelligence()) + super.sumShields());
    }
    
    @Override
    public String toString(){
        return (super.toString());
    }
    
}