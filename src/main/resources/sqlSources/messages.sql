-- UUID kengaytmasini o'rnatish
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- Messages jadvalini yaratish
CREATE TABLE messages
(
    id         VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    text       TEXT,
    owner_id   VARCHAR REFERENCES users (id),
    chat_id    VARCHAR REFERENCES chats (id)
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
    ON messages
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

insert into messages (text, owner_id, chat_id) values ('posuere cubilia curae mauris', 'b68ba678-d315-43a1-9558-9c736495d18a', 'b1df1e58-bc81-4fd4-b91c-624dec3f28d5');
insert into messages (text, owner_id, chat_id) values  ('nisi at nibh in', 'b68ba678-d315-43a1-9558-9c736495d18a', 'b1df1e58-bc81-4fd4-b91c-624dec3f28d5');
insert into messages (text, owner_id, chat_id) values ('in felis eu', 'b68ba678-d315-43a1-9558-9c736495d18a', 'b1df1e58-bc81-4fd4-b91c-624dec3f28d5');
insert into messages (text, owner_id, chat_id) values  ('quam nec dui luctus rutrum', 'b68ba678-d315-43a1-9558-9c736495d18a', 'b1df1e58-bc81-4fd4-b91c-624dec3f28d5');
insert into messages (text, owner_id, chat_id) values  ('rhoncus mauris', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3','b1df1e58-bc81-4fd4-b91c-624dec3f28d5');
insert into messages (text, owner_id, chat_id) values  ('eget elit sodales scelerisque mauris', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3','b1df1e58-bc81-4fd4-b91c-624dec3f28d5');
insert into messages (text, owner_id, chat_id) values  ('adipiscing lorem vitae', 'b660ac00-0865-4955-a3b0-786fa5d8389e', 'b8c1e171-b559-4973-aa77-e58bc5e54158');
insert into messages (text, owner_id, chat_id) values ('vel est donec', 'b660ac00-0865-4955-a3b0-786fa5d8389e', 'b8c1e171-b559-4973-aa77-e58bc5e54158');
insert into messages (text, owner_id, chat_id) values ('vel nisl duis', 'b660ac00-0865-4955-a3b0-786fa5d8389e', 'b8c1e171-b559-4973-aa77-e58bc5e54158');
insert into messages (text, owner_id, chat_id) values ('ligula sit amet eleifend pede', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3', 'b8c1e171-b559-4973-aa77-e58bc5e54158');
insert into messages (text, owner_id, chat_id) values  ('proin at turpis a pede', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3','df33595a-b393-4e50-9bad-58f81b73179d');
insert into messages (text, owner_id, chat_id) values  ('id mauris', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3','df33595a-b393-4e50-9bad-58f81b73179d');
insert into messages (text, owner_id, chat_id) values ('in leo', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3','df33595a-b393-4e50-9bad-58f81b73179d');
insert into messages (text, owner_id, chat_id) values ('interdum eu tincidunt in leo', 'ccd9f388-16ab-4f4b-96e6-08ddd64453b4', 'df33595a-b393-4e50-9bad-58f81b73179d');
insert into messages (text, owner_id, chat_id) values  ('quam a odio in', 'ccd9f388-16ab-4f4b-96e6-08ddd64453b4', 'df33595a-b393-4e50-9bad-58f81b73179d');
insert into messages (text, owner_id, chat_id) values  ('id lobortis convallis tortor', 'ccd9f388-16ab-4f4b-96e6-08ddd64453b4', 'df33595a-b393-4e50-9bad-58f81b73179d');
insert into messages (text, owner_id, chat_id) values  ('viverra diam vitae quam suspendisse',  'b0d93d6b-743c-4a52-88dd-a01a4e5b28fe', 'da64fa4a-115b-4c8f-a3c2-b9e8760f35dc');
insert into messages (text, owner_id, chat_id) values  ('in felis eu', 'b0d93d6b-743c-4a52-88dd-a01a4e5b28fe', 'da64fa4a-115b-4c8f-a3c2-b9e8760f35dc');
insert into messages (text, owner_id, chat_id) values ('etiam pretium iaculis justo in', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3','da64fa4a-115b-4c8f-a3c2-b9e8760f35dc');
insert into messages (text, owner_id, chat_id) values ('integer a', '344f24a5-a1ad-4e28-9b95-cf3931b3dbe3','da64fa4a-115b-4c8f-a3c2-b9e8760f35dc');


