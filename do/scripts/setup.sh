#!/bin/bash


function setEnvVars {
    echo "----------Env Vars----------"
    export PATH="$PATH:C:\Program Files\PostgreSQL\10\bin"
}

function createDatabase {
    PSQL_CMD="psql --help"
    cmd //c $PSQL_CMD
}

setEnvVars
createDatabase


