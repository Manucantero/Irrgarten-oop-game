/**
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Manuel Cantero
 */

public class Dice {
    static private final int MAX_USES = 5;
    static private final float MAX_INTELLIGENCE = 10.0f;
    static private final float MAX_STRENGTH = 10.0f;
    static private final float RESURRECT_PROB = 0.3f;
    static private final int WEAPONS_REWARD = 2;
    static private final int SHIELDS_REWARD = 3;
    static private final int HEALTH_REWARD = 5;
    static private final int MAX_ATTACK = 3;
    static private final int MAX_SHIELD = 2;
    static private Random generator = new Random();
    
    /** 
     * @brief Devuelve un número aleatorio de fila o columna n con 
     * 0 <= n < max
     * @param max Valor de filas a columnas
     */
    static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    
    /**
     * @brief Devuelve el indice del jugador que comenzará la partida.
     * @param nplayers número de jugadores en la partida. nplayers >= 0
     */
    static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    /**
     * @brief Devuelve un valor aleatorio de inteligencia entre 0 y 
     * MAX_INTELLIGENCE 0 <= n < MAX_INTELLIGENCE
     */
    static float randomIntelligence(){
        return generator.nextFloat()*MAX_INTELLIGENCE;
    }
    
    /**
     * @brief Devuelve un valor aleatorio de fuerza entre 0 y 
     * MAX_STRENGTH 0 <= n < MAX_STRENGTH
     */
    static float randomStrength(){
        return generator.nextFloat()*MAX_STRENGTH;
    }
    
    /**
     * @brief Indica si un jugador ha de ser resucitado o no 
     */
    static boolean resurrectPlayer(){
        return generator.nextFloat() <= RESURRECT_PROB;
    }
    
    /**
     * @brief Devuelve el numero de armas que ha de recibir un jugador
     * 0 <= numero de armas <= WEAPONS_REWARD
     */
    static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    
    /**
     * @brief Devuelve el numero de escudos que ha de recibir un jugador
     * 0 <= numero de escudos <= SHIELDS_REWARD
     */
    static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    /**
     * @brief Devuelve el numero de unidades de salud que ha de recibir 
     * un jugador 0 <= numero de escudos <= HEALTH_REWARD
     */
    static int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    /**
     * @brief Devuelve un numero entre 0 y MAX_ATTACK 0 <= n < MAX_ATTACK
     */
    static float weaponPower(){
        return generator.nextFloat()*MAX_ATTACK;
    }
    
    /**
     * @brief Devuelve un numero entre 0 y MAX_SHIELD 0 <= n < MAX_SHIELD
     */
    static float shieldPower(){
        return generator.nextFloat()*MAX_SHIELD;
    }
    
    /**
     * @brief Devuelve el numero de usos que se le asignará  a un arma o
     * escudo <= n <= MAX_USES
     */
    static int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    /**
     * @brief Devuelve la cantidad de competencia aplicada 0 <= n < competence
     * @param competence indica la competencia maxima que se puede aplicar
     */
    static float intensity(float competence){
        return generator.nextFloat()*competence;
    }
    
    /** 
     * @brief Devuelve un valor inversamente proporcional a usesLeft. Esto es,
     * cuanto mayor sea usesLeft más probabilidades habrá de que se devuelva
     * false y viceversa.
     * @param usesLeft 
     */
    static boolean discardElement(int usesLeft){
        float prob = (float)usesLeft/MAX_USES;
        return generator.nextFloat() >= prob;
    }
    
    static Directions nextStep(Directions preference, 
                    ArrayList<Directions> validMoves, float intelligence){
        if(Dice.randomIntelligence() < intelligence){
            return preference;
        }
        else{
            return(validMoves.get(Dice.randomPos(validMoves.size())));
        }
    }
}
