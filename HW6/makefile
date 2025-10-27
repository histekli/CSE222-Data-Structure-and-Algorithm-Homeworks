# Compiler and flags
JAVAC = javac
JAVA = java
SRC_DIR = src/spellchecker
BIN_DIR = bin

# Default target
all: build

# Build target
build:
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SRC_DIR)/*.java

# Run target
run: build
	$(JAVA) -cp $(BIN_DIR) src.spellchecker.SpellChecker

# Clean target
clean:
	rm -rf $(BIN_DIR)

# Test target
test: build
	$(JAVA) -cp $(BIN_DIR) src.spellchecker.TestRunner