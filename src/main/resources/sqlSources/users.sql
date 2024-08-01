-- UUID kengaytmasini o'rnatish
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Users jadvalini yaratish
CREATE TABLE users
(
    id          VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    name        VARCHAR(255) NOT NULL,
    age         INT CHECK (age > 0),
    email       VARCHAR(255) NOT NULL,
    username    VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL
);

-- Trigger funktsiyasini yaratish
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger yaratish
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

insert into users (name, username, email, age, password) values ('Ricardo', 'rboatman0', 'rgermann0@cyberchimps.com', 29, 'tC2`*$gm');
insert into users (name, username, email, age, password) values ('Richart', 'rgrinsted1', 'renga1@nyu.edu', 63, 'lH3"wz%TIsk');
insert into users (name, username, email, age, password) values ('Nataline', 'nbraiden2', 'ndodle2@cargocollective.com', 58, 'qQ7)XP?@R=GW)r');
insert into users (name, username, email, age, password) values ('Obidiah', 'oockleshaw3', 'ohuc3@domainmarket.com', 53, 'nQ0_u="''2L`Y');
insert into users (name, username, email, age, password) values ('Derk', 'dchatell4', 'ddumphry4@cafepress.com', 50, 'tS9|>>XE');
insert into users (name, username, email, age, password) values ('Atlanta', 'astains5', 'ahaycraft5@ebay.co.uk', 17, 'hF2<hg*M''kyh!Wg.');
insert into users (name, username, email, age, password) values ('Maren', 'mhulse6', 'mjanacek6@mashable.com', 35, 'aF4=Qa%M');
insert into users (name, username, email, age, password) values ('Wallace', 'wbalmer7', 'wtalton7@auda.org.au', 93, 'lO0*V68(CT@73');
insert into users (name, username, email, age, password) values ('Rubetta', 'rgladdis8', 'reliasson8@people.com.cn', 99, 'eH3.4jC`');
insert into users (name, username, email, age, password) values ('Nada', 'ncockcroft9', 'nriply9@mlb.com', 37, 'eY1)t_U($XBw3r=');
insert into users (name, username, email, age, password) values ('Emmy', 'ecalberta', 'egodmara@ezinearticles.com', 98, 'fJ3#"jtun<RO');
insert into users (name, username, email, age, password) values ('Mufi', 'mgerransb', 'mesbrookb@usda.gov', 49, 'vB3#.R5tU/9');
insert into users (name, username, email, age, password) values ('Elmore', 'etrokerc', 'esnellec@jugem.jp', 37, 'iE2*yAFF3o0B9');
insert into users (name, username, email, age, password) values ('Tobye', 'ttonnerd', 'tcottond@nydailynews.com', 22, 'nM0(RKqbE');
insert into users (name, username, email, age, password) values ('Truda', 'tkynee', 'tbrodeure@addtoany.com', 26, 'fC5`xwKaMVJO8');
insert into users (name, username, email, age, password) values ('Flo', 'fbramfordf', 'fclementsonf@zimbio.com', 15, 'pC4|l}?Yvi$Nt*/');
insert into users (name, username, email, age, password) values ('Sada', 'sjanssensg', 'smckendog@cargocollective.com', 64, 'bU3#*&y"Rt{Z');
insert into users (name, username, email, age, password) values ('Tiffanie', 'tdobbieh', 'tdecourcyh@blogtalkradio.com', 14, 'qS1"pl6ajv''0');
insert into users (name, username, email, age, password) values ('Belia', 'bfeaveri', 'bsidawayi@google.ru', 90, 'fN7.L=n*ah?`l');
insert into users (name, username, email, age, password) values ('Allix', 'ahemshallj', 'aocallaghanj@pen.io', 44, 'jP5!LyKx');

