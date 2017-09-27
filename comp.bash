#!/bin/bash
DATE=`date '+%Y-%m-%d %H:%M:%S'`
loc="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

cd $loc

function main {
  #Build
  echo "Building Library..."
  ant -S
  
  #Move javadocs to silverlibDoc
  echo "Migrating JavaDocs..."
  cp -r doc/* ../silverlibDoc/
  
  #Copy jar to dist
  echo "Moving Jar..."
  cp lib/silverlib.jar dist/
  
  #Zip dist and move it to silverlibDoc
  echo "Creating distribution file..."
  zip -r ../silverlibDoc/Silverlib.zip dist
  
  #Remove doc&build
  echo "Cleaning directories..."
  ant -S clean
}

function buildOnly {
  #Build
  echo "Building Library..."
  ant -S
  
  #Remove doc,build,and dist
  echo "Cleaning directories..."
  ant -S clean
}

function pushDoc {
  cd ../silverlibDoc
  
  echo "Pushing JavaDocs..."
  git add .
  git commit -m "$DATE UPDATE"
  git push -u origin master
}

function pushIt {
  echo "Pushing source with commit message $1..."
  git add .
  git commit -m $1
  git push -u origin master
}

function zipIt {
  #Zip dist and move it to silverlibDoc
  echo "Creating distribution file..."
  zip -r ../silverlibDoc/Silverlib.zip dist
}

function versionUpdate {
  echo "Modifying version number and info..."
  echo -e "$1;$2" > "dist/info.txt"
}


if [[ $# == 0 ]]; then
  main
  pushDoc
elif [[ $1 == "-b" ]]; then
  buildOnly
elif [[ $1 == "-d" ]]; then
  pushDoc
elif [[ $1 == "-p" ]]; then
  pushIt $2
elif [[ $1 == "-z" ]]; then
  zipIt
elif [[ $1 == "-v" ]]; then
  versionUpdate "$2" "$3"
elif [[ $1 == "-h" ]]; then
  echo -e "No args: Build library, copy javadocs to silverlibDoc, create dowload zip,\nremove extra directories, push silverlibDoc"
  echo -e "-b: Only build the library"
  echo -e "-d: Push silverlibDoc"
  echo -e "-p: Push the Library source"
  echo -e "-z: Create a zipped dist"
  echo -e "-v: Update the version, where  arg 1 is version number and arg 2 is version info"
fi
