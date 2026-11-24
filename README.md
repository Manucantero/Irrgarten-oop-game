# Irrgarten — Object-Oriented Dungeon Game (Java & Ruby)

Turn-based dungeon crawler game developed in **Java** and **Ruby** to apply Object-Oriented Programming (OOP) principles, software design patterns, and Model-View-Controller (MVC) architecture across two distinct paradigms (statically typed vs. dynamically typed).

## Game Overview

- **Grid-Based Labyrinth:** Players navigate a 2D labyrinth containing obstacles, weapons, shields, monsters, and an exit square.
- **Combat & Equipment System:** Dice-driven combat mechanics where players equip weapons and shields with limited uses and upgrade their stats upon defeating monsters.
- **Polymorphism & Inheritance:** Specialized player variants (such as *FuzzyPlayer*) and modular character behaviors implemented via inheritance and method overriding.
- **MVC Architecture:** Clear separation between the game logic (`irrgarten` model), user interface (`UI`), and game loop (`controller`).

## Repository Structure

- `/src`: Complete Java implementation organized into packages (`irrgarten`, `UI`, `controller`, `Main`).
- `/ruby`: Equivalent Ruby implementation mirroring the class hierarchy, mixins/modules, and MVC structure.

## Tech Stack

- **Languages:** Java (JDK 17+) & Ruby (3.0+)
- **Core Concepts:** Encapsulation, Inheritance, Polymorphism, MVC Pattern, UML-to-Code Design

## How to Run

1. Clone the repository:
   ```bash
   git clone [https://github.com/Manucantero/irrgarten-oop-game.git](https://github.com/Manucantero/irrgarten-oop-game.git)
   cd irrgarten-oop-game
   ```

2. **Run the Java version:**
   ```bash
   cd src
   javac $(find . -name "*.java")
   java Main.P5
   ```

3. **Run the Ruby version:**
   ```bash
   cd ruby
   ruby main.rb
   ```
   *(Note: Replace `main.rb` with the exact name of your main `.rb` entry file).*
