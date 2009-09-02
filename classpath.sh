#!/bin/bash
CLASSPATH="."
for JAR in $(find . -iname "*.jar"); do
	CLASSPATH="$CLASSPATH:$JAR"
done
export CLASSPATH
