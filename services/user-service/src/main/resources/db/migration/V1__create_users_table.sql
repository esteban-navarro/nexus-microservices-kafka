CREATE TABLE users (
    id BIGINT IDENTITY(1,1) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    created_at DATETIME2 NOT NULL,
    updated_at DATETIME2 NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
);