# Hardware System Simulator Makefile
JAVA_FILES = $(shell find src -name "*.java")
BUILD_DIR = build
MAIN_CLASS = Main.Main

.PHONY: all collect build run clean help

all: build

collect:
	@echo "Collecting Java source files..."
	find src -name "*.java" > sources.txt
	@echo "Found $(shell wc -l < sources.txt) Java files"

build: collect
	@echo "Building project..."
	@mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) @sources.txt
	@echo "Build completed successfully"

run: build
	@echo "Starting Hardware System Simulator..."
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

clean:
	@echo "Cleaning build files..."
	rm -rf $(BUILD_DIR) sources.txt
	@echo "Clean completed"

help:
	@echo "Available targets:"
	@echo "  all     - Build the project (default)"
	@echo "  collect - Collect Java source files"
	@echo "  build   - Compile the project"
	@echo "  run     - Run the application"
	@echo "  clean   - Remove build files"
	@echo "  help    - Show this help message"