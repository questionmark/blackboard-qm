#!/bin/bash
rm PerceptionPlugin.war
cd WebRoot
zip -r ../PerceptionPlugin.war * -x "*/*.svn/*"
cd ..
