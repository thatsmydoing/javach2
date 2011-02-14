#!/bin/sh

for i in {5..12}
do
  NUMBER=`printf "%02d" $i`
  NUMBER8=`printf "%02d" $((i+8))`
  NUMBER16=`printf "%02d" $((i+16))`
  NUMBER24=`printf "%02d" $((i+24))`
  sed "1 s/"$NUMBER"/"$NUMBER8"/;5 s/String \"txt\"/int 5/g;5 s/String txt/int num/g;5 s/\"txt\"/5/g;5 s/txt/num/g" < CPE_TYPARG_$NUMBER.java > CPE_TYPARG_$NUMBER8.java
  sed "1 s/"$NUMBER"/"$NUMBER16"/;5 s/String \"txt\",/int 5,/g;5 s/String txt,/int num,/g;5 s/\"txt\",/5,/g;5 s/txt,/num,/g" < CPE_TYPARG_$NUMBER.java > CPE_TYPARG_$NUMBER16.java
  sed "1 s/"$NUMBER"/"$NUMBER24"/;5 s/, String \"txt\"/, int 5/g;5 s/, String txt/, int num/g;5 s/, \"txt\"/, 5/g;5 s/, txt/, num/g" < CPE_TYPARG_$NUMBER.java > CPE_TYPARG_$NUMBER24.java
done