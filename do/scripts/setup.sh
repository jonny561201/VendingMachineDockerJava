#!/bin/bash

#DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
POSTGRES_VERSION="pg10"

function installPostGres {
    echo "----------Installing PostGres----------"
    POSTGRES_INSTALL_CMD="pgc install $POSTGRES_VERSION"
    cmd //c $POSTGRES_INSTALL_CMD
}

function startSqlService {
    echo "----------Starting Sql Service----------"
    START_CMD="pgc start $POSTGRES_VERSION"
    cmd //c $START_CMD
}

TEST="$(cd  "$(dirname "$0")" && pwd)"
ROOT_DIR=${TEST}/../../
cd ${ROOT_DIR}bigsql/
#cmd //c $"pgc help"

