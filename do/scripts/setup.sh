#!/bin/bash


function setEnvVars {
    echo "----------Env Vars----------"
    export PATH="$PATH:C:\Program Files\PostgreSQL\10\bin"
}

function createDatabase {
    PSQL_CONNECTION_CMD="psql -U postgreSQL"
    cmd //c $PSQL_CONNECTION_CMD

    CREATE_DB_CMD="psql CREATE DATABASE VendingMachine WITH ENCODING 'UTF8'"
    cmd //c $CREATE_DB_CMD
}

setEnvVars
createDatabase


