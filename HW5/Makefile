JAVAC = javac
JAVA = java
SRC = $(wildcard *.java)
CLASS_FILES = $(SRC:.java=.class)

build: $(CLASS_FILES)

%.class: %.java
	$(JAVAC) $<

run: build
	$(JAVA) TaskManager $(ARGS)

clean:
	rm -f *.class
