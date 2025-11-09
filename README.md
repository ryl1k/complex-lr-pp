# Passenger Train Manager

A Java application for managing passenger train composition with wagon management, sorting, searching, and persistence features.

## Overview

This is a terminal-based train management system that allows users to:
- Create and configure passenger trains
- Add different types of wagons (simple, passenger with amenities, luxury wagons)
- Sort wagons by various criteria
- Search for wagons matching specific requirements
- Save and load train configurations to/from CSV and JSON files
- View statistics about the train composition

The application demonstrates key OOP concepts including inheritance, polymorphism, abstract classes, and the Command design pattern.

## Features

### Wagon Types

The system supports three types of wagons through an inheritance hierarchy:

1. **Simple Wagon** (PassengerWagon with no amenities)
   - ID, type, passenger count, luggage weight, comfort level (1-5)

2. **Passenger Wagon** (PassengerWagon with amenities)
   - All basic wagon properties
   - WiFi availability
   - Air conditioning

3. **Luxury Wagon** (LuxuryWagon extends PassengerWagon)
   - All passenger wagon properties
   - Restaurant availability
   - Bar availability
   - Price per seat

### Main Operations

- **Create Train**: Initialize a new train with a name and destination
- **Load Train**: Load train configuration from CSV file
- **Add Wagon**: Add wagons of different types to the train
- **Show Wagons**: Display all wagons in the current train
- **Edit Wagon**: Modify properties of existing wagons
- **Remove Wagon**: Remove a wagon from the train
- **Sort Wagons**: Sort by comfort level or passenger count
- **Find Wagons**: Search by passenger range, wagon type, or comfort level
- **Statistics**: View comprehensive train statistics
- **Save Train**: Save train configuration to CSV or JSON format

### File Formats

#### CSV Format
```
ID,Type,PassengerCount,LuggageWeight,ComfortLevel,WiFi,AirConditioning,Restaurant,Bar,PricePerSeat
1,Platzkart,54,120.5,3,true,true,false,false,0.0
2,Luxury,18,50.0,5,true,true,true,true,1500.0
```

#### JSON Format
```json
{
  "train": {
    "name": "Train Name",
    "destination": "Destination",
    "wagons": [
      {
        "id": 1,
        "type": "Platzkart",
        "passengerCount": 54,
        "luggageWeight": 120.5,
        "comfortLevel": 3,
        "hasWiFi": true,
        "hasAirConditioning": true
      }
    ]
  }
}
```

## Architecture

### Design Patterns

- **Command Pattern**: All menu operations implement the `Command` interface
- **Template Method**: `TrainCommand` abstract class provides common behavior
- **Strategy Pattern**: Different sorting and searching strategies

### Package Structure

```
src/
├── Main.java                      # Application entry point
├── cli/
│   ├── Command.java               # Command pattern interface
│   ├── Menu.java                  # Menu system with command execution
│   ├── InputValidator.java        # User input validation utilities
│   └── commands/
│       ├── TrainCommand.java      # Base class for train commands
│       ├── CreateTrainCommand.java
│       ├── LoadCommand.java
│       ├── AddWagonCommand.java
│       ├── ShowWagonsCommand.java
│       ├── EditWagonCommand.java
│       ├── RemoveWagonCommand.java
│       ├── SortCommand.java
│       ├── FindCommand.java
│       ├── StatisticsCommand.java
│       └── SaveCommand.java
├── model/
│   ├── Wagon.java                 # Abstract base wagon class
│   ├── PassengerWagon.java        # Standard passenger wagon
│   ├── LuxuryWagon.java           # Luxury wagon with restaurant/bar
│   └── Train.java                 # Train composition
└── service/
    ├── FileService.java           # CSV/JSON persistence (manual parsing)
    └── TrainService.java          # Business logic (sorting, searching, stats)

test/
├── model/
│   ├── WagonTest.java
│   ├── PassengerWagonTest.java
│   ├── LuxuryWagonTest.java
│   └── TrainTest.java
└── service/
    ├── FileServiceTest.java
    └── TrainServiceTest.java
```

## Quick Start

### For Windows PowerShell:

```powershell
# 1. Navigate to project directory
cd C:\Users\ryl1k\complex-lr-pp

# 2. Compile the application
javac -encoding UTF-8 -d out src\Main.java src\cli\*.java src\cli\commands\*.java src\model\*.java src\service\*.java

# 3. Run the application
java -cp out Main
```

### For Windows CMD:

```cmd
# 1. Navigate to project directory
cd C:\Users\ryl1k\complex-lr-pp

# 2. Compile the application
javac -encoding UTF-8 -d out src\Main.java src\cli\*.java src\cli\commands\*.java src\model\*.java src\service\*.java

# 3. Run the application
java -cp out Main
```

### For Linux/Mac/Git Bash:

```bash
# 1. Navigate to project directory
cd /path/to/complex-lr-pp

# 2. Compile the application
javac -encoding UTF-8 -d out src/**/*.java src/**/**/*.java

# 3. Run the application
java -cp out Main
```

Once running, type `help` to see available commands!

## Requirements

- **Java**: JDK 8 or higher
- **JUnit 4**: For running tests (optional, required only for testing)
- **Hamcrest**: For test assertions (optional, required only for testing)

No build tools (Maven/Gradle) are required - this is a simple Java project compilable with `javac`.

## Installation & Setup

1. **Clone or download** the project to your local machine

2. **Ensure Java is installed**:
   ```bash
   java -version
   ```

3. **No additional dependencies** required for running the application
   - For testing, place JUnit and Hamcrest JARs in `lib/` directory

## Compilation

### Compile the main application:

```powershell
# Windows PowerShell / CMD
javac -encoding UTF-8 -d out src\Main.java src\cli\*.java src\cli\commands\*.java src\model\*.java src\service\*.java
```

```bash
# Linux/Mac/Git Bash
javac -encoding UTF-8 -d out src/**/*.java src/**/**/*.java
```

The `-encoding UTF-8` flag ensures proper character encoding.

### Compile tests (optional):

```powershell
# Windows PowerShell / CMD
javac -encoding UTF-8 -d out -cp "out;lib/*" test\model\*.java test\service\*.java
```

```bash
# Linux/Mac/Git Bash
javac -encoding UTF-8 -d out -cp "out:lib/*" test/model/*.java test/service/*.java
```

## Running the Application

### Start the program:

```bash
# All platforms
java -cp out Main
```

### Run tests:

```powershell
# Windows PowerShell / CMD - Run a specific test class
java -cp "out;lib/*" org.junit.runner.JUnitCore model.WagonTest

# Windows PowerShell / CMD - Run all tests
java -cp "out;lib/*" org.junit.runner.JUnitCore model.WagonTest model.PassengerWagonTest model.LuxuryWagonTest model.TrainTest service.FileServiceTest service.TrainServiceTest
```

```bash
# Linux/Mac/Git Bash - Run a specific test class
java -cp "out:lib/*" org.junit.runner.JUnitCore model.WagonTest

# Linux/Mac/Git Bash - Run all tests
java -cp "out:lib/*" org.junit.runner.JUnitCore model.WagonTest model.PassengerWagonTest model.LuxuryWagonTest model.TrainTest service.FileServiceTest service.TrainServiceTest
```

## Usage Guide

### Basic Workflow

1. **Start the application**
2. **Create a new train** or **load an existing one** from a file
3. **Add wagons** to your train composition
4. **Manage wagons** - view, edit, remove, sort, search
5. **View statistics** about your train
6. **Save your train** to a file (CSV or JSON)

### Menu Navigation

The application uses a command-based menu system:

- Type a command name (e.g., `create`, `add`, `show`) and press Enter
- Type `help` to see available commands
- Type `exit` to return to the previous menu or quit

### Input Validation

All user inputs are validated automatically:
- Numeric inputs: must be valid numbers
- Positive numbers: must be > 0
- Ranges: values must be within specified min/max
- Non-empty strings: cannot be blank
- Booleans: accepts `true/false`, `t/f`, `yes/no`, `y/n`, `1/0`

Invalid input will show an error message and prompt again.

### Example Session

```
====================================
   PASSENGER TRAIN MANAGER
====================================

====================================
            MAIN MENU
====================================
Available commands:
  load            - Load train
  create          - Create new train
  show            - Show wagons
  add             - Add wagon
  edit            - Edit wagon
  remove          - Remove wagon
  stats           - Show statistics
  sort            - Sort wagons
  find            - Find wagons
  save            - Save train
  help            - Show this help
  exit            - Exit menu

Enter command: create
Enter train name: Kyiv Express
Enter destination: Lviv
New train 'Kyiv Express' created!

Enter command: add
[Select wagon type from submenu...]

Enter command: stats
=== Train statistics: Kyiv Express ===
Total passengers: 54
Total luggage: 120.5 kg
...

Enter command: save
[Select CSV or JSON format...]

Enter command: exit
```

## Logging

The application uses `java.util.logging` to log important events:

- **Log file**: `train.log` (created in the current directory)
- **Console output**: Also displays log messages during execution
- **Configuration**: `logging.properties` file

### Log Levels
- `INFO`: Normal operations (train creation, file operations, searches)
- `WARNING`: Non-critical issues (empty trains, file not found)
- `SEVERE`: Critical errors (file I/O failures, parsing errors)

### Logging Configuration

Edit `logging.properties` to customize:
```properties
.level=INFO
handlers=java.util.logging.FileHandler, java.util.logging.ConsoleHandler

java.util.logging.FileHandler.pattern=train.log
java.util.logging.FileHandler.limit=50000
java.util.logging.FileHandler.count=1
java.util.logging.FileHandler.formatter=java.util.logging.SimpleFormatter

java.util.logging.ConsoleHandler.level=INFO
java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter
```

## Testing

The project includes comprehensive unit tests with 80%+ code coverage:

### Test Coverage

- **Model Tests**:
  - `WagonTest.java` - Abstract wagon behavior
  - `PassengerWagonTest.java` - Passenger wagon functionality
  - `LuxuryWagonTest.java` - Luxury wagon features
  - `TrainTest.java` - Train operations and aggregations

- **Service Tests**:
  - `FileServiceTest.java` - CSV/JSON save and load operations
  - `TrainServiceTest.java` - Sorting, searching, and statistics

### Running Tests

See the "Running the Application" section above for test execution commands.

## Key Implementation Details

### JSON Parsing

The application implements **manual JSON parsing** without external libraries:
- Custom parsing methods extract values by key
- Brace counting algorithm splits wagon array
- Type detection based on field presence

### Abstract Wagon Class

The `Wagon` class is abstract to enforce type safety:
- Cannot instantiate generic wagons
- Must use specific types: `PassengerWagon` or `LuxuryWagon`
- Better polymorphism and OOP design

### Input Validation

The `InputValidator` utility class prevents crashes:
- Retry loops for invalid input
- Type-safe conversions with exception handling
- Range validation for bounded values

## Project History

This project was developed as a series of university laboratory assignments:

- **LR-4**: UML diagrams and design
- **LR-5**: Command pattern implementation
- **LR-6**: Core functionality and JSON parsing
- **LR-7**: Unit testing (JUnit 4)
- **LR-8**: Logging with java.util.logging

## Code Quality

- ✅ Clean, readable code with English identifiers and comments
- ✅ Natural single-line comments (//) where they add value
- ✅ Clean, readable code structure without emojis
- ✅ Proper OOP principles (abstraction, inheritance, polymorphism)
- ✅ Comprehensive error handling
- ✅ Input validation on all user input
- ✅ 68 passing unit tests with comprehensive coverage

## Troubleshooting

### Compilation Issues

**Problem**: Classes not found during compilation
**Solution**: Make sure you're compiling all source files with the correct paths

**Problem**: `package does not exist` errors
**Solution**: Compile all files at once using the commands shown in the Compilation section

### Runtime Issues

**Problem**: `NoClassDefFoundError`
**Solution**: Make sure you compiled before running, and use `-cp out` when running

**Problem**: Test failures
**Solution**: Ensure JUnit 4 and Hamcrest JARs are in the `lib/` directory

### File Operations

**Problem**: File not found when loading
**Solution**: Check the file exists in the current directory or provide full path

**Problem**: Invalid JSON format
**Solution**: The manual parser expects exact format - use the save function to generate valid JSON

## Future Enhancements

Potential improvements for this project:

- Add GUI using JavaFX or Swing
- Support for more file formats (XML, binary)
- Database integration for persistence
- Multi-train management
- Wagon booking/reservation system
- Route planning functionality
- Migration to modern build tools (Maven/Gradle)
- Use external JSON library (Jackson, Gson)

## License

This is an educational project created for university coursework. Free to use and modify for learning purposes.

## Author

Created as part of university programming coursework for learning Java OOP, design patterns, file I/O, and testing.
