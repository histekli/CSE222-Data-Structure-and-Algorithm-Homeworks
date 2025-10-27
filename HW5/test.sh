#!/bin/bash
make clean
make build
echo -e "4\n1\n3\n1\n0\n2\n4\n3\n1\nexecute" | make run ARGS="config.txt"
make clean