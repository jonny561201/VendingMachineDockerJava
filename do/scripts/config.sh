#!/bin/bash

DATABASE_VERSION="1.1"

PRESENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
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
    MIGRATIONS_DIR="filesystem:..\..\db\migration"
    pushd $PRESENT_DIR
    flyway migrate -user=$PSQL_USER -password=$PSQL_PASS -url="jdbc:postgresql://localhost:5432/vending_machine" -target=$DATABASE_VERSION -locations=$MIGRATIONS_DIR
    popd
}

function cleanupTempDir {
    echo "----------Cleanup Temp Dir----------"
    rm -rf $WORK_DIR
}

if [[ $PATH == *"flyway"* ]]; then
    echo "----------Flyway Already Installed----------"
    migrateDatabase
else
    downloadFlyway
    extractFlyway
    setEnvVars
    migrateDatabase
fi


trap cleanupTempDir EXIT