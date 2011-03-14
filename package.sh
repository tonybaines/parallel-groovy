#!/bin/sh
NAME=parallel-groovy
rm -rf $NAME $NAME.zip
mkdir $NAME
cp -a src lib build.gradle .classpath .project $NAME
zip -r $NAME.zip $NAME
