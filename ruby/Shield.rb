#encoding: UTF-8

require_relative 'CombatElement'
module Irrgarten
    class Shield < CombatElement

        def initialize(p,u)
            super(p,u)
        end

        def protect
            produce_effect
        end

        def to_s
            "S" + super
        end
    end
end
