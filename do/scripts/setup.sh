#!/bin/bash

CURRENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
POSTGRES_USER="postgres"
POSTGRES_PASS="password"
SQL_FILE="Init.sql"
SQL_FILE_LOC="$CURRENT_DIR/$SQL_FILE"

function setEnvVars {
    echo "----------Postgres Env Vars----------"
    export PATH="$PATH:C:\Program Files\PostgreSQL\10\bin"
    export PGPASSWORD="$POSTGRES_PASS"
}

function createDatabase {
    echo "----------Creating Database----------"
    PSQL_CMD="psql -U $POSTGRES_USER -f $SQL_FILE_LOC"
    cmd //c $PSQL_CMD
}

setEnvVars
createDatabase


