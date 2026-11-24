module Irrgarten
    class LabyrinthCharacter
        @@INVALID = -1
        
        attr_reader :row
        attr_reader :col
        attr_reader :name
        attr_reader :intelligence
        attr_reader :strength
        attr_accessor :health

        def initialize(name, intelligence, strength, health)
            @name = name
            @intelligence = intelligence
            @strength = strength
            @health = health
            @row = @@INVALID
            @col = @@INVALID
        end

        def copy_cons(other)
            @name = other.name
            @intelligence = other.intelligence
            @strength = other.strength
            @health = other.health          
            set_pos(other.row, other.col)
        end

        def dead
            return (@health <= 0)
        end

        def set_pos(row, col)
            @row = row
            @col = col
        end

        def to_s
      "#{@name}[S: #{'%.2f' % @strength}, I: #{'%.2f' % @intelligence}, H: #{'%.2f' % @health}]"
    end

        protected
        def got_wounded
            @health-=1
        end

    end
end
