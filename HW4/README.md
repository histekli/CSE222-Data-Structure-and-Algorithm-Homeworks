# HW4 - Planetary System with Tree Data Structure

## Description
This homework implements a **Tree Data Structure** to simulate a planetary system. The project demonstrates hierarchical relationships between celestial bodies (stars, planets, and moons) using tree nodes, along with advanced tree traversal algorithms and sensor data management.

## Features
- **Tree Data Structure**: Custom implementation using `TreeNode` class
- **Hierarchical System**: Stars → Planets → Moons relationship
- **Sensor Data Management**: Temperature, pressure, humidity, and radiation tracking
- **Advanced Tree Operations**:
  - Tree traversal and search algorithms
  - Path finding using Stack data structure
  - Radiation anomaly detection
  - Mission report generation
- **Command-Line Interface**: Interactive system for managing the planetary system

## Project Structure
```
HW4/
├── Main.java               # Main application with CLI
├── PlanetarySystem.java    # Core system managing the tree structure
├── TreeNode.java          # Tree node implementation
├── SensorData.java        # Sensor data class for environmental readings
├── HW4.pdf               # Assignment description
└── README.md             # This file
```

## Key Data Structures Used
1. **Tree Structure**: Custom tree implementation with parent-child relationships
2. **ArrayList**: For managing lists of moons
3. **Stack**: For path tracking (getPathTo method)
4. **List**: For storing search results

## Core Classes

### TreeNode
- Represents nodes in the planetary system tree
- Supports hierarchical relationships (Star → Planet → Moon)
- Manages sensor data for each celestial body

### PlanetarySystem  
- Main system class managing the entire tree structure
- Implements tree traversal algorithms
- Provides search and analysis functionality

### SensorData
- Stores environmental data (temperature, pressure, humidity, radiation)
- Provides formatted output for sensor readings

## Available Commands
```bash
# Create the planetary system with a star
createPlanetSystem <starName> <temperature> <pressure> <humidity> <radiation>

# Add a planet to a star or another planet
addPlanet <planetName> <parentName> <temperature> <pressure> <humidity> <radiation>

# Add a satellite/moon to a planet
addSatellite <moonName> <planetName> <temperature> <pressure> <humidity> <radiation>

# Find radiation anomalies above threshold
findRadiationAnomalies <threshold>

# Get path to a specific celestial body
getPathTo <bodyName>

# Print mission report for entire system or specific body
printMissionReport [bodyName]

# Exit the program
exit
```

## How to Build and Run
```bash
# Compile all Java files
javac *.java

# Run the program
java Main

# Example usage:
createPlanetSystem Sol 5778 0 0 1000
addPlanet Earth Sol 288 101325 60 0.1
addSatellite Moon Earth 250 0 0 0.05
findRadiationAnomalies 0.5
getPathTo Moon
printMissionReport
```

## Key Algorithms Implemented
- **Tree Traversal**: Depth-first search for finding nodes
- **Path Finding**: Stack-based path reconstruction
- **Anomaly Detection**: Tree-wide search with filtering
- **Hierarchical Display**: Recursive tree printing

## Example Tree Structure
```
Sol (Star)
├── Mercury (Planet)
├── Venus (Planet)
├── Earth (Planet)
│   ├── Moon (Satellite)
│   └── ISS (Satellite)
└── Mars (Planet)
    ├── Phobos (Satellite)
    └── Deimos (Satellite)
```

## Assignment Files
- `HW4.pdf` - Assignment description and requirements

---
**Course:** CSE222 - Data Structures and Algorithms  
**Semester:** Spring 2025  
**Focus:** Tree Data Structure, Hierarchical Systems, Tree Traversal Algorithms