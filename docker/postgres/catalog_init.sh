set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER $CATALOG_DB_USER WITH PASSWORD '$CATALOG_DB_PASSWORD';
    CREATE DATABASE $CATALOG_DB_NAME;
    GRANT ALL PRIVILEGES ON DATABASE $CATALOG_DB_NAME TO $CATALOG_DB_USER;
    ALTER DATABASE $CATALOG_DB_NAME OWNER TO $CATALOG_DB_USER;
EOSQL