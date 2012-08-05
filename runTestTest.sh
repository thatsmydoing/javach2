#!/bin/sh
for file in `find res/test/ -maxdepth 1 -type f -iname "*.java"`
do
	java -cp build edu.ateneo.javach.CommandLineDetector $file >> resTestFilesResults 2>> resTestFilesResults
done
