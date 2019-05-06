#!/bin/bash

WORK_DIR=`mktemp -d -p "$USERPROFILE"`
FLYWAY_VERSION="5.2.4"
FLYWAY_TEMP_DIR="$WORK_DIR\flyway.zip"
FLYWAY_DIR="$USERPROFILE\flyway"

function downloadFlyway {
    if [[ ! "$WORK_DIR" || ! -d "$WORK_DIR" ]]; then
        echo "Could not create temp directory!"
        exit 1
    else
        echo "----------Downloading Flyway----------"
        curl -L -o $FLYWAY_TEMP_DIR "https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/$FLYWAY_VERSION/flyway-commandline-$FLYWAY_VERSION-windows-x64.zip"
    fi
}

function extractFlyway {
    echo "----------Extracting Flyway----------"
    unzip $FLYWAY_TEMP_DIR -d $FLYWAY_DIR
}

downloadFlyway
extractFlyway