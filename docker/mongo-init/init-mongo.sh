#!/bin/sh
set -eu

log() {
  printf '%s %s\n' "mongo-init:" "$1"
}

database_name="${BU_JOURNAL_MONGO_DB_DATABASE:-bu-journal}"
username="${BU_JOURNAL_MONGO_DB_USERNAME:-bu-journal}"
password="${BU_JOURNAL_MONGO_DB_PASSWORD:-password}"
root_username="${MONGO_INITDB_ROOT_USERNAME:?MONGO_INITDB_ROOT_USERNAME is required}"
root_password="${MONGO_INITDB_ROOT_PASSWORD:?MONGO_INITDB_ROOT_PASSWORD is required}"

log "starting initialization"
log "target database: ${database_name}"
log "target user: ${username}"
log "creating application user"

if mongosh --authenticationDatabase admin \
  --username "$root_username" \
  --password "$root_password" \
  --eval "
    db = db.getSiblingDB('$database_name');
    db.createUser({
      user: '$username',
      pwd: '$password',
      roles: [{ role: 'readWrite', db: '$database_name' }]
    });
  "
then
  log "application user created successfully"
else
  log "failed to create application user"
  exit 1
fi
