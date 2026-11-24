require_relative '../Game'
require_relative '../Controller/controller'
require_relative '../UI/textUI'
require_relative '../FuzzyPlayer'
require_relative '../FuzzyPlayer'

module Prueba
    class FuzzyPlayerGoneNuts < FuzzyPlayer
        def initialize(other)
            copy_cons(other)
            @cont_fuzzy = 0
            @cont_up = 0
        end

        def move(direction,valid_moves)
            if (Irrgarten::Dice.gone)
                @cont_fuzzy += 1
                return(super(direction, valid_moves)
            else
                @cont_up += 1
                return(Irrgarten::Directions.UP)
            end
        end
    end

    class Examen 
        def main
            @game = Irrgarten::Game.new(1)
            @ui = UI::TextUI.new
            @control = Control::Controller.new(@game, @ui)
             22.times do
                @view.show_game(@game.game_state) 
                direction = @view.next_move 
                end_of_game = @game.next_step(direction)
            end
      @view.show_game(@game.game_state)
        end
      
    end
end

