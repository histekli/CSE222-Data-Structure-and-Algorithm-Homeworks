# HW5 - Task Manager with Priority Queue and Min Heap

## Description
This homework implements a **Priority Queue** using a custom **Min Heap** data structure to manage tasks efficiently. The system demonstrates advanced heap operations, priority-based task scheduling, and user management with priority levels.

## Features
- **Min Heap Implementation**: Custom min heap data structure from scratch
- **Priority Queue**: Task prioritization using heap-based priority queue
- **Task Management**: Add, execute, and manage tasks based on user priorities
- **User System**: User registration with priority levels
- **Configuration Management**: File-based user configuration system
- **Comprehensive Testing**: Automated test suite with shell scripts

## Project Structure
```
HW5/
├── TaskManager.java           # Main task management system
├── MinHeap.java              # Custom Min Heap implementation
├── MyPriorityQueue.java      # Priority Queue using Min Heap
├── MyTask.java               # Task representation with priority
├── MyUser.java               # User class with priority levels
├── Makefile                  # Build configuration
├── config.txt                # User configuration file
├── test.sh                   # Automated test script
├── CSE222_BİL505 Assignment #5.pdf  # Assignment description
└── README.md                 # This file
```

## Key Data Structures

### Min Heap (`MinHeap.java`)
- **Purpose**: Fundamental heap data structure for priority management
- **Operations**: 
  - `insert()` - O(log n) insertion with heap property maintenance
  - `extractMin()` - O(log n) minimum element removal
  - `heapify()` - Maintains heap property after operations
  - `buildHeap()` - Constructs heap from array in O(n) time

### Priority Queue (`MyPriorityQueue.java`)
- **Purpose**: High-level interface built on top of Min Heap
- **Features**: Priority-based element management using heap operations

### Task System
- **MyTask**: Represents tasks with priority, user assignment, and execution details
- **MyUser**: User representation with priority levels for task scheduling

## Core Algorithms Implemented

1. **Heap Operations**:
   - **Insertion**: Bottom-up heapify to maintain min heap property
   - **Extraction**: Remove minimum element and restore heap structure
   - **Heapify**: Recursive procedure to maintain heap property

2. **Priority Management**:
   - Tasks prioritized based on user priority levels
   - Efficient O(log n) task insertion and extraction

3. **Task Scheduling**:
   - Priority-based task execution order
   - User-specific task assignment and management

## How to Build and Run

### Build
```bash
# Using Makefile
make

# Or compile manually
javac *.java
```

### Run
```bash
# Run the main program
java TaskManager

# Run automated tests
./test.sh

# Make test script executable if needed
chmod +x test.sh
```

### Configuration
The `config.txt` file contains user priority levels:
```
# Each line represents a user's priority (lower number = higher priority)
1
2
3
5
```

## Key Features Demonstrated

### 1. Min Heap Properties
- **Complete Binary Tree**: Efficiently stored in array
- **Heap Property**: Parent ≤ Children for all nodes
- **Efficient Operations**: O(log n) insertion and deletion

### 2. Priority Queue Operations
```java
// Add task with priority
taskManager.addTask(taskDescription, userId);

// Execute highest priority task
taskManager.executeNextTask();

// Check task queue status
taskManager.displayTaskQueue();
```

### 3. User Management
- Load users from configuration file
- Assign priority levels to users
- Priority-based task assignment

## Example Usage
```java
TaskManager manager = new TaskManager();

// Load users from config
manager.loadUsers("config.txt");

// Add tasks with different priorities
manager.addTask("High priority task", 0);     // User 0 (priority 1)
manager.addTask("Lower priority task", 1);    // User 1 (priority 2)

// Execute tasks in priority order
manager.executeNextTask();  // Executes high priority task first
```

## Complexity Analysis
- **Insertion**: O(log n) - Heap insertion with upward heapify
- **Extraction**: O(log n) - Remove min with downward heapify
- **Peek**: O(1) - Access minimum element
- **Build Heap**: O(n) - Construct heap from array

## Testing
The project includes comprehensive testing:
- **Unit Tests**: Individual component testing
- **Integration Tests**: Full system workflow testing
- **Performance Tests**: Heap operation efficiency validation

## Assignment Files
- `CSE222_BİL505 Assignment #5.pdf` - Detailed assignment requirements

---
**Course:** CSE222 - Data Structures and Algorithms  
**Semester:** Spring 2025  
**Focus:** Priority Queue, Min Heap, Heap Operations, Task Scheduling