#!/bin/bash
DATE=`date '+%Y-%m-%d %H:%M:%S'`

ant -S

cp -r doc/* ../silverlibDoc/

cp lib/silverlib.jar dist/

zip -r ../silverlibDoc/Silverlib.zip dist

ant -S clean

if [ $# == 0 ]; then
  cd ../silverlibDoc

  git add .
  git commit -m "$DATE UPDATE"
  git push -u origin master
elif [[ $1 == "-d" ]]; then
  cd ../silverlibDoc

  git add .
  git commit -m "$DATE UPDATE"
  git push -u origin master
elif [[ $1 == "-p" ]]; then
  git add .

  if [[ $# == 2 ]]; then
    git commit -m "CHANGES $DATE"
  else
    git commit -m $3
  fi

  git push -u origin master

  cd ../silverlibDoc

  git add .
  git commit -m "$DATE UPDATE"
  git push -u origin master
fi
