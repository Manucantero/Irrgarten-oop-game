#encoding: UTF-8

require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Dice'
require_relative 'Directions'
require_relative 'Orientation'
require_relative 'GameCharacter'
require_relative 'GameState'
module Irrgarten
    class Test1
        def self.main
        
            
            #Weapon
            
            wp = Weapon.new(10.0,5)
            
            puts "=== Testing Weapon ==="
            
            puts " Discard: #{wp.discard}"    #Siempre false
            puts " W: #{wp.to_s}"
            puts
            
            puts " Attack: #{wp.attack} "
            puts " W: #{wp.to_s}"
            puts " Discard: #{wp.discard}"
            puts
            
            puts " Attack: #{wp.attack} "
            puts " W: #{wp.to_s}"
            puts " Discard: #{wp.discard}"
            puts
            
            puts " Attack: #{wp.attack} "
            puts " W: #{wp.to_s}"
            puts " Discard: #{wp.discard}"
            puts
            
            puts " Attack: #{wp.attack} "
            puts " W: #{wp.to_s}"
            puts " Discard: #{wp.discard}"
            puts
            
            puts " Attack: #{wp.attack} "
            puts " W: #{wp.to_s}"
            puts " Discard: #{wp.discard}" #Siempre true
            puts
            
            
            #SHield
            
            sh = Shield.new(10.0,5)
            
            puts "=== Testing Shield ==="
            
            puts " Discard: #{sh.discard}" #Siempre false
            puts " S: #{sh.to_s}"
            puts
            
            puts " Protect: #{sh.protect} "
            puts " S: #{sh.to_s}"
            puts " Discard: #{sh.discard}"
            puts
            
            puts " Protect: #{sh.protect} "
            puts " S: #{sh.to_s}"
            puts " Discard: #{sh.discard}"
            puts
            
            puts " Protect: #{sh.protect} "
            puts " S: #{sh.to_s}"
            puts " Discard: #{sh.discard}"
            puts
            
            puts " Protect: #{sh.protect} "
            puts " S: #{sh.to_s}"
            puts " Discard: #{sh.discard}"
            puts
            
            puts " Protect: #{sh.protect} "
            puts " S: #{sh.to_s}"
            puts " Discard: #{sh.discard}" #Siempre true
            puts
            
            # Enums
            
            puts "=== Testing Enumerations ==="
            puts "Direction: #{Directions::LEFT}"
            puts "Direction: #{Directions::RIGHT}"
            puts "Direction: #{Directions::UP}"
            puts "Direction: #{Directions::DOWN}"
            puts
            
            puts "Orientation: #{Orientation::VERTICAL}"
            puts "Orientation: #{Orientation::HORIZONTAL}"  
            puts
            
            puts "Character: #{GameCharacter::PLAYER}"
            puts "Character: #{GameCharacter::MONSTER}"            
            puts
            
            #GameState
            
            puts "=== Testing GameState ==="
            gs = GameState.new("labyrinth", "players", "monsters", 3, false, "log" )
            puts " Lb= #{gs.labyrinth} "
            puts " Pl= #{gs.players} "
            puts " Ms= #{gs.monsters} "
            puts " Log= #{gs.log} "
            puts " current= #{gs.current_player} "
            puts " winner= #{gs.winner} "
            puts
            
            #Dice
            puts "=== Testing Dice ==="
            puts "Random Pos: #{Dice.random_pos(2)}"
            puts "Who starts: #{Dice.who_starts(4)}"
            puts "Random intelligence: #{Dice.random_intelligence}"
            puts "Random strength: #{Dice.random_strength.round(2)}"
            puts "Resurrect player: #{Dice.resurrect_player}"
            puts "Weapons reward: #{Dice.weapons_reward}"
            puts "Shields reward: #{Dice.shields_reward}"
            puts "Health reward: #{Dice.health_reward}"
            puts "Weapon Power: #{Dice.weapon_power}"
            puts "Shield Power: #{Dice.shield_power}"            
            puts "Uses Left: #{Dice.uses_left}"      
            puts "Intensity: #{Dice.intensity(5)}"     
            puts "Discard: #{Dice.discard_element(0)}"                 
        end
    end
    Test1.main
end
