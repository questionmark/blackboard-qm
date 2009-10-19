#!/bin/bash
. ./classpath.sh
mkdir -p doc/qmwise
javadoc -d doc/qmwise src/com/questionmark/QMWISe/*.java
