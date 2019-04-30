#!/bin/bash

BOOTSTRAP_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
WORK_DIR=`mktemp -d -p "$BOOTSTRAP_DIR"`
FILE_NAME="PostGres.exe"
FILE_PATH="$WORK_DIR/$FILE_NAME"


function cleanupTempDir {
    rm -rf "$WORK_DIR"
    echo "----------Deleted temp directory ----------"
}

function downloadInstaller {
    echo "----------Downloading $FILE_NAME----------"
    curl -L -o $FILE_PATH https://get.enterprisedb.com/postgresql/postgresql-10.7-2-windows-x64.exe
    installPostGres
}

function installPostGres {
    echo "----------Installing PostGres----------"
    INSTALL_CMD="$FILE_PATH  --unattendedmodeui minimal --mode unattended --superpassword "password" --servicename "postgreSQL" --servicepassword "password" --serverport 5432"
    cmd //c $INSTALL_CMD
}

if [[ ! "$WORK_DIR" || ! -d "$WORK_DIR" ]]; then
    echo "Could not create temp directory!"
    exit 1
else
    echo "----------Created Directory: $WORK_DIR"
    downloadInstaller
fi


#register cleanupTempDir function to get called on EXIT
trap cleanupTempDir EXIT