#!/bin/bash
THE_OS=`uname`
loc="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
INSTALLED=false

if [ -d "~/Library/silverlib" ]; then
  INSTALLED=true
fi

if [ $# == 0 ]; then
  cp -R $loc/. ~/Library/silverlib
else
  if [ $1 == "-v" ]; then
    vers=$(cat ~/Library/silverlib/version.txt)
    echo "Silverlib Java Library, version ${vers}; Copyright Jake Trimble, 2018"
  elif [ $1 == "-i" ]; then
    mkdir ~/Library/silverlib

    cp -R $loc/. ~/Library/silverlib
    echo "alias silverlib=\`~/Library/silverlib/silverlib.bash\`" >> ~/.bash_profile
  elif [ $1 == "-u" ]; then
    cp -R $loc/. ~/Library/silverlib
  fi
fi
