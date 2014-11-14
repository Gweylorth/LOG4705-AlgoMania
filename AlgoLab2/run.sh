#!/bin/bash

indexes="abcdefghij"

for PROGRAM in 'vorace' 'retourArriere'
do
	echo "" > $PROGRAM.out
done 

for i in {10..30..4}
do
	for j in {4..10..2}
	do
		if [ $i -eq 10 ] && [ $j -eq 10 ];
		then
			echo "Skipping 10-10"
			continue
		fi
		for (( k=0; k<${#indexes}; k++ )); 
		do
			alpha=${indexes:$k:1}
			for PROGRAM in 'vorace' 'retourArriere'
			do
				echo "Current : $PROGRAM poset$i-$j$alpha"
				./$PROGRAM -p -f poset$i-$j$alpha >> $PROGRAM.out
			done 
		done
	done 
done
