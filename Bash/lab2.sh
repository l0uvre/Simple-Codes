#!/bin/bash
#current_dir=""
origin_pos=$HOME
declare -i file_num=0
declare -i dir_num=0
#origin_pos=$(pwd)
cd "/home" 
function file_info {
cd $1;
current_dir=$1
#current_dir=$(pwd)
real_dir=$(pwd)
printf "[ $current_dir ]\n"

for file in *
do
  if [ -d $file ]
  then
    ((dir_num++))
    #file_info "$file"
    printf "${real_dir:5}/$file\n"
    #printf "[ $file ]\n"
  else
    if [ -e $file ]
    then
      ((file_num++))
      printf "${real_dir:5}/$file\n"
    fi
  fi

done

printf "\n"

for file in *
do
if [ -d $file ] 
then 
  file_info $file
  cd ..
fi
done
}

file_info ${origin_pos:6}
cd $origin_pos
printf "[ Directories Count ] : $dir_num\n"
printf "\n"
printf "[ Files Count ] : $file_num\n"
printf "\n"
