#!/bin/bash
DATE=`date '+%Y-%m-%d %H:%M:%S'`

cd ~/Stuff/Creations/Programming/silverlib

ant -S

cp -r doc/* ../silverlibDoc/

ant -S clean

cd ../silverlibDoc

git add .
git commit -m "$DATE UPDATE"
git push -u origin master
