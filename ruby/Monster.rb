#encoding:utf-8

require_relative 'LabyrinthCharacter'

module Irrgarten
    class Monster < LabyrinthCharacter
    # Initial health value for all monsters
    @@INITIAL_HEALTH = 5

    # Constructor for the Monster class
    # @param name [String] the name of the monster
    # @param intelligence [Integer] the intelligence level of the monster
    # @param strength [Integer] the strength level of the monster
    def initialize (name, intelligence, strength)
        super(name, intelligence, strength, @@INITIAL_HEALTH)
    end

    # Performs an attack using the monster's strength
    # @return [Integer] the intensity of the attack
    def attack
      Dice.intensity(@strength)
    end
    # Defends against an incoming attack
    # @param received_attack [Integer] the intensity of the received attack
    def defend(received_attack)
      is_dead = dead()
      if !is_dead
        defensive_energy = Dice.intensity(@intelligence)

        if defensive_energy < received_attack
          got_wounded()
          is_dead = dead()
        end
      end
      is_dead
    end
  end
end
