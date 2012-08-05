#!/bin/sh
for file in `find res -maxdepth 1 -type f -iname "UV*.java"`
do
	java -cp build edu.ateneo.javach.CommandLineDetector $file #>> resFilesResults 2>> resFilesResults
done
