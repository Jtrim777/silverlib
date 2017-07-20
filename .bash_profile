#Startup
echo "Welcome, Jake"

#Environment
export CLICOLOR=1
export LSCOLORS=GxFEBBxxxxegedabagacad
export ANT_HOME=/Users/jake2/Library/Ant
export PATH=/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Users/jake2/Library/Ant/bin:/Users/jake2/Stuff/Creations/Programming/silverlib/lib
export CLASSPATH=${CLASSPATH}:/Users/jake2/Stuff/Creations/Programming/silverlib/lib/silverlib.jar

#General Commands
alias home='cd ~'
alias dsk='cd ~/Desktop'
alias tocode='cd ~/Stuff/Creations/Programming'
alias ..='cd ../'                           # Go back 1 directory level
alias .2='cd ../../'                        # Go back 2 directory levels
alias .3='cd ../../../'                     # Go back 3 directory levels
alias .4='cd ../../../../'                  # Go back 4 directory levels
alias .5='cd ../../../../../'               # Go back 5 directory levels
alias .6='cd ../../../../../../'            # Go back 6 directory levels
alias helpme='./.cmdhelp.bash'              # Lists all user added commands
alias bfile='atom ~/.bash_profile'       # Opens this file

#Git Commands
alias ga='git add'
alias gc='git commit -m'
alias clone='~/Desktop/.clone.bash'
alias gp='git push origin master'
alias gitgo='~/.git_process.bash'

#Project Specific Commands
alias lang='~/Stuff/Creations/Programming/SilvianTranslator/scripts/r.bash'
alias book='atom /Users/jake2/Library/Mobile\ Documents/com\~apple\~CloudDocs/Books/Mertheria/Mer'
alias bookpack='atom /Users/jake2/.atom/packages/language-book'
alias booktheme='atom /Users/jake2/.atom/packages/book-syntax'
alias updatelib='~/.updatelib.bash'
alias proj='~/.openProject.bash'


#List of aliases
#in helpme | Shows this list
#in bfile  | Opens the bash profile in Atom
#in hm     | Changes working directory to user home
#in dsk    | Changes working directory to Desktop
#in tocode | Changes working directory to Programming folder in Stuff
#in ..     | Goes back 1 directory level
#in .2     | Goes back 2 directory levels
#in .3     | Goes back 3 directory levels
#in .4     | Goes back 4 directory levels
#in .5     | Goes back 5 directory levels
#in .6     | Goes back 6 directory levels
#in ga     | Adds $1 to the staging platform for GitLab
#in gc     | Commits staging platform to GitLab with message $1
#in gp     | Pushes to GitLab branch master
#in gitgo  | Runs the whole Git process with commit message $1 and optionally branch $2
#in clone  | Clones from GitLab repository $1
#in lang   | Runs the main program for SilvianTranslator (Use -l on helpme to get options)

#List of Silvian Translator commands
#lng main   | Translates the text in Input.txt into Silvian in Translated.txt
#lng learn  | Adds $1 to English and $2 at the same spot in Silvian
#lng learno | Same as above but forces in case word already exists
#lng locate | Gives location of $1 in database $2
#lng wordat | Gives word at location $1 in database $2
#lng same   | Gives Silvian form of $1 (Must be one word)
#lng text   | Translates $1 into Silvian
