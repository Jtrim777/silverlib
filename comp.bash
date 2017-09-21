#!/bin/bash

cd ~/Stuff/Creations/Programming/silverlib

ant -S

cp -r doc/* ../silverlibDoc/

ant -S clean
