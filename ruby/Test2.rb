#encoding:utf-8
require_relative 'Dice'
require_relative 'GameState'
require_relative 'Shield'
require_relative 'Weapon'
require_relative 'GameCharacter'
require_relative 'Orientation'
require_relative 'Directions'
require_relative 'Monster'
require_relative 'Labyrinth'
require_relative 'Game'
require_relative 'Player'

class TestP2
  def self.main
    lab = Irrgarten::Labyrinth.new(10,10,1,1)
    puts lab.to_s
  end
end

TestP2.main