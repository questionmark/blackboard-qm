#!/bin/bash
rm PerceptionPlugin.war
cd WebContent
zip -r ../PerceptionPlugin.war * -x "*/*.svn/*"
cd ..
