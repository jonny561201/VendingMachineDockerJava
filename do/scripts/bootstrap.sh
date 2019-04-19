#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
WORK_DIR=`mktemp -d -p "$DIR"`
FILE_NAME="PostGres.ps1"
FILE_PATH="$WORK_DIR/$FILE_NAME"


function cleanupTempDir {
    rm -rf "$WORK_DIR"
    echo "----------Deleted temp directory ----------"
}

function installFile {
    echo "----------Installing $FILE_NAME----------"
    INSTALL_CMD="@powershell $FILE_PATH -NoProfile -ExecutionPolicy unrestricted"
    echo "$INSTALL_CMD"
    cmd //c $INSTALL_CMD
}

if [[ ! "$WORK_DIR" || ! -d "$WORK_DIR" ]]; then
    echo "Could not create temp directory!"
    exit 1
else
    echo "----------Created Directory: $WORK_DIR"
    curl -L -o $FILE_PATH https://s3.amazonaws.com/pgcentral/install.ps1
    installFile
fi


#register cleanupTempDir function to get called on EXIT
trap cleanupTempDir EXIT