require_relative 'Dice'

module Irrgarten
    class CombatElement
        def initialize(effect, uses)
            @effect = effect
            @uses = uses
        end

        def discard
            return (Dice.discard_element(@uses))
        end

        def to_s
            return "[#{@effect},#{@uses}]"
        end

        protected
        def produce_effect
            if(@uses > 0)
                @uses = @uses - 1
            else
                return 0 
            end

            return @effect
        end
    end
end