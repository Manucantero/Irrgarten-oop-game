#encoding: UTF-8

require_relative 'CombatElement'

module Irrgarten
    class Weapon < CombatElement

        def initialize(p,u)
            super(p,u)
        end

        def attack
            produce_effect
        end

        def to_s
            "W" + super
        end
    end
end
