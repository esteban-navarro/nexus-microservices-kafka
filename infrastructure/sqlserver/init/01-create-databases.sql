IF DB_ID('nexus_user_db') IS NULL
BEGIN
    CREATE DATABASE nexus_user_db;
    PRINT 'Database nexus_user_db created successfully.';
END
ELSE
BEGIN
    PRINT 'Database nexus_user_db already exists.';
END
GO