#!/bin/bash

WORK_DIR=`mktemp -d -p "$USERPROFILE"`
PSQL_USER="postgres"
PSQL_PASS="password"
FLYWAY_VERSION="5.2.4"
FLYWAY_TEMP_DIR="$WORK_DIR\flyway.zip"
FLYWAY_DIR="$USERPROFILE\flyway"
FLYWAY_LOC="$FLYWAY_DIR\flyway-$FLYWAY_VERSION"

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

function setEnvVars {
    echo "----------Flyway Env Vars----------"
    export PATH=$PATH:$FLYWAY_LOC
}

function migrateDatabase {
    echo "----------Migrating Database----------"
    flyway migrate -user=$PSQL_USER -password=$PSQL_PASS -url="jdbc:postgresql://localhost:5432/vending_machine"
}

function cleanupTempDir {
    echo "----------Cleanup Temp Dir----------"
    rm -rf $WORK_DIR
}

downloadFlyway
extractFlyway
setEnvVars
migrateDatabase

trap cleanupTempDir EXIT