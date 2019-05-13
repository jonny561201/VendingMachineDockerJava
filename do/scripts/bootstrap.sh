#!/bin/bash

WORK_DIR=`mktemp -d -p "$USERPROFILE"`
DOCKER_TEMP_DIR="$WORK_DIR\docker.exe"

function downloadDocker {
    if [[ ! "$WORK_DIR" || ! -d "$WORK_DIR" ]]; then
        echo "Could not create temp directory!"
        exit 1
    else
        echo "----------Downloading Docker----------"
        curl -L -o $DOCKER_TEMP_DIR "https://download.docker.com/win/stable/Docker%20for%20Windows%20Installer.exe"
    fi
}

function installingDocker {
    echo "----------Installing Docker----------"
    TEST_LOC="$DOCKER_TEMP_DIR/docker.exe"
    INSTALL_CMD="$DOCKER_TEMP_DIR"
    cmd //c $INSTALL_CMD
}

function cleanupTempDir {
    echo "----------Cleanup Temp Dir----------"
    rm -rf $WORK_DIR
}

if [[ $PATH == *"docker"* ]]; then
    echo "----------Docker Already Installed----------"
else
    downloadDocker
    installingDocker
fi


#trap cleanupTempDir EXIT