# Makefile for HW4 - Planetary System

# Java compiler
JAVAC = javac
JAVA = java

# Source files
SOURCES = Main.java PlanetarySystem.java TreeNode.java SensorData.java

# Class files
CLASSES = $(SOURCES:.java=.class)

# Main class
MAIN_CLASS = Main

# Default target
all: $(CLASSES)

# Compile Java files
%.class: %.java
	$(JAVAC) $<

# Run the program
run: $(CLASSES)
	$(JAVA) $(MAIN_CLASS)

# Clean compiled files
clean:
	rm -f *.class

# Generate documentation
docs:
	javadoc -d docs *.java

# Help target
help:
	@echo "Available targets:"
	@echo "  all    - Compile all Java files"
	@echo "  run    - Compile and run the program"
	@echo "  clean  - Remove compiled class files"
	@echo "  docs   - Generate Javadoc documentation"
	@echo "  help   - Show this help message"

.PHONY: all run clean docs help