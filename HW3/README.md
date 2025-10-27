# HW3 - Hardware System with Queue Implementation

## Description
This homework implements a hardware system simulation using Queue data structure. The project extends the hardware system concept with command queuing functionality, demonstrating the use of LinkedList-based Queue implementation for processing system commands.

## Features
- **Queue-based Command Processing**: Uses Java's LinkedList as a Queue to store and process commands sequentially
- **Hardware System Simulation**: Simulates various hardware devices and their interactions
- **Logging System**: Comprehensive logging functionality for system operations
- **Configuration Management**: Supports configuration files for system setup

## Project Structure
```
src/
├── Main/
│   └── Main.java          # Main application entry point with Queue implementation
├── core/                  # Core system components
├── devices/               # Device implementations
└── protocols/             # Communication protocols
```

## Key Components
- **Queue Implementation**: Uses `LinkedList<String>` as a Queue for command processing
- **Command Processing**: Sequential processing of commands from the queue
- **Hardware System**: Integration with hardware devices and protocols
- **Configuration System**: File-based configuration management

## How to Build and Run
```bash
# Build the project
make

# Run the program
java -cp build Main.Main <config_file> <log_path>

# Example
java -cp build Main.Main config.txt logs/
```

## Data Structures Used
- **Queue (LinkedList)**: For command storage and sequential processing
- **Various device-specific data structures**: For hardware simulation

## Assignment Files
- `HW3.pdf` - Assignment description and requirements
- `makefile` - Build configuration
- `src/` - Source code directory

---
**Course:** CSE222 - Data Structures and Algorithms  
**Semester:** Spring 2025  
**Focus:** Queue Data Structure Implementation