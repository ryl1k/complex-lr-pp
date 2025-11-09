# Complete Program Functionality Guide

## Table of Contents
1. [Program Overview](#program-overview)
2. [Menu System](#menu-system)
3. [Train Management](#train-management)
4. [Wagon Management](#wagon-management)
5. [Data Operations](#data-operations)
6. [Search and Filter](#search-and-filter)
7. [Statistics](#statistics)
8. [File Operations](#file-operations)
9. [Input Validation](#input-validation)
10. [Logging System](#logging-system)

---

## Program Overview

**Passenger Train Manager** is a command-line application for managing passenger train compositions. It allows users to create trains, add different types of wagons, perform various operations (sorting, searching, statistics), and save/load train configurations.

### Key Features
- Interactive menu-based interface
- Three types of wagons (Simple, Passenger with amenities, Luxury)
- Comprehensive input validation
- File persistence (CSV and JSON formats)
- Manual JSON parsing (no external libraries)
- Detailed statistics and reporting
- Comprehensive logging
- Full test coverage (68 unit tests)

---

## Menu System

### Main Menu Commands

When you start the program, you see the following menu:

```
╔════════════════════════════════════╗
║   PASSENGER TRAIN MANAGER          ║
╚════════════════════════════════════╝

╔════════════════════════════════════╗
║          MAIN MENU                 ║
╚════════════════════════════════════╝
Available commands:
  load            - Load train
  create          - Create new train
  show            - Show all wagons
  add             - Add wagon
  edit            - Edit wagon
  remove          - Remove wagon
  stats           - Show statistics
  sort            - Sort wagons
  find            - Find wagons
  save            - Save train
  help            - Show this help
  exit            - Exit menu
```

### Navigation
- Type a command name and press Enter
- Type `help` to see the command list
- Type `exit` to quit or go back to previous menu
- All submenus work the same way

---

## Train Management

### Create New Train

**Command:** `create`

Creates a new train from scratch, clearing any existing wagons.

**Process:**
1. Enter train name
2. Enter destination
3. Confirmation message appears

**Example:**
```
Enter command: create
Enter train name: Express Kyiv
Enter destination: Lviv
✓ New train 'Express Kyiv' created!
```

**Use Case:** Starting a new train configuration or resetting the current one.

---

### Load Existing Train

**Command:** `load`

Loads a previously saved train from a CSV file.

**Process:**
1. Enter filename (without .csv extension)
2. Enter train name (used if file doesn't contain name)
3. Enter destination (used if file doesn't contain destination)
4. System loads all wagons from the file

**Example:**
```
Enter command: load
Enter filename (without extension): mytrain
Enter train name: Intercity
Enter destination: Odesa
✓ Train loaded!
```

**Use Case:** Continuing work on a previously saved train configuration.

**Note:** The file must exist in the current directory. If not found, an error message appears.

---

## Wagon Management

### Wagon Types

The system supports three types of wagons:

#### 1. Simple Wagon
- **Basic properties:** ID, type, passenger count, luggage weight, comfort level
- **Amenities:** None
- **Example:** Basic platzkart wagon without WiFi or AC

#### 2. Passenger Wagon
- **Basic properties:** All simple wagon properties
- **Additional amenities:** WiFi, Air Conditioning
- **Example:** Modern coupe wagon with WiFi and AC

#### 3. Luxury Wagon
- **Basic properties:** All passenger wagon properties
- **Additional amenities:** Restaurant, Bar, Price per seat
- **Example:** First-class SV wagon with restaurant and bar

### Add Wagon

**Command:** `add`

Opens a submenu to select wagon type and add it to the train.

**Submenu Options:**
```
1 - Simple wagon
2 - Passenger wagon (WiFi, AC)
3 - Luxury wagon (restaurant, bar)
```

#### Adding Simple Wagon

**Process:**
1. Select option `1`
2. Enter wagon ID (positive integer)
3. Enter wagon type (e.g., "Platzkart")
4. Enter passenger count (positive integer)
5. Enter luggage weight in kg (positive decimal)
6. Enter comfort level (1-5)

**Example:**
```
Enter command: add
Enter command: 1
Enter wagon ID: 1
Enter wagon type: Platzkart
Enter passenger count: 54
Enter luggage weight (kg): 120.5
Enter comfort level (1-5): 3
✓ Simple wagon added!
```

#### Adding Passenger Wagon

**Process:**
1. Select option `2`
2. Enter all simple wagon fields
3. Has WiFi? (true/false or yes/no)
4. Has air conditioning? (true/false or yes/no)

**Example:**
```
Enter command: add
Enter command: 2
Enter wagon ID: 2
Enter wagon type: Coupe
Enter passenger count: 36
Enter luggage weight (kg): 80.0
Enter comfort level (1-5): 4
Has WiFi? (true/false): yes
Has air conditioning? (true/false): true
✓ Passenger wagon added!
```

#### Adding Luxury Wagon

**Process:**
1. Select option `3`
2. Enter all passenger wagon fields
3. Has restaurant? (true/false or yes/no)
4. Has bar? (true/false or yes/no)
5. Enter price per seat (positive decimal)

**Example:**
```
Enter command: add
Enter command: 3
Enter wagon ID: 3
Enter wagon type: SV
Enter passenger count: 18
Enter luggage weight (kg): 40.0
Enter comfort level (1-5): 5
Has WiFi? (true/false): true
Has air conditioning? (true/false): true
Has restaurant? (true/false): yes
Has bar? (true/false): yes
Enter price per seat: 1500.0
✓ Luxury wagon added!
```

**Use Cases:**
- Building a train composition from scratch
- Adding more wagons to an existing train
- Creating diverse train configurations

---

### Edit Wagon

**Command:** `edit`

Modifies properties of an existing wagon.

**Process:**
1. Enter wagon ID to edit
2. If wagon exists, submenu appears with edit options
3. Select property to edit

**Edit Submenu:**
```
1 - Change wagon type
2 - Change passenger count
3 - Change luggage weight
4 - Change comfort level
```

**Example:**
```
Enter command: edit
Enter wagon ID to edit: 2
╔════════════════════════════════════╗
║      Editing wagon ID=2            ║
╚════════════════════════════════════╝
Enter command: 2
Enter new passenger count: 40
✓ Passenger count changed to: 40
```

**Use Cases:**
- Correcting data entry mistakes
- Updating wagon specifications
- Adjusting capacity or comfort levels

**Note:** Can only edit wagons that exist. Shows error message if ID not found.

---

### Remove Wagon

**Command:** `remove`

Deletes a wagon from the train by ID.

**Process:**
1. Enter wagon ID to remove
2. If wagon exists, it's removed
3. Confirmation message appears

**Example:**
```
Enter command: remove
Enter wagon ID to remove: 5
✓ Wagon removed!
```

**Error Case:**
```
Enter command: remove
Enter wagon ID to remove: 999
✗ Wagon with ID 999 not found!
```

**Use Cases:**
- Removing incorrect wagons
- Reducing train composition
- Restructuring train layout

---

### Show All Wagons

**Command:** `show`

Displays complete information about all wagons in the train.

**Output Format:**
```
=== Train composition: Express Kyiv ===
Destination: Lviv
Wagon count: 3
-----------------------------------------
PassengerWagon{id=1, type='Platzkart', passengerCount=54, luggageWeight=120.0, comfortLevel=3, hasWiFi=false, hasAirConditioning=false}
PassengerWagon{id=2, type='Coupe', passengerCount=36, luggageWeight=80.0, comfortLevel=4, hasWiFi=true, hasAirConditioning=true}
LuxuryWagon{id=3, type='SV', passengerCount=18, luggageWeight=40.0, comfortLevel=5, hasWiFi=true, hasAirConditioning=true, hasRestaurant=true, hasBar=true, pricePerSeat=1500.0}
-----------------------------------------
```

**If Train is Empty:**
```
No wagons in the train!
```

**Use Cases:**
- Reviewing current train composition
- Verifying wagon details
- Checking train structure before saving

---

## Data Operations

### Sort Wagons

**Command:** `sort`

Sorts wagons by different criteria.

**Submenu Options:**
```
1 - By comfort level
2 - By passenger count
```

#### Sort by Comfort Level

**Process:**
1. Select option `1`
2. Wagons are sorted in **descending order** (highest comfort first)

**Example:**
```
Enter command: sort
Enter command: 1
✓ Wagons sorted!
```

**Result:** Comfort level 5 wagons appear first, then 4, 3, 2, 1

#### Sort by Passenger Count

**Process:**
1. Select option `2`
2. Wagons are sorted in **descending order** (most passengers first)

**Example:**
```
Enter command: sort
Enter command: 2
✓ Wagons sorted!
```

**Result:** Wagon with 54 passengers appears first, then 36, then 18, etc.

**Use Cases:**
- Organizing wagons for better visualization
- Finding highest/lowest capacity wagons
- Preparing train composition reports

**Note:** Sorting modifies the wagon order in the train. Use `show` to see the new order.

---

## Search and Filter

### Find Wagons

**Command:** `find`

Searches for wagons matching specific criteria.

**Submenu Options:**
```
1 - By passenger range
2 - By wagon type
3 - By comfort level
```

#### Find by Passenger Range

**Process:**
1. Select option `1`
2. Enter minimum passenger count
3. Enter maximum passenger count
4. System displays all wagons within that range

**Example:**
```
Enter command: find
Enter command: 1
Enter minimum passengers: 30
Enter maximum passengers: 50
✓ Found 2 wagon(s):
  PassengerWagon{id=2, type='Coupe', passengerCount=36, ...}
  PassengerWagon{id=4, type='Coupe', passengerCount=40, ...}
```

**No Results:**
```
✗ No wagons found!
```

**Use Cases:**
- Finding wagons with specific capacity
- Identifying wagons that can accommodate certain group sizes
- Filtering by passenger density

#### Find by Wagon Type

**Process:**
1. Select option `2`
2. Enter wagon type (case-insensitive search)
3. System displays all wagons of that type

**Example:**
```
Enter command: find
Enter command: 2
Enter wagon type: Coupe
✓ Found 3 wagon(s):
  PassengerWagon{id=2, type='Coupe', passengerCount=36, ...}
  PassengerWagon{id=4, type='Coupe', passengerCount=40, ...}
  PassengerWagon{id=7, type='Coupe', passengerCount=32, ...}
```

**Use Cases:**
- Finding all wagons of a specific type
- Counting how many Platzkart/Coupe/SV wagons exist
- Analyzing train composition by type

#### Find by Comfort Level

**Process:**
1. Select option `3`
2. Enter comfort level (1-5)
3. System displays all wagons with that exact comfort level

**Example:**
```
Enter command: find
Enter command: 3
Enter comfort level (1-5): 5
✓ Found 1 wagon(s):
  LuxuryWagon{id=3, type='SV', passengerCount=18, ...}
```

**Use Cases:**
- Finding premium wagons (comfort 5)
- Identifying basic wagons (comfort 1-2)
- Analyzing comfort distribution

---

## Statistics

### Show Statistics

**Command:** `stats`

Displays comprehensive statistics about the current train.

**Output Includes:**
- Total passenger capacity
- Total luggage weight
- Wagon count
- Average passengers per wagon
- Average comfort level
- Most comfortable wagon
- Wagon with most passengers

**Example Output:**
```
=== Train statistics: Express Kyiv ===
Total passengers: 108
Total luggage weight: 240.0 kg
Wagon count: 3
Average passengers per wagon: 36.00
Average comfort level: 4.00
Most comfortable wagon: ID=3 (comfort=5)
Wagon with most passengers: ID=1 (54 passengers)
====================================
```

**Empty Train:**
```
Train is empty!
```

**Use Cases:**
- Quick overview of train capacity
- Understanding train composition
- Reporting train statistics
- Planning decisions

**Calculations:**
- **Total passengers:** Sum of all wagon passenger counts
- **Total luggage:** Sum of all wagon luggage weights
- **Average passengers:** Total passengers / Wagon count
- **Average comfort:** Mean of all comfort levels
- **Most comfortable:** Wagon with highest comfort level
- **Most passengers:** Wagon with highest passenger count

---

## File Operations

### Save Train

**Command:** `save`

Saves the current train configuration to a file.

**Submenu Options:**
```
1 - Save to CSV
2 - Save to JSON
```

#### Save to CSV

**Process:**
1. Select option `1`
2. File is saved as `train_data.csv` in current directory

**CSV Format:**
```
ID,Type,PassengerCount,LuggageWeight,ComfortLevel,WiFi,AirConditioning,Restaurant,Bar,PricePerSeat
1,Platzkart,54,120.0,3,false,false,false,false,0.0
2,Coupe,36,80.0,4,true,true,false,false,0.0
3,SV,18,40.0,5,true,true,true,true,1500.0
```

**Features:**
- Header row with column names
- One wagon per line
- All wagon types use same format (unused fields set to default values)
- Compatible with Excel and spreadsheet applications

**Example:**
```
Enter command: save
Enter command: 1
Train saved to file: train_data.csv
✓ Train saved to CSV!
```

#### Save to JSON

**Process:**
1. Select option `2`
2. File is saved as `train_data.json` in current directory

**JSON Format:**
```json
{
  "train": {
    "name": "Express Kyiv",
    "destination": "Lviv",
    "wagons": [
      {
        "id": 1,
        "type": "Platzkart",
        "passengerCount": 54,
        "luggageWeight": 120.0,
        "comfortLevel": 3,
        "hasWiFi": false,
        "hasAirConditioning": false
      },
      {
        "id": 3,
        "type": "SV",
        "passengerCount": 18,
        "luggageWeight": 40.0,
        "comfortLevel": 5,
        "hasWiFi": true,
        "hasAirConditioning": true,
        "hasRestaurant": true,
        "hasBar": true,
        "pricePerSeat": 1500.0
      }
    ]
  }
}
```

**Features:**
- Human-readable format
- Preserves train name and destination
- Only includes relevant fields for each wagon type
- Indented for readability
- Manual parsing (no external JSON libraries)

**Example:**
```
Enter command: save
Enter command: 2
Train saved to JSON file: train_data.json
✓ Train saved to JSON!
```

**Use Cases:**
- Backing up train configurations
- Sharing train designs
- Version control
- Data export for analysis

**Notes:**
- CSV: Good for spreadsheet analysis, simple format
- JSON: Better structure, preserves more metadata, easier to parse programmatically
- Both formats preserve all wagon information accurately

---

## Input Validation

The program validates all user input automatically to prevent errors.

### Validation Types

#### Integer Input
- **Requirement:** Must be a valid whole number
- **Error:** `✗ Error: enter a valid integer!`
- **Retry:** Keeps asking until valid input provided

**Example:**
```
Enter wagon ID: abc
✗ Error: enter a valid integer!
Enter wagon ID: 5
```

#### Positive Integer
- **Requirement:** Must be > 0
- **Error:** `✗ Error: number must be greater than 0!`

**Example:**
```
Enter passenger count: -10
✗ Error: number must be greater than 0!
Enter passenger count: 0
✗ Error: number must be greater than 0!
Enter passenger count: 54
```

#### Integer in Range
- **Requirement:** Must be within specified min-max range
- **Error:** `✗ Error: number must be between X and Y`

**Example:**
```
Enter comfort level (1-5): 7
✗ Error: number must be between 1 and 5
Enter comfort level (1-5): 3
```

#### Decimal Number
- **Requirement:** Must be a valid decimal number
- **Error:** `✗ Error: enter a valid decimal number!`

**Example:**
```
Enter luggage weight (kg): abc
✗ Error: enter a valid decimal number!
Enter luggage weight (kg): 120.5
```

#### Positive Decimal
- **Requirement:** Must be >= 0
- **Error:** `✗ Error: number must be greater than or equal to 0!`

**Example:**
```
Enter price per seat: -100
✗ Error: number must be greater than or equal to 0!
Enter price per seat: 1500.0
```

#### Boolean Input
- **Accepts:** true, false, yes, no, y, n, 1, 0 (case-insensitive)
- **Error:** `✗ Error: enter true/false (or yes/no)!`

**Example:**
```
Has WiFi? (true/false): maybe
✗ Error: enter true/false (or yes/no)!
Has WiFi? (true/false): yes
```

#### Non-Empty String
- **Requirement:** Cannot be blank or whitespace only
- **Error:** `✗ Error: string cannot be empty!`

**Example:**
```
Enter wagon type:
✗ Error: string cannot be empty!
Enter wagon type: Coupe
```

### Benefits
- **Prevents crashes:** Invalid input cannot break the program
- **User-friendly:** Clear error messages explain what's wrong
- **Retry mechanism:** Keeps asking until valid input provided
- **No data corruption:** Only valid data enters the system

---

## Logging System

The application uses `java.util.logging` to record important events.

### Log Configuration

**File:** `logging.properties`

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

### Log Levels

#### INFO
**When:** Normal operations
**Examples:**
- Application start/shutdown
- Train creation
- File save/load operations
- Wagon searches

**Log Entry:**
```
Nov 09, 2025 7:10:06 PM service.FileService saveToCSV
INFO: Starting to save train to CSV file: train_data.csv
```

#### WARNING
**When:** Non-critical issues
**Examples:**
- File not found (expected scenario)
- Empty train sort attempted
- Missing optional configuration

**Log Entry:**
```
Nov 09, 2025 7:06:31 PM service.FileService loadFromCSV
WARNING: CSV file not found: missing_file.csv
```

#### SEVERE
**When:** Critical errors
**Examples:**
- File I/O failures
- JSON parsing errors
- Unexpected exceptions

**Log Entry:**
```
Nov 09, 2025 7:06:31 PM service.FileService saveToJSON
SEVERE: Critical error saving to JSON file: train_data.json
```

### Log Locations

#### Console Output
All log messages appear in the terminal during program execution.

**Example:**
```
✓ Logging initialized successfully

Nov 09, 2025 7:10:06 PM Main main
INFO: ========== APPLICATION START ==========
...
```

#### Log File
**File:** `train.log` (created in current directory)

**Features:**
- Persists across program runs
- Maximum size: 50KB (then rotates)
- Single file (no rotation backups)
- Same format as console output

**Example Content:**
```
Nov 09, 2025 7:10:06 PM Main main
INFO: ========== APPLICATION START ==========
Nov 09, 2025 7:10:15 PM service.FileService saveToCSV
INFO: Starting to save train to CSV file: train_data.csv
Nov 09, 2025 7:10:15 PM service.FileService saveToCSV
INFO: Train successfully saved to CSV file. Wagon count: 3
```

### Logged Operations

#### Application Lifecycle
- Start: `========== APPLICATION START ==========`
- Shutdown: `========== APPLICATION SHUTDOWN ==========`
- Menu start: `Starting main menu`

#### Train Operations
- Create train: `Created new train: {name}`

#### File Operations
- Save CSV: `Starting to save train to CSV file: {path}`
- Load CSV: `Starting to load train from CSV file: {path}`
- Save JSON: `Starting to save train to JSON file: {path}`
- Load JSON: `Starting to load train from JSON file: {path}`
- Success: `Train successfully {saved/loaded} to/from {format} file. Wagon count: {count}`

#### Search Operations
- Search: `Search wagons by passenger range: {min}-{max}`
- Results: `Found wagons: {count}`

#### Sort Operations
- Sort: `Sorting wagons by comfort. Count: {wagonCount}`

### Use Cases
- **Debugging:** Track down issues by reviewing log file
- **Audit trail:** Record of all operations performed
- **Monitoring:** See what the application is doing
- **Troubleshooting:** Identify where errors occur

---

## Advanced Features

### OOP Design

#### Abstract Wagon Class
- `Wagon` is abstract - cannot be instantiated directly
- Forces use of specific types: `PassengerWagon` or `LuxuryWagon`
- Better type safety and polymorphism

#### Inheritance Hierarchy
```
Wagon (abstract)
  └── PassengerWagon
        └── LuxuryWagon
```

#### Command Pattern
- All menu commands implement `Command` interface
- Encapsulates operations as objects
- Easy to add new commands

### Manual JSON Parsing

The application parses JSON without external libraries.

**Features:**
- Custom field extraction methods
- Brace counting for array splitting
- Type detection based on field presence
- Whitespace handling

**Methods:**
- `extractStringValue()` - Extract string fields
- `extractIntValue()` - Extract integer fields
- `extractDoubleValue()` - Extract decimal fields
- `extractBooleanValue()` - Extract boolean fields
- `splitWagonObjects()` - Split wagon array by brace counting
- `parseWagonFromJSON()` - Parse individual wagon and determine type

**Type Detection:**
```
If hasRestaurant && hasBar && pricePerSeat present:
    → LuxuryWagon
Else if hasWiFi || hasAirConditioning present:
    → PassengerWagon
Else:
    → PassengerWagon (no amenities)
```

### Error Handling

#### File Operations
- **File not found:** Friendly error message, returns empty train
- **Parse errors:** Logged as SEVERE, operation fails gracefully
- **Invalid format:** Skips invalid entries, processes valid ones

#### User Input
- **Invalid type:** Error message, retry prompt
- **Out of range:** Error message, retry prompt
- **Empty string:** Error message, retry prompt
- **Infinite retry:** Keeps asking until valid input

#### Wagon Operations
- **Wagon not found:** Clear error message
- **Empty train:** Friendly message instead of error

---

## Testing

The application includes comprehensive unit tests.

### Test Coverage

**Total Tests:** 68

**Test Classes:**
- `WagonTest` - 9 tests
- `PassengerWagonTest` - 7 tests
- `LuxuryWagonTest` - 9 tests
- `TrainTest` - 16 tests
- `FileServiceTest` - 10 tests
- `TrainServiceTest` - 17 tests

### Test Categories

#### Model Tests
- Constructor validation
- Getter/setter functionality
- toString() output
- Edge cases (zero passengers, max comfort, etc.)

#### Service Tests
- File save/load (CSV and JSON)
- Round-trip preservation
- Empty train handling
- Sorting algorithms
- Search functionality
- Statistics calculations

### Running Tests

```bash
# Compile tests
javac -encoding UTF-8 -cp "out;lib/*" -d out test/**/*.java

# Run all tests
java -cp "out;lib/*" org.junit.runner.JUnitCore model.WagonTest model.PassengerWagonTest model.LuxuryWagonTest model.TrainTest service.FileServiceTest service.TrainServiceTest

# Expected output
OK (68 tests)
```

### Test Benefits
- **Confidence:** Changes don't break existing functionality
- **Documentation:** Tests show how to use the API
- **Quality:** Catches bugs before they reach users
- **Coverage:** 80%+ code coverage

---

## Common Workflows

### Complete Workflow Example

**Task:** Create a train, add wagons, save to file

```
1. Start program
   → java -cp out Main

2. Create new train
   → create
   → Express Kyiv
   → Lviv

3. Add simple wagon
   → add
   → 1
   → ID: 1, Type: Platzkart, Passengers: 54, Luggage: 120, Comfort: 3

4. Add passenger wagon
   → add
   → 2
   → ID: 2, Type: Coupe, Passengers: 36, Luggage: 80, Comfort: 4
   → WiFi: yes, AC: yes

5. Add luxury wagon
   → add
   → 3
   → ID: 3, Type: SV, Passengers: 18, Luggage: 40, Comfort: 5
   → WiFi: yes, AC: yes, Restaurant: yes, Bar: yes, Price: 1500

6. Review composition
   → show

7. View statistics
   → stats

8. Sort by comfort
   → sort
   → 1

9. Save to JSON
   → save
   → 2

10. Exit
    → exit
```

### Load and Modify Workflow

**Task:** Load existing train, modify it, save changes

```
1. Load train
   → load
   → mytrain
   → Express
   → Lviv

2. Show current composition
   → show

3. Remove wagon
   → remove
   → 5

4. Edit wagon
   → edit
   → 2
   → 2 (change passengers)
   → 40

5. Add new wagon
   → add
   → 2
   → [enter details]

6. Save changes
   → save
   → 1 (CSV)

7. Exit
   → exit
```

### Analysis Workflow

**Task:** Analyze train composition

```
1. Load train
   → load
   → mytrain

2. View statistics
   → stats

3. Find high-capacity wagons
   → find
   → 1 (by passenger range)
   → 50 (min)
   → 100 (max)

4. Find all Coupe wagons
   → find
   → 2 (by type)
   → Coupe

5. Sort by comfort
   → sort
   → 1

6. Show sorted composition
   → show

7. Exit without saving
   → exit
```

---

## Tips and Best Practices

### Data Entry
- **Use descriptive wagon types:** "Platzkart", "Coupe", "SV" instead of generic names
- **Use unique IDs:** Don't reuse wagon IDs within the same train
- **Be consistent:** Use the same naming scheme for similar wagons
- **Realistic values:** Use reasonable passenger counts and luggage weights

### File Management
- **Regular saves:** Save your work frequently
- **Descriptive filenames:** Use meaningful names when manually renaming files
- **Backup important trains:** Keep copies of important configurations
- **Version control:** Save different versions with different filenames

### Performance
- **Large trains:** The program handles 100+ wagons efficiently
- **File size:** JSON files are larger but more readable than CSV
- **Memory:** All data kept in memory - no database needed

### Troubleshooting
- **Check log file:** Review `train.log` for errors
- **Verify file format:** Ensure CSV/JSON files aren't corrupted
- **Restart if needed:** Program state resets on restart
- **Input validation:** Read error messages carefully

---

## Limitations

### Known Constraints
- **No undo:** Changes cannot be undone (except by reloading)
- **Single train:** Can only work with one train at a time
- **Manual JSON:** Compact JSON only (custom parser limitations)
- **No network:** Local files only, no cloud storage
- **Console only:** No graphical interface

### Future Enhancements
- Multi-train support
- Undo/redo functionality
- Database integration
- Web interface
- Export to other formats (XML, Excel)
- Advanced search (regex, multiple criteria)
- Wagon templates
- Train composition validation rules

---

## Conclusion

This Passenger Train Manager provides a complete solution for managing train compositions through an intuitive command-line interface. With robust input validation, comprehensive logging, and flexible file storage options, it's suitable for both educational purposes and practical train composition planning.

For technical implementation details, see the main README.md file.
For refactoring notes, see REFACTORING.md.

**Version:** 1.0
**Last Updated:** November 2025
**License:** Educational use
