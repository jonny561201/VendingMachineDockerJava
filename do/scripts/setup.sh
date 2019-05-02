#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
POSTGRES_USER="postgres"
POSTGRES_PASS="password"
SQL_FILE="Init.sql"
SQL_FILE_LOC="$DIR/$SQL_FILE"

function setEnvVars {
    echo "----------Env Vars----------"
    export PATH="$PATH:C:\Program Files\PostgreSQL\10\bin"
    export PGPASSWORD="$POSTGRES_PASS"
}

function createDatabase {
    echo "----------Creating Database----------"
    PSQL_CMD="psql -U $POSTGRES_PASS -f $SQL_FILE_LOC"
    cmd //c $PSQL_CMD
}

setEnvVars
createDatabase


