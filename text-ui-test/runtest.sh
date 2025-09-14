#!/usr/bin/env bash

if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

if [ -e "./ACTUAL1.TXT" ]
then
    rm ACTUAL1.TXT
fi

if [ -e "./ACTUAL2.TXT" ]
then
    rm ACTUAL2.TXT
fi

if [ -e "./tasks.txt" ]
then
    rm tasks.txt
fi

if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/clanky/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# First run: Create tasks and write to tasks.txt
java -classpath ../bin clanky.Clanky < input1.txt > ACTUAL1.TXT

# Ensure persisted data is present
if [ ! -e "tasks.txt" ]; then
    echo "********** PERSISTENCE FAILURE **********"
    exit 1
fi

# Second run: Load tasks from tasks.txt and check against expected output
java -classpath ../bin clanky.Clanky < input2.txt > ACTUAL2.TXT

# Convert to UNIX format (if necessary on your platform)
cp EXPECTED1.TXT EXPECTED1-UNIX.TXT
cp EXPECTED2.TXT EXPECTED2-UNIX.TXT
# dos2unix ACTUAL1.TXT EXPECTED1-UNIX.TXT
# dos2unix ACTUAL2.TXT EXPECTED2-UNIX.TXT
# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT

# Compare the output to the expected output for both runs
diff ACTUAL1.TXT EXPECTED1.TXT
result1=$?

diff ACTUAL2.TXT EXPECTED2.TXT
result2=$?

if [ $result1 -eq 0 ] && [ $result2 -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi