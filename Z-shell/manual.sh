#!/bin/bash

FILEDIR=`pwd`
_jar=`ls lib | grep "..*\.jar$"`
_classpath=". ${_jar}"
classpath=`echo ${_classpath} | sed -e 's/ /:lib\//g'`
javaArgs="-server -Xms1024m -Xmx1024m -Djava.awt.headless=true"

java ${javaArgs} -classpath ${classpath} com.zouqi.dataflow.Manual ${FILEDIR}/$1
