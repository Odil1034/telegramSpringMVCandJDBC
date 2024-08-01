-- UUID kengaytmasini o'rnatish
CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

-- Uploads jadvalini yaratish
CREATE TABLE uploads
(
    id             VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at     TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    generated_name varchar not null,
    extension      varchar not null,
    mimeType       varchar not null,
    original_name  varchar not null

);

-- Trigger funktsiyasini yaratish
CREATE
    OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at
        = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

-- Trigger yaratish
CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON uploads
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
