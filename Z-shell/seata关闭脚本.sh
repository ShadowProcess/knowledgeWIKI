#!/bin/bash

PIDFILE=seata.pid

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



