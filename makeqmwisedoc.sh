#!/bin/bash
. ./classpath.sh
mkdir -p doc/qmwise
javadoc -d doc/qmwise WebRoot/WEB-INF/src/com/questionmark/QMWISe/*.java
