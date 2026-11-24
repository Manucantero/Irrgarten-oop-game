#encoding:utf-8

require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Dice'
require_relative 'LabyrinthCharacter'

module Irrgarten
  # The Player class represents a player in the game with attributes such as 
  # number, intelligence, strength, health, position, weapons, and shields.
  class Player < LabyrinthCharacter
    # Maximum number of weapons a player can have
    @@MAX_WEAPONS = 2
    # Maximum number of shields a player can have
    @@MAX_SHIELDS = 3
    # Initial health of a player
    @@INITIAL_HEALTH = 10
    # Number of consecutive hits required to lose
    @@HITS2LOSE = 3

    @@ID = "Player"
    
    attr_reader :row, :col, :number, :weapons, :shields, :consecutive_hits

    # Initializes a new player with the given number, intelligence, and strength.
    # @param number [Integer] the player's number
    # @param intelligence [Integer] the player's intelligence
    # @param strength [Integer] the player's strength
    def initialize(number, intelligence, strength)
      @number = number 
      super(@@ID + @number.to_s, intelligence, strength, @@INITIAL_HEALTH)
      @consecutive_hits = 0

      # Arrays to store weapons and shields
      @weapons = Array.new
      @shields = Array.new 
    end

    def copy_cons(other)
        super
        @number = other.number
        @weapons = other.weapons
        @shields = other.shields
        @consecutive_hits = other.consecutive_hits
    end
    # Resurrects the player by clearing weapons and shields, resetting health and hits.
    def resurrect 
      @weapons.clear()
      @shields.clear()

      @health = @@INITIAL_HEALTH
      reset_hits()
      nil
    end

    # Calculates the attack power of the player.
    # @return [Integer] the attack power
    def attack 
      @strength + sum_weapons()
    end

    # Manages the defense of the player against a received attack.
    # @param received_attack [Integer] the power of the received attack
    def defend(received_attack)
      manage_hit(received_attack)
    end

    # Returns a string representation of the player.
    # @return [String] the string representation
    def to_s
           play = super
          
           play += ", Weapons: "
            @weapons.each do |i|
                play += " " + i.to_s
            end
            
            play += ", Shields: "
            @shields.each do |i|
                play += " " + i.to_s
            end

            play += " ] \n"
            return play
    end
    

    # Moves the player in the given direction if the move is valid.
    # @param direction [Symbol] the direction to move
    # @param valid_moves [Array<Symbol>] the list of valid moves
    def move(direction, valid_moves)
      size = valid_moves.size
      contained = valid_moves.include?(direction)

      if (size > 0 && !contained)
        return valid_moves[0]
      else
        return direction
      end
    end

    # Handles the reception of a reward.
    def receive_reward
      w_reward = Dice.weapons_reward
      s_reward = Dice.shields_reward

      for i in 0...w_reward
          wnew = new_weapon
          receive_weapon(wnew)
      end

      (1...s_reward).each do
        snew = self.new_shield
        self.receive_shield(snew)
      end

      extra_health = Dice.health_reward
      @health += extra_health
    end

    private

    # Creates a new weapon.
    # @return [Weapon] the new weapon
    def new_weapon
      Weapon.new(Dice.weapon_power, Dice.uses_left)
    end

    # Creates a new shield.
    # @return [Shield] the new shield
    def new_shield
      Shield.new(Dice.shield_power, Dice.uses_left)
    end

    # Calculates the defensive energy of the player.
    # @return [Integer] the defensive energy
    def defensive_energy
      @intelligence + sum_shields()
    end

    # Resets the consecutive hits counter.
    def reset_hits
      @consecutive_hits = 0
    end
    

    # Increases the consecutive hits counter by one.
    def inc_consecutive_hits
      @consecutive_hits += 1
    end

    # Sums the attack power of all weapons.
    # @return [Integer] the total attack power of weapons
    
  protected
    def sum_weapons
        sum = 0.0 #float
        for w in @weapons
            sum += w.attack()
        end
        sum
    end

    # Sums the defensive power of all shields.
    # @return [Integer] the total defensive power of shields
    def sum_shields
            sum = 0.0 #float
            for s in @shields
                sum += s.protect
            end
            sum
        end
    private
    # Handles the reception of a weapon.
    # @param w [Weapon] the received weapon
    def receive_weapon(w)
        @weapons.each do |i|
            wi = i
            discard = wi.discard
            if(discard)
                @weapons.delete(i)
            end
        end

        if(@weapons.size < @@MAX_WEAPONS)
            @weapons.push(w)
        end
    end

    # Handles the reception of a shield.
    # @param s [Shield] the received shield
    def receive_shield(s)
        @shields.each do |i|
            si = i
            discard = si.discard
            if(discard)
                @shields.delete(i)
            end
        end

        if(@shields.size < @@MAX_SHIELDS)
            @shields.push(s)
        end
    end

    # Manages the hit received by the player.
    # @param received_attack [Integer] the power of the received attack
    def manage_hit(received_attack)
      defense = defensive_energy()

      if defense < received_attack
        got_wounded()
        inc_consecutive_hits()
      else
        reset_hits()
      end

      if @consecutive_hits == @@HITS2LOSE || dead()
        reset_hits()
        return true
      else
        return false
      end
    end

  end

end
