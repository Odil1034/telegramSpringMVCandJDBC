-- UUID kengaytmasini o'rnatish
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Chats jadvalini yaratish
CREATE TABLE chats
(
    id         VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    user1_id   varchar REFERENCES telegramspringjdbc.public.users,
    use21_id   varchar REFERENCES telegramspringjdbc.public.users

);

-- Trigger funktsiyasini yaratish
CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger yaratish
CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON chats
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
