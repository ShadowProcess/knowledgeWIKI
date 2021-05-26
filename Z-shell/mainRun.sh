#!/bin/bash

COMP_NAME=dataflow-pretreatment
PIDFILE_BASE=/u/ll/component/$COMP_NAME
PIDFILE=${PIDFILE_BASE}/${COMP_NAME}.pid

_jar=`ls lib | grep "..*\.jar$"`
_classpath=". ${_jar}"
classpath=`echo ${_classpath} | sed -e 's/ /:lib\//g'`
#javaArgs="-server -Xms512m -Xmx1024m -Djava.awt.headless=true -javaagent:lib/springloaded.jar -noverify"
javaArgs="-server -Xms2048m -Xmx2048m -Djava.awt.headless=true"

case "$1" in
    start)
        if [ -f $PIDFILE ]
        then
                echo "$PIDFILE exists, process is already running or crashed"
        else
                echo "Starting $COMP_NAME server..."
                nohup java ${javaArgs} -classpath ${classpath} com.zouqi.dataflow.Main $COMP_NAME 2>&1 &
                echo $! > "$PIDFILE"
        fi
        if [ "$?"="0" ]
        then
              echo " is running..."
        fi
        ;;
    stop)
        if [ ! -f $PIDFILE ]
        then
                echo "$PIDFILE does not exist, process is not running"
        else
                PID=$(cat $PIDFILE)
                echo "Stopping ..."
                kill -15 "$PID"
                rm "$PIDFILE"
                echo "Component $COMP_NAME stopped"
        fi
        ;;
  *)
    echo "Usage: ./run_app.sh {start|stop}" >&2
        exit 1
esac
