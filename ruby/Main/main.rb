require_relative '../Game'
require_relative '../Controller/controller'
require_relative '../UI/textUI'

module Main
    class Main
        @@N_PLAYERS = 2
        def main
            @game = Irrgarten::Game.new(@@N_PLAYERS)
            @ui = UI::TextUI.new
            @control = Control::Controller.new(@game, @ui)
            @control.play
        end
    end

    juego = Main.new
    juego.main
end

