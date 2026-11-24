#encoding:utf-8
require_relative 'Player'
require_relative 'Labyrinth'
require_relative 'Dice'
require_relative 'Orientation'
require_relative 'Monster'
require_relative 'GameState'
require_relative 'GameCharacter'
require_relative 'FuzzyPlayer'

# The Irrgarten module encapsulates all classes and methods related to the game.
module Irrgarten
  # The Game class represents the main game logic and state.
  class Game
    # Maximum number of rounds in the game.
    @@MAX_ROUNDS = 10

    # Initializes a new game with the specified number of players.
    # @param nplayers [Integer] the number of players in the game
    def initialize(nplayers)
      # Array of players in the game.
      @players = Array.new()
      # Add new players with random attributes
      nplayers.times do |i|
        @players << Player.new("#{i}", Dice.random_intelligence, Dice.random_strength)
      end
      
      # Index of the current player.
      @current_player_index = Dice.who_starts(nplayers)
      # The current player object.
      @current_player = @players[@current_player_index]
      # Array of monsters in the game.
      @monsters = Array.new
      # The labyrinth object representing the game board.
      
      # Si quiero una casilla aleatoria poner en vez de los dos ultimos
      # Dice.random_pos(n_filas)
      @labyrinth = Labyrinth.new(3,3,0,0)
      # Log of game events.
      @log = ""

      # Configure the labyrinth and spread the players throughout it.
      configure_labyrinth()
      
      @labyrinth.spread_players(@players)
      
       @log = "Game started\n"
    end

    # Checks if the game has finished.
    # @return [Boolean] true if there is a winner, false otherwise
    def finished
      @labyrinth.have_a_winner()
    end

    # Gets the current state of the game.
    # @return [GameState] the current game state
    def game_state
            players_s = ""
            monsters_s = ""

            @players.each do |p|
                players_s += p.to_s + "\n"
            end

            @monsters.each do |m|
                monsters_s += m.to_s + "\n"
            end

            gamestate = GameState.new(@labyrinth.to_s, players_s, 
                                        monsters_s, @current_player_index, finished,@log)
            
        end
    
    # Determines the next step based on the preferred direction.
    # @param preferred_direction [Symbol] the preferred direction to move
    def next_step(preferred_direction)
      @log = ""

      dead = @current_player.dead

      if !dead
        direction = actual_direction(preferred_direction)

        log_player_no_orders if direction != preferred_direction

        monster = @labyrinth.put_player(direction, @current_player)

        if (monster == nil)
          log_no_monster
        else
          winner = combat(monster)
          manage_reward(winner)
        end

      else
        manage_resurrection
      end

      end_game = finished
      next_player if !end_game

      end_game
    end

    private

    # Configures the labyrinth.
    def configure_labyrinth
    
    # Orientacion, fila, columna, longitud
    @labyrinth.add_block(Irrgarten::Orientation::VERTICAL,1,1,3)
    # @labyrinth.add_block(Irrgarten::Orientation::VERTICAL,0,7,8)
    # @labyrinth.add_block(Irrgarten::Orientation::HORIZONTAL,0,1,8)
    # @labyrinth.add_block(Irrgarten::Orientation::HORIZONTAL,7,1,8)

    # Creamos el monstruo, lo añadimos tanto a la matriz de mosntruos como al laberinto
     # Random
     # m1 = Monster.new("Uno", Dice.random_intelligence,Dice.random_strength)
     # m2 = Monster.new("Dos", Dice.random_intelligence,Dice.random_strength)
      
      # Valores que queremos
      m1 = Monster.new("Uno", 999,999)
      m2 = Monster.new("Dos", 0,0)
      @monsters << m1
      @monsters << m2

      @labyrinth.add_monster(4,5,m1)
      @labyrinth.add_monster(3,5,m2)

     
    end

    # Advances to the next player.
    def next_player
        @current_player_index = (@current_player_index + 1) % @players.size
        @current_player = @players[@current_player_index]
    end
    

    # Logs that the current player won a combat.
    def log_player_won
      @log += "The player won the fight \n"
      nil
    end

    # Logs that a monster won a combat.
    def log_monster_won
      @log += "The monster won the fight \n"
      nil
    end

    # Logs that the current player was resurrected.
    def log_resurrected
      @log += "The player has been brought back to life as fuzzy\n"
      nil
    end

    # Logs that the current player skipped their turn due to being dead.
    def log_player_skip_turn
      @log += "Player cannot move, they're dead \n"
      nil
    end

    # Logs that the current player did not follow human player instructions.
    def log_player_no_orders
      @log += "Player cannot go there\n"
      nil
    end

    # Logs that the current player moved to an empty cell or could not move.
    def log_no_monster
      @log += "Player didn't find a monster \n"
      nil
    end

    # Logs the number of rounds that have occurred.
    # @param rounds [Integer] the number of rounds that have occurred
    # @param max [Integer] the maximum number of rounds
    def  log_rounds(rounds,max)
      @log += "#{rounds} rounds of #{max} took place\n"
      nil
    end

    # Determines the actual direction based on the preferred direction.
    # @param preferred_direction [Symbol] the preferred direction to move
    def actual_direction(preferred_direction)
      valid_moves = @labyrinth.valid_moves(@current_player.row, @current_player.col)
      output = @current_player.move(preferred_direction, valid_moves)

      output
    end

    # Handles combat with a monster.
    # @param monster [Monster] the monster to fight
    def combat(monster)
      rounds = 0
      winner = GameCharacter::PLAYER

      player_attack = @current_player.attack
      lose = monster.defend(player_attack)

      while !lose && rounds < @@MAX_ROUNDS
        winner = GameCharacter::MONSTER
        rounds += 1 

        monster_attack = monster.attack
        lose = @current_player.defend(monster_attack)

        if !lose
          player_attack = @current_player.attack
          winner = GameCharacter::PLAYER
          lose = monster.defend(player_attack)
        end
      end

      log_rounds(rounds,@@MAX_ROUNDS)

      return winner
    end

    # Manages the reward for the winner of a combat.
    # @param winner [Player, Monster] the winner of the combat
    def manage_reward(winner)
      if winner == GameCharacter::PLAYER
        @current_player.receive_reward
        log_player_won
      else
        log_monster_won
      end
    end

    # Manages the resurrection of a player.
    def manage_resurrection

        if(Dice.resurrect_player)
                @current_player.resurrect
                log_resurrected
                fuzzy = FuzzyPlayer.new(@current_player)
                @players[@current_player_index] = fuzzy
                @labyrinth.convert_to_fuzzy(fuzzy)
            else
                log_player_skip_turn
            end
    end
      
  end

end
