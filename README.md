# D&D Character Sheet Manager

A Java-based command-line application for creating, managing, and storing Dungeons & Dragons 5th Edition character sheets.

## Features

- **Interactive Character Creation**: Step-by-step CLI interface for creating new D&D characters
- **Character Attributes**: Support for all core D&D 5e attributes including:
    - Races (Human, Elf, Dwarf, Halfling, Dragonborn, Gnome, Half-Elf, Half-Orc, Orc, Tiefling)
    - Classes (Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, Rogue, Sorcerer, Warlock, Wizard)
    - Backgrounds (Acolyte, Charlatan, Criminal, Entertainer, Folk Hero, Guild Artisan, Hermit, Noble, Outlander, Sage, Sailor, Soldier, Urchin)
    - Alignments (All nine D&D alignments)
- **Ability Score Generation**: Automated dice rolling using the standard 4d6 drop-lowest method
- **Character Progression**: Level tracking with experience points and automatic level-up mechanics
- **Combat Statistics**: Automatic calculation of:
    - Hit points and hit dice
    - Armor class
    - Initiative
    - Saving throws
    - Skill modifiers
- **Inventory Management**: Track weapons, armor, adventure gear, instruments, and miscellaneous items
- **Currency System**: Manage character wealth across all coin types (copper, silver, electrum, gold, platinum)
- **Save/Load System**: Persistent character storage using JSON format
- **Interactive Terminal UI**: Navigate menus using arrow keys for a smooth user experience

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Installation

### Option 1: Download Pre-built Release

1. Go to the [Releases](https://github.com/Astronomy20/CharacterSheet/releases) page
2. Download the latest `CharacterSheet.jar` file
3. Run the application:
```bash
java -jar CharacterSheet.jar
```

### Option 2: Build from Source

1. Clone the repository:
```bash
git clone <repository-url>
cd CharacterSheet
```
2. Build the project:
```bash
mvn clean package
```

3. Run the application:
```bash
java -jar target/CharacterSheet.jar
```

## Usage

### Interactive Mode

Run the main application to access the interactive character creator:

```bash
java -jar target/CharacterSheet.jar
```

**Main Menu Options:**
- **Create New Character**: Step through the character creation process
- **Load Saved Character**: Select and view a previously saved character

### Character Creation Flow

1. Enter a character name
2. Select race using arrow keys and Enter
3. Select class
4. Select background
5. Select alignment
6. Ability scores are automatically rolled
7. Character is saved and displayed

## Project Structure

```
src/main/java/net/astronomy/dnd/
├── model/
│   ├── Ability.java           # Character ability scores
│   ├── Character.java         # Main character class
│   ├── Currency.java          # Money management
│   ├── Inventory.java         # Equipment tracking
│   ├── Level.java            # Experience and leveling
│   ├── Life.java             # HP, AC, initiative, speed
│   ├── Modifier.java         # Ability modifiers
│   ├── SavingThrow.java      # Saving throw calculations
│   ├── Skills.java           # Skill modifiers
│   └── enums/
│       ├── attributes/        # Race, Class, Background, etc.
│       └── equipment/         # Weapons, Armor, Gear, etc.
├── ui/
│   ├── CliSelector.java          # Base CLI selection system
│   ├── CliAttributesSelector.java # Attribute selection UI
│   └── CliSessionSelector.java    # Save/load UI
├── util/
│   ├── CharacterPrinter.java  # Character sheet display
│   ├── Session.java           # Save/load functionality
│   └── dice/
│       ├── DiceRoll.java      # Dice rolling mechanics
│       └── Dices.java         # Dice types and utilities
└── CharacterSheetTerminalWithUI.java  # Main application entry point
```

## Game Mechanics

### Ability Score Generation
Characters' ability scores are generated using the standard D&D 5e method:
- Roll 4d6 for each ability
- Drop the lowest die
- Sum the remaining three dice
- Repeat six times for all abilities (STR, DEX, CON, INT, WIS, CHA)

### Racial Bonuses
Each race provides specific ability score bonuses that are automatically applied during character creation.

### Class Features
- Each class has specific proficiency bonuses for saving throws
- Hit dice vary by class (d6 for Wizard/Sorcerer, d8 for most classes, d10 for martial classes, d12 for Barbarian)
- Proficiency bonus scales with level (+2 at levels 1-4, +3 at 5-8, +4 at 9-12, etc.)

### Leveling Up
- Experience points are tracked automatically
- Characters level up when reaching XP thresholds
- Hit points increase on level up based on class hit dice and Constitution modifier

### Armor Class Calculation
AC is calculated as: `10 + Dexterity modifier + armor bonus + shield bonus`
- Light armor: Full Dex modifier
- Medium armor: Dex modifier capped at +2
- Heavy armor: No Dex modifier

## Character Save Files

Characters are saved as JSON files in the `saves/` directory. Each file is named after the character (with special characters sanitized).

Example save file location:
```
saves/Thorin_Ironforge.json
```

## Dependencies

- **Gson 2.10.1**: JSON serialization/deserialization
- **JLine 3.25.1**: Enhanced terminal functionality for interactive CLI

## Development

### Building from Source

```bash
# Compile
mvn compile

# Package
mvn package

# Create executable JAR with dependencies
mvn clean package
```

### Running Without Building

```bash
mvn exec:java -Dexec.mainClass="net.astronomy.dnd.CharacterSheetTerminalWithUI"
```

## Future Enhancements

Potential features for future development:
- Spell management for spellcasting classes
- Equipment weight and encumbrance tracking
- Character class features and abilities
- Multi-classing support
- Custom race/class creation
- Party management
- Combat encounter simulator
- Character export to PDF
- Web-based UI

## Acknowledgments

Built for Dungeons & Dragons 5th Edition. D&D and all related properties are owned by Wizards of the Coast.