#!/bin/bash
. ./classpath.sh
mkdir -p WebRoot/WEB-INF/classes
find WebRoot -name "*.java" -exec javac -target 1.5 -d WebRoot/WEB-INF/classes {} +
