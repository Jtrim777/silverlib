#!/bin/bash
THE_OS=`uname`
loc="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
INSTALLED=false

cd $loc
VERSION="UNKNOWN VERSION"

if [[ $loc -ef ~/Library/silverlib ]]; then
  INSTALLED=true
fi

function install {
  cd ~/Library

  if [ -d "silverlib" ]; then
    #Overwrite
    cd silverlib
    rm -f silverlib.jar
    cd $loc
    cp -r ./* ~/Library/silverlib/
  else
    #Create
    mkdir "silverlib"
    cd $loc
    cp -r ./* ~/Library/silverlib/

    cd ~/
    if [ -f ".bash_profile" ]; then
      #Append
      echo -e "\nexport PATH=${PATH}:~/Library/silverlib/" >> .bash_profile
      echo -e "export CLASSPATH=${CLASSPATH}:~/Library/silverlib/silverlib.jar" >> .bash_profile
      echo -e "alias silverlib=~/Library/silverlib/silverlib.bash" >> .bash_profile
      # echo -e "export SILVERLIB_VERSION='$VERSION'" >> .bash_profile
    else
      #Create and add
      echo -e "export PATH=${PATH}:~/Library/silverlib/" > .bash_profile
      echo -e "export CLASSPATH=${CLASSPATH}:~/Library/silverlib/silverlib.jar" >> .bash_profile
      echo -e "alias silverlib=~/Library/silverlib/silverlib.bash" >> .bash_profile
      # echo -e "export SILVERLIB_VERSION='$VERSION'" >> .bash_profile
    fi
  fi
  
  cd ~/Library/silverlib
  git init
  git remote add -f origin https://gitlab.com/SiLordOfLight/silverlib.git
  git pull --tags
  VERSION=`git describe --tags | cut -d '-' -f1`
}

function update {
  cd ~/Library/silverlib
  
  git pull --tags
  VERSION=`git describe --tags | cut -d '-' -f1`
  
  git config core.sparseCheckout true
  echo "dist/silverlib.jar" >> .git/info/sparse-checkout
  echo "dist/info.txt" >> .git/info/sparse-checkout
  git pull origin master
}

if [[ $THE_OS == 'Darwin' ]]; then
  if [ $INSTALLED == false ] && [ $# == 0 ]; then
    install
    cd ..
    rmdir -f $loc
  elif [ $INSTALLED == true ] && [ $# == 0 ]; then
    update
  elif [[ $1 == '-i' ]]; then
    install
    cd ..
    rmdir -f $loc
  elif [[ $1 == '-u' ]]; then
    update
  elif [[ $1 == '-v' ]]; then
    echo -e "Silverlib Java Library\n Copyright 2017 Jake Trimble" 
    echo -e "$VERSION"
  elif [[ $1 == '--uninstall' ]]; then
    rm -f ~/Library/silverlib
  elif [[ $1 == '-h' ]]; then
    echo "-i to install library"
    echo "-u to update library"
    echo "-v to get library version"
    echo ""
    echo -e "Go to silordoflight.github.io/silverlib for Java Documentation on the classes\nin this library"
  fi
else
  echo "We don't serve your kind"
fi
