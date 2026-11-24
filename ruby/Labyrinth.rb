#encoding:utf-8

# The Irrgarten module contains classes and methods related to the labyrinth game.
module Irrgarten
  # The Labyrinth class represents a labyrinth game with monsters, players, and an exit.
  class Labyrinth
    # Class variables representing different characters in the labyrinth.
    @@BLOCK_CHAR = 'X' # Character representing a block in the labyrinth.
    @@EMPTY_CHAR = '-' # Character representing an empty space in the labyrinth.
    @@MONSTER_CHAR = 'M' # Character representing a monster in the labyrinth.
    @@COMBAT_CHAR = 'C' # Character representing a combat position in the labyrinth.
    @@EXIT_CHAR = 'E' # Character representing the exit of the labyrinth.
    @@INVALID = -1 # Initial pos

    #Estos dos son para matrices (dir2pos y random_empty_pos)
    @@ROW = 0
    @@COL = 1
    
    # Initializes a new Labyrinth object.
    # @param n_rows [Integer] Number of rows in the labyrinth.
    # @param n_cols [Integer] Number of columns in the labyrinth.
    # @param exit_row [Integer] Row index of the exit.
    # @param exit_col [Integer] Column index of the exit.
    def initialize(n_rows, n_cols, exit_row, exit_col)
      @n_rows = n_rows # Number of rows in the labyrinth.
      @n_cols = n_cols # Number of columns in the labyrinth.
      @exit_row = exit_row # Row index of the exit.
      @exit_col = exit_col # Column index of the exit.

      @monster_square = Array.new(n_rows) { Array.new(n_cols)} # 2D array to store monsters.
      @player_square = Array.new(n_rows) { Array.new(n_cols)} # 2D array to store players.
      @labyrinth_square = Array.new(n_rows) { Array.new(n_cols,@@EMPTY_CHAR)} # 2D array representing the labyrinth.

      (1...n_rows).each do |i|
        (1...n_cols).each do |j|
          @labyrinth_square[i][j] = @@EMPTY_CHAR
        end
      end

      @labyrinth_square[exit_row][exit_col] = @@EXIT_CHAR
    end

    # Checks if there is a winner (i.e., a player has reached the exit).
    # @return [Boolean] True if a player is at the exit position, false otherwise.
    def have_a_winner
      @player_square[@exit_row][@exit_col] != nil 
    end

    # Converts the labyrinth to a string representation.
    # @return [String] String representation of the labyrinth.
    def to_s
      output_string = "" 
      (0...@n_rows).each do |r|
        (0...@n_cols).each do |c|
          output_string << @labyrinth_square[r][c].to_s
          output_string << " "
        end
        output_string << "\n"
      end
      output_string
    end

    # Adds a monster to the labyrinth at the specified position.
    # @param row [Integer] Row index to place the monster.
    # @param col [Integer] Column index to place the monster.
    # @param monster [Object] The monster object to be placed.
    def add_monster(row, col, monster)
      if pos_ok(row,col) && empty_pos(row,col)
        @labyrinth_square[row][col] = @@MONSTER_CHAR
        @monster_square[row][col] = monster
        monster.set_pos(row,col)
      end
      nil
    end 
    
    # Spreads players randomly in the labyrinth.
    # @param players [Array<Object>] Array of player objects to be spread.
    def spread_players(players)
      old_col = @@INVALID
      old_row = @@INVALID

      pos0 = [2,2]
      
      put_player_2d(old_row, old_col, pos0[@@ROW], pos0[@@COL], players[0])
    
    end

    # Puts a player in the labyrinth based on the given direction.
    # @param direction [String] Direction to place the player.
    # @param player [Object] The player object to be placed.
    def put_player(direction, player)
      old_row = player.row
      old_col = player.col
      new_pos = dir_2_pos(old_row, old_col, direction)

      put_player_2d(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player) #returns Monster
    end

    # Adds a block of a given length in the labyrinth.
    # @param orientation [String] Orientation of the block ("HORIZONTAL" or "VERTICAL").
    # @param start_row [Integer] Starting row index of the block.
    # @param start_col [Integer] Starting column index of the block.
    # @param length [Integer] Length of the block.
    def add_block(orientation, start_row, start_col, length)
      if orientation == Irrgarten::Orientation::VERTICAL
        inc_row = 1;
        inc_col = 0;
      else
        inc_row = 0;
        inc_col = 1;
      end

      row = start_row
      col = start_col

      while pos_ok(row,col) && empty_pos(row,col) && length.positive?
        @labyrinth_square[row][col] = @@BLOCK_CHAR
        length -= 1
        row += inc_row
        col += inc_col
      end
    end

    # Finds valid moves from a given position.
    # @param row [Integer] Current row index.
    # @param col [Integer] Current column index.
    # @return [Array<Array<Integer>>] Array of valid moves as [row, col].
    def valid_moves(row,col)
      output = []

      output << Irrgarten::Directions::DOWN if can_step_on(row+1,col)
      output << Irrgarten::Directions::UP if can_step_on(row-1,col)
      output << Irrgarten::Directions::RIGHT if can_step_on(row,col+1)
      output << Irrgarten::Directions::LEFT if can_step_on(row,col-1)

      output
    end

    def convert_to_fuzzy(other)
        row = other.row
        col = other.col

        if(@player_square[row][col].number == other.number)
            @player_square[row][col] = other
        end

    end
    private

    # Checks if the given position is within the bounds of the labyrinth.
    # @param row [Integer] Row index to check.
    # @param col [Integer] Column index to check.
    # @return [Boolean] True if the position is valid, false otherwise.
    def pos_ok(row,col)
      row >= 0 && col >= 0 && row < @n_rows && col < @n_cols
    end

    # Checks if the given position is empty.
    # @param row [Integer] Row index to check.
    # @param col [Integer] Column index to check.
    # @return [Boolean] True if the position is empty, false otherwise.
    def empty_pos(row,col)
      @labyrinth_square[row][col] == @@EMPTY_CHAR
    end 

    # Checks if the given position contains a monster.
    # @param row [Integer] Row index to check.
    # @param col [Integer] Column index to check.
    # @return [Boolean] True if the position contains a monster, false otherwise.
    def monster_pos(row,col) 
      @labyrinth_square[row][col] == @@MONSTER_CHAR 
    end

    # Checks if the given position is the exit.
    # @param row [Integer] Row index to check.
    # @param col [Integer] Column index to check.
    # @return [Boolean] True if the position is the exit, false otherwise.
    def exit_pos(row,col)
      @labyrinth_square[row][col] == @@EXIT_CHAR
    end

    # Checks if the given position is a combat position.
    # @param row [Integer] Row index to check.
    # @param col [Integer] Column index to check.
    # @return [Boolean] True if the position is a combat position, false otherwise.
    def combat_pos(row,col)
      @labyrinth_square[row][col] == @@COMBAT_CHAR
    end

    # Checks if a player can step on the given position.
    # @param row [Integer] Row index to check.
    # @param col [Integer] Column index to check.
    # @return [Boolean] True if the player can step on the position, false otherwise.
    def can_step_on(row,col)
      pos_ok(row,col) && (empty_pos(row,col) || monster_pos(row,col) || exit_pos(row,col))
    end

    # Updates the old position of a player or monster.
    # @param row [Integer] Row index to update.
    # @param col [Integer] Column index to update.
    def update_old_pos(row, col)
      if pos_ok(row, col)
        if combat_pos(row, col)
          @labyrinth_square[row][col] = @@MONSTER_CHAR
        else
          @labyrinth_square[row][col] = @@EMPTY_CHAR
        end
      end
    end
    

    # Converts a direction to a new position.
    # @param row [Integer] Current row index.
    # @param col [Integer] Current column index.
    # @param direction [String] Direction to move ("UP", "DOWN", "LEFT", "RIGHT").
    # @return [Array<Integer>] New position as [row, col].
    def dir_2_pos(row, col, direction)
      case direction
      when :up
        row -= 1
      when :down
        row += 1
      when :left
        col -= 1
      when :right
        col += 1
      end
      [row, col]
    end
    

    # Finds a random empty position in the labyrinth.
    # @return [Array<Integer>] Random empty position as [row, col].
    def random_empty_pos
      row = Dice.random_pos(@n_rows)
      col = Dice.random_pos(@n_cols)

      while !empty_pos(row,col)
        row = Dice.random_pos(@n_rows)
        col = Dice.random_pos(@n_cols)
      end
      
      [row,col]
    end

    # Puts a player in a 2D array.
    def put_player_2d(old_row, old_col, row, col, player)
      output = nil

      if can_step_on(row,col)
        if pos_ok(old_row,old_col)
          p = @player_square[old_row][old_col]
          if p == player
            update_old_pos(old_row,old_col)
            @player_square[old_row][old_col] = nil
          end
        end

        monster_pos = monster_pos(row,col)

        if monster_pos == true
          @labyrinth_square[row][col] = @@COMBAT_CHAR
          output = @monster_square[row][col]
        else
          number = player.number
          @labyrinth_square[row][col] = number
        end

        @player_square[row][col] = player
        player.set_pos(row,col)
      end
      output
    end
  end
end
