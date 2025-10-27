# HW6 - Spell Checker with Hash Tables and Edit Distance

## Description
This homework implements a sophisticated **Spell Checker** application using custom **Hash Table** implementations (HashSet and HashMap) combined with **Edit Distance algorithms** for intelligent word correction suggestions. The project demonstrates advanced hashing techniques, collision resolution, and dynamic programming algorithms.

## Features
- **Custom Hash Table Implementation**: Built-from-scratch GTUHashSet and GTUHashMap
- **Advanced Spell Checking**: Dictionary-based word validation
- **Edit Distance Algorithm**: Levenshtein distance for intelligent suggestions
- **Collision Resolution**: Efficient handling of hash collisions
- **Performance Optimization**: High-performance dictionary loading and lookup
- **Interactive Interface**: Real-time spell checking with correction suggestions

## Project Structure
```
HW6/
├── src/spellchecker/
│   ├── SpellChecker.java          # Main spell checker application
│   ├── GTUHashSet.java           # Custom HashSet implementation
│   ├── GTUHashMap.java           # Custom HashMap implementation
│   ├── EditDistanceHelper.java   # Edit distance algorithms
│   ├── Entry.java                # Hash table entry class
│   ├── GTUArrayList.java         # Custom ArrayList implementation
│   └── TestRunner.java           # Testing and benchmarking
├── dictionary.txt                # Dictionary file with words
├── makefile                      # Build configuration
├── HW6 1.pdf                    # Assignment description
├── report.pdf                   # Performance analysis report
└── README.md                    # This file
```

## Key Data Structures and Algorithms

### 1. Hash Table Implementation (`GTUHashMap.java`)
- **Custom Hash Function**: Efficient hashing for string keys
- **Collision Resolution**: Open addressing or chaining (implementation specific)
- **Dynamic Resizing**: Automatic capacity expansion with load factor management
- **Operations**: O(1) average case for insert, search, delete

### 2. Hash Set (`GTUHashSet.java`)
- **Set Interface**: Built on top of GTUHashMap
- **Uniqueness Guarantee**: Prevents duplicate elements
- **Set Operations**: Add, remove, contains with hash table efficiency

### 3. Edit Distance Algorithm (`EditDistanceHelper.java`)
- **Levenshtein Distance**: Dynamic programming approach
- **Operations Supported**:
  - Character insertion
  - Character deletion  
  - Character substitution
- **Time Complexity**: O(m×n) where m,n are string lengths
- **Space Optimization**: Possible O(min(m,n)) space optimization

### 4. Spell Checking Logic
- **Dictionary Loading**: Efficient bulk loading of dictionary words
- **Word Validation**: O(1) dictionary lookup using hash table
- **Suggestion Generation**: Edit distance-based word recommendations
- **Performance Metrics**: Loading time and lookup performance tracking

## Core Algorithms

### Hash Table Operations
```java
// Custom hash function implementation
private int hash(K key) {
    return Math.abs(key.hashCode()) % capacity;
}

// Collision resolution and dynamic resizing
private void resize() {
    // Rehash all entries to new capacity
}
```

### Edit Distance Calculation
```java
// Dynamic programming approach for Levenshtein distance
public static int calculateEditDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    // Fill DP table with minimum edit operations
}
```

## How to Build and Run

### Build
```bash
# Using Makefile
make

# Or compile manually
javac -cp . src/spellchecker/*.java
```

### Run
```bash
# Run spell checker
java src.spellchecker.SpellChecker

# Run tests and benchmarks
java src.spellchecker.TestRunner
```

### Usage Example
```
Loading dictionary...
Dictionary loaded with 120000 words in 45.32 ms.

Enter a word (or type 'exit' to quit): recieve
"recieve" is not in the dictionary.
Did you mean:
1. receive (edit distance: 1)
2. receiver (edit distance: 2)
3. received (edit distance: 2)

Enter a word (or type 'exit' to quit): algorithm
"algorithm" is correctly spelled!
```

## Performance Features

### Hash Table Optimization
- **Initial Capacity**: Pre-sized for dictionary size to minimize resizing
- **Load Factor Management**: Optimal load factor for performance vs. memory
- **Buffered I/O**: 64KB buffer for fast dictionary loading

### Edit Distance Optimization
- **Early Termination**: Stop calculation when distance exceeds threshold
- **Space Optimization**: Reduce space complexity where possible
- **Candidate Filtering**: Efficient filtering of suggestion candidates

## Key Algorithms Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Hash Insert | O(1) average | O(n) total |
| Hash Lookup | O(1) average | O(1) |
| Edit Distance | O(m×n) | O(m×n) |
| Dictionary Load | O(n) | O(n) |

## Testing and Benchmarking

### Performance Metrics
- Dictionary loading time
- Hash table collision statistics
- Lookup performance analysis
- Memory usage profiling

### Test Cases
- Correctly spelled words
- Misspelled words with suggestions
- Edge cases and performance stress tests

## Assignment Files
- `HW6 1.pdf` - Assignment requirements and specifications
- `report.pdf` - Performance analysis and implementation details

---
**Course:** CSE222 - Data Structures and Algorithms  
**Semester:** Spring 2025  
**Focus:** Hash Tables, Edit Distance Algorithms, String Processing, Performance Optimization