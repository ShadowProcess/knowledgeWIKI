#!/bin/bash

PIDFILE=seata.pid

 if [ -f $PIDFILE ]
        then
                echo "$PIDFILE exists, process is already running or crashed"
        else
                echo "Starting $COMP_NAME server..."
                nohup sh bin/seata-server.sh > logs/seata.log 2>&1 &
                echo $! > "$PIDFILE"
                echo "[Start to finish]"
        fi

