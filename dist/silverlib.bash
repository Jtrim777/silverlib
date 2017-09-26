#!/bin/bash
THE_OS=`uname`
loc="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

if [[ $THE_OS == 'Darwin' ]]; then
  #Install
  cd ~/Library

  if [ -d "silverlib" ]; then
    #Overwrite
    cd silverlib
    rm -f silverlib.jar
    cd $loc
    cp silverlib.jar ~/Library/silverlib/
  else
    #Create
    mkdir "silverlib"
    cd $loc
    cp silverlib.jar ~/Library/silverlib/

    cd ~/
    if [ -f ".bash_profile" ]; then
      #Append
      echo -e "\nexport PATH=${PATH}:~/Library/silverlib/" >> .bash_profile
      echo -e "\nexport CLASSPATH=${CLASSPATH}:~/Library/silverlib/silverlib.jar" >> .bash_profile
    else
      #Create and add
      echo -e "\nexport PATH=${PATH}:~/Library/silverlib/" > .bash_profile
      echo -e "\nexport CLASSPATH=${CLASSPATH}:~/Library/silverlib/silverlib.jar" >> .bash_profile
    fi
  fi
else
  echo "We don't serve your kind"
fi
