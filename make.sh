#!/bin/bash
. ./classpath.sh
mkdir -p WebContent/WEB-INF/classes
find src -name "*.java" -exec javac -target 1.5 -d WebContent/WEB-INF/classes {} +
