SRC = src
BUILD = build

collect:
	find $(SRC) -name "*.java" > sources.txt

build:
	javac -d $(BUILD) @sources.txt

run:
	java -cp build Main.Main $(ARGS)

clean:
	rm -rf $(BUILD) sources.txt