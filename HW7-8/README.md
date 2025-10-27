# HW7-8 - Comprehensive Data Structures and Algorithms Final Project

## Description
This is the **final comprehensive project** (HW7-8 combined) that demonstrates mastery of advanced data structures and algorithms. The project implements multiple **Graph Algorithms**, **Sorting Algorithms**, and provides extensive performance analysis and testing frameworks.

## Features
- **Graph Data Structures**: Multiple graph representations and traversal algorithms
- **Advanced Sorting Algorithms**: Implementation and analysis of various sorting techniques
- **Comprehensive Testing**: Extensive test suites for all algorithms
- **Performance Analysis**: Detailed benchmarking and complexity analysis
- **Documentation**: Complete Javadoc documentation and technical reports

## Project Structure
```
HW7-8/
├── src/
│   ├── DSA/
│   │   ├── Graphs/           # Graph algorithms and data structures
│   │   ├── Sorting/          # Sorting algorithm implementations
│   │   └── Tests/            # Comprehensive test suites
│   └── Main/                 # Main application and demos
├── doc/                      # Javadoc generated documentation
├── makefile                  # Build configuration
├── sources.txt               # Source file listing
├── hw7_8_report.pdf         # Technical report and analysis
├── CSE222 BIL505 Homework #7-8.pdf  # Assignment description
└── README.md                 # This file
```

## Key Components

### 1. Graph Algorithms (`src/DSA/Graphs/`)
Implementation of fundamental graph algorithms including:

#### Graph Representations
- **Adjacency Matrix**: Matrix-based graph representation
- **Adjacency List**: List-based graph representation  
- **Edge List**: Edge-based graph representation

#### Graph Traversal Algorithms
- **Depth-First Search (DFS)**: Recursive and iterative implementations
- **Breadth-First Search (BFS)**: Queue-based traversal
- **Topological Sort**: DAG ordering algorithms

#### Shortest Path Algorithms
- **Dijkstra's Algorithm**: Single-source shortest path
- **Bellman-Ford Algorithm**: Handles negative weights
- **Floyd-Warshall Algorithm**: All-pairs shortest path

#### Minimum Spanning Tree
- **Kruskal's Algorithm**: Edge-based MST with Union-Find
- **Prim's Algorithm**: Vertex-based MST with priority queue

#### Advanced Graph Algorithms
- **Strongly Connected Components**: Kosaraju's and Tarjan's algorithms
- **Cycle Detection**: Directed and undirected graphs
- **Graph Coloring**: Vertex coloring algorithms

### 2. Sorting Algorithms (`src/DSA/Sorting/`)
Comprehensive implementation of sorting algorithms:

#### Comparison-Based Sorts
- **Quick Sort**: Divide-and-conquer with pivot selection
- **Merge Sort**: Stable divide-and-conquer sorting
- **Heap Sort**: Binary heap-based sorting
- **Insertion Sort**: Simple comparison-based sort
- **Selection Sort**: Minimum selection algorithm
- **Bubble Sort**: Adjacent element swapping

#### Non-Comparison Sorts
- **Counting Sort**: Integer sorting algorithm
- **Radix Sort**: Digit-based sorting
- **Bucket Sort**: Distribution-based sorting

#### Hybrid Algorithms
- **Intro Sort**: Quick sort with heap sort fallback
- **Tim Sort**: Hybrid merge sort and insertion sort

### 3. Testing Framework (`src/DSA/Tests/`)
Comprehensive testing and benchmarking:
- **Unit Tests**: Individual algorithm testing
- **Performance Tests**: Time and space complexity analysis
- **Correctness Tests**: Algorithm validation
- **Stress Tests**: Large dataset performance evaluation

## Algorithm Complexity Analysis

### Graph Algorithms
| Algorithm | Time Complexity | Space Complexity | Use Case |
|-----------|----------------|------------------|----------|
| DFS | O(V + E) | O(V) | Graph traversal |
| BFS | O(V + E) | O(V) | Shortest path (unweighted) |
| Dijkstra | O((V + E) log V) | O(V) | Single-source shortest path |
| Floyd-Warshall | O(V³) | O(V²) | All-pairs shortest path |
| Kruskal | O(E log E) | O(V) | Minimum spanning tree |
| Prim | O(V² or E log V) | O(V) | Minimum spanning tree |

### Sorting Algorithms
| Algorithm | Best Case | Average Case | Worst Case | Space | Stable |
|-----------|-----------|--------------|------------|-------|--------|
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No |
| Counting Sort | O(n + k) | O(n + k) | O(n + k) | O(k) | Yes |
| Radix Sort | O(d(n + k)) | O(d(n + k)) | O(d(n + k)) | O(n + k) | Yes |

## How to Build and Run

### Build
```bash
# Using Makefile
make

# Clean build files
make clean

# Generate documentation
make docs
```

### Run
```bash
# Run main application
java -cp build Main.Main

# Run specific tests
java -cp build DSA.Tests.GraphTests
java -cp build DSA.Tests.SortingTests
```

### Testing
```bash
# Run all tests
make test

# Run performance benchmarks
make benchmark
```

## Key Features Demonstrated

### 1. Graph Processing
- **Large Graph Handling**: Efficient processing of large graph datasets
- **Multiple Representations**: Comparison of different graph storage methods
- **Algorithm Selection**: Choosing optimal algorithms for specific problems

### 2. Sorting Analysis
- **Performance Comparison**: Empirical analysis of sorting algorithms
- **Stability Analysis**: Understanding stable vs unstable sorts
- **Adaptive Algorithms**: Algorithms that perform better on partially sorted data

### 3. Memory Management
- **Space Optimization**: Efficient memory usage in algorithms
- **Cache Efficiency**: Algorithms optimized for modern CPU caches
- **Scalability**: Handling large datasets efficiently

## Performance Benchmarking

### Graph Algorithm Benchmarks
- Comparison of graph representations for different operations
- Analysis of shortest path algorithms on various graph types
- MST algorithm performance on dense vs sparse graphs

### Sorting Algorithm Benchmarks
- Performance analysis across different input sizes
- Best/average/worst case empirical validation
- Memory usage profiling

## Technical Reports
- `hw7_8_report.pdf` - Comprehensive technical analysis and performance evaluation
- `CSE222 BIL505 Homework #7-8.pdf` - Assignment requirements and specifications

## Documentation
Complete Javadoc documentation available in the `doc/` directory, including:
- API documentation for all classes and methods
- Algorithm complexity analysis
- Usage examples and best practices

---
**Course:** CSE222 - Data Structures and Algorithms  
**Semester:** Spring 2025  
**Focus:** Advanced Graph Algorithms, Sorting Algorithms, Performance Analysis, Algorithm Design