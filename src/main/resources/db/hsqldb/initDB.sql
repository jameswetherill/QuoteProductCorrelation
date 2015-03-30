DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE owners IF EXISTS;


DROP TABLE topic IF EXISTS;
DROP TABLE quote IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE product_topic IF EXISTS;
DROP TABLE quote_topic IF EXISTS;
DROP TABLE emotiontype IF EXISTS;
DROP TABLE quote_product_connector  IF EXISTS;
DROP TABLE effect_words IF EXISTS;
DROP TABLE effect_words_emotions IF EXISTS;

CREATE TABLE emotiontype (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
--CREATE INDEX emotiontype_name ON emotiontype (name);

CREATE TABLE topic(
id INTEGER IDENTITY PRIMARY KEY,
emotiontype_id INTEGER,
description varchar(256),
weight double
);
ALTER TABLE topic ADD CONSTRAINT fk_topic_emotion FOREIGN KEY (emotiontype_id) REFERENCES emotiontype (id);

CREATE TABLE quote(
	id INTEGER IDENTITY PRIMARY KEY,
	description varchar(1024),
	author varchar(256)
);

CREATE TABLE product(
	id INTEGER IDENTITY PRIMARY KEY,
	description varchar(1024),
	retailer varchar(256)
);

CREATE TABLE product_topic (
  product_id       INTEGER NOT NULL,
  topic_id 		   INTEGER NOT NULL
);
ALTER TABLE product_topic ADD CONSTRAINT fk_product_topic_products FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE product_topic ADD CONSTRAINT fk_product_topic_topics FOREIGN KEY (topic_id) REFERENCES topic (id);

CREATE TABLE quote_topic (
  quote_id       INTEGER NOT NULL,
  topic_id 		   INTEGER NOT NULL
);
ALTER TABLE quote_topic ADD CONSTRAINT fk_quote_topic_quotes FOREIGN KEY (quote_id) REFERENCES quote (id);
ALTER TABLE quote_topic ADD CONSTRAINT fk_quote_topic_topics FOREIGN KEY (topic_id) REFERENCES topic (id);




CREATE TABLE quote_product_connector(
	id   INTEGER IDENTITY PRIMARY KEY,
	quote_id       INTEGER NOT NULL,
  	product_id INTEGER NOT NULL
);
ALTER TABLE quote_product_connector ADD CONSTRAINT fk_quotes_product_connector FOREIGN KEY (quote_id) REFERENCES quote (id);
ALTER TABLE quote_product_connector ADD CONSTRAINT fk_product_quotes_connector FOREIGN KEY (product_id) REFERENCES product (id);

CREATE TABLE effect_words(
	id   INTEGER IDENTITY PRIMARY KEY,
  	name VARCHAR(80)
);


CREATE TABLE effect_words_emotions(
	effect_words_id       INTEGER NOT NULL,
  	emotiontype_id 		   INTEGER NOT NULL
);

ALTER TABLE effect_words_emotions ADD CONSTRAINT fk_emotions_effect_words FOREIGN KEY (effect_words_id) REFERENCES effect_words (id);
ALTER TABLE effect_words_emotions ADD CONSTRAINT fk_effect_words_emotions FOREIGN KEY (emotiontype_id) REFERENCES emotiontype (id);



CREATE TABLE vets (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE specialties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE owners (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE pets (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type_id    INTEGER NOT NULL,
  owner_id   INTEGER NOT NULL
);
ALTER TABLE pets ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE visits (
  id          INTEGER IDENTITY PRIMARY KEY,
  pet_id      INTEGER NOT NULL,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id ON visits (pet_id);
