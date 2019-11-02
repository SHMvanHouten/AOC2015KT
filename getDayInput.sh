#!/bin/bash

if [[ -z "$1" ]]
then
echo "please enter a the number of the day you want to pull"
exit 0
fi

mkdir src/main/kotlin/com/github/shmvanhouten/adventofcode2015kt/Day$1
mkdir src/test/kotlin/com/github/shmvanhouten/adventofcode2015kt/Day$1

curl --cookie "_ga=GA1.2.577789105.1571511744; session=53616c7465645f5f89fdd0e25fabde8fef6a9b84d2c01ca677e84cc4f96f800b76f4185fc3e577c44c434d2c80c4ad6d; _gid=GA1.2.662718664.1572108320" https://adventofcode.com/2015/day/$1/input -o src/main/resources/input-day$1.txt -s

#curl https://adventofcode.com/2015/day/$1/input -o src/AOC/Day$1/challenge.html
