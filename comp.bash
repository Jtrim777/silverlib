#!/bin/bash

cd ~/Stuff/Creations/Programming/silverlib

ant

if [ ! -f silverlib.jar ]; then
  echo "Failiure!!!"
  exit
fi

cd lib

rm silverlib.jar

cd ..

mv silverlib.jar lib/silverlib.jar
