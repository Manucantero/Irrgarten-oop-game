package irrgarten;

/**
 *
 * @author Manuel Cantero
 */
public class TestP2 {
    
    public static void main(String[] args) {
     /*
        
       Constructores:

Weapon w = new Weapon(poder, usos)		usos = Dice.usesLeft()
Shields s = new Shield(protection,usos)		weaponPower/shieldPower()
Game g = new Game(nplayers)
GameState gs = new GameState(todos)
Monster ms = new Monster(nombre, inteligencia,fuerza)
Player pl = new Player(numero, inteligencia,fuerza)

Crear un laberinto (n_filas,n_cols, fila_salida, col_salida)
Labyrinth lb =  newLabyrinth(int nRows, int nCols, int exitRow, int exitCol)

Dar forma al laberinto: 
Game.configurelabyrinth

labyrinth.addBlock(Orientation.VERTICAL, 1, 1, 1);  Orientacion, fila, columna, longitud

Añadir un monstruo:
Monster m1 = new Monster("slime", 999, 999);
monsters.add(m1);
labyrinth.addMonster(0, 3, m1);


Si queremos hacer que los jugadores estén en una posicion concreta, modificamos spread players (Labyrinth)

def spread_players(players)
      old_col = -1;
      old_row = -1;

      pos0 y pos1 son las posiciones fijas donde queremos que los jugadores 0 y 1 esten
      pos0 = [1,3]
      pos1 = [4,3]
      
      Llamamos a put_player_2d (privado)
								  player es un vector
      put_player_2d(old_row, old_col, pos0[@@ROW], pos0[@@COL], players[0])
      put_player_2d(old_row, old_col, pos1[@@ROW], pos1[@@COL], players[1])
    
    end



################################################################################



Si queremos tener una secuencia fija de movimientos, modificamos textUI

Creamos el atributo de clase:

 # Secuencia fija de direcciones 
    @@FIXED_DIRECTIONS = [
      Irrgarten::Directions::UP,   # Primera UP
      Irrgarten::Directions::UP    # Segunda UP
    ] + Array.new(20, Irrgarten::Directions::LEFT) # 20 veces LEFT
    
 Tenemos que definir initialize para poder llevar la cuenta con un indice:
def initialize
      @current_index = 0
 end

Por ultimo modificamos next_move

def next_move
      
	direction = @@FIXED_DIRECTIONS[@current_index]
	@current_index += 1

	return direction
end

#############################################

For:

@n_rows.times do |i|
   @n_cols.times do |j|
       @casillas[i][j] = @@EMPTY_CHAR
   end
end

---------------------------------------
players.each do |p|
	pos = random_empty_pos
	monster = put_player_2D(old_row, old_col, pos[@@ROW], pos[@@COL],p)
end

-----------------------

(1...n_rows).each do |i|
	(1...n_cols).each do |j|
	     @labyrinth_square[i][j] = @@EMPTY_CHAR
	end
end

-------------------------------------------------
4.times do |i|
                game.next_player
            end

#################################################################

Nos piden cambiar las cosas de Player para que sea CombatElement
Cambios:

Atributos:
private ArrayList<CombatElement> weapons;
private ArrayList<CombatElement> shields;

receiveWeapon y receiveShield  ponemos: CombatElement wi;

en sum:    sum += weapons.get(i).produceEffect();

Como queremos que aparezca con un arma: this.receiveWeapon(newWeapon()); en el constructor


Hacer que los jugadores cambien las armas:
 public void swapWeapons(Player other){
        ArrayList<CombatElement> tmp = weapons;
        weapons = other.weapons;
        other.weapons = tmp;
    }
    
  
    public void loseEnergy(){
        attack();
    }
    


<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
RUBY:

Constructor de copia:

def copy_cons(other)
        super
        @number = other.number
        @weapons = other.weapons
        @shields = other.shields
        @consecutive_hits = other.consecutive_hits
    end


public Player(Player other){
        super(other);
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;
        
        this.weapons = new ArrayList<>(other.weapons);
        this.shields = new ArrayList<>(other.shields);
        
        this.weaponCardDeck = other.weaponCardDeck;
        this.shieldCardDeck = other.shieldCardDeck;
    }
    

---------------------------

Hacer que se salte el turno:

 @Override
    public void nextPlayer() {
        int index = Dice.random_player(this.getPlayers().size());
        super.nextPlayer();
        if(index == this.getCurrentPlayerIndex()) {
            logSkipRandomPlayer();
            super.nextPlayer();
        }
    }



!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

def self.main

            @players = Array.new

            @@NUM_PLAYERS.times do |i|
                @players << ::Irrgarten::Player.new("#{i}", ::Irrgarten::Dice.random_intelligence, ::Irrgarten::Dice.random_strength)
            end

            puts "Players:\n#{@players.map(&:to_s).join(', ')}" # Print all the stats

        end
        
        
        
        
        
        def self.main

            labyrinth.to_s
        
        getlabyrinth
        
        convert to fuuzygone
        
        10.times do |i|

            @@NUM_PLAYERS.times do |i|
                @players << ::Irrgarten::Player.new("#{i}", ::Irrgarten::Dice.random_intelligence, ::Irrgarten::Dice.random_strength)
            end

            puts "Players:\n#{@players.map(&:to_s).join(', ')}" # Print all the stats

        end
    
    */
 

    }
}
