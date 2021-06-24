#!/bin/bash

#组件名
COMPONENT_NAME=AsiaCloudShiro
DESTINATION=/program/$COMPONENT_NAME
PIDFILE=${DESTINATION}/${COMPONENT_NAME}.pid

JAR_DIR=/var/lib/jenkins/workspace/AsiaCloudShiro/target
JAR_FILE=`ls ${JAR_DIR} | grep "jar$"`

if test -z "${JAR_FILE}"
then
   echo "jar文件不存在，程序终止"
   exit 1
else
   echo "jar file exist!"
fi

mkdir -p ${DESTINATION}

cp ${JAR_DIR}/${JAR_FILE} ${DESTINATION}


####################################################
if [ -f $PIDFILE ]
   then 
     PID=$(cat $PIDFILE)
     echo "开始停止程序 ..."
     kill -15 "$PID" 
     rm "$PIDFILE"
     echo "程序已停止 ..."
   else
      echo "..."
fi	
##################################################
echo "开始运行程序 ..."

nohup java -jar ${DESTINATION}/${JAR_FILE} 2>&1 &

echo $! > ${PIDFILE}










