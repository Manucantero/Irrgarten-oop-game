require_relative 'Dice'
require_relative 'Player'
module Irrgarten
    class FuzzyPlayer < Player
        def initialize(other)
            copy_cons(other)
        end

        def move(direction,valid_moves)
            return(Dice.next_step(super(direction, valid_moves),valid_moves,intelligence))
        end

        def attack
            return(Dice.intensity(strength)) + sum_weapons
        end

        def defensive_energy
            return(Dice.intensity(intelligence)) + sum_shields
        end

        def to_string
            super
        end
    end
end
