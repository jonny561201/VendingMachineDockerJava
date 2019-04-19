#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

function isAdmin {
    net session > /dev/null 2>&1
    if [ $? -eq 0 ]
        then
            return 0
        else
            return 1
    fi
}

if isAdmin $1
    then
        echo "----------terminal is running as admin----------"
        source "$DIR/scripts/bootstrap.sh"
    else
        echo "Terminal must be running as Administrator. Open new terminal."
fi