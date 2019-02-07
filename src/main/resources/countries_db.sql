--

-- PostgreSQL database dump
--

-- DROP DATABASE IF EXISTS countries;
--
-- CREATE DATABASE countries;
-- \c countries
-- CREATE SCHEMA cd;
--
-- SET client_encoding = 'UTF8';
-- SET standard_conforming_strings = on;
-- SET check_function_bodies = false;
-- SET client_min_messages = warniSng;


CREATE TABLE IF NOT EXISTS country
(
  country_id      serial PRIMARY KEY,
  name            VARCHAR(30) UNIQUE NOT NULL,
  population      integer,
  area            integer,
  capital         VARCHAR(30),
  GDP             integer,
  government_type VARCHAR(30),
  currency        VARCHAR(5)         NOT NULL,
  internet_domain VARCHAR(5)
);

CREATE TABLE IF NOT EXISTS lang
(
  lang_id  serial PRIMARY KEY,
  langName VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS religion
(
  religion_id  serial PRIMARY KEY,
  religionName VARCHAR(30)
);


CREATE TABLE IF NOT EXISTS country_lang
(
  country_id integer REFERENCES country,
  lang_id    integer REFERENCES lang
);

CREATE TABLE IF NOT EXISTS country_religion
(
  country_id  integer REFERENCES country,
  religion_id integer REFERENCES religion
);


INSERT INTO country(name, population, area, capital, GDP, government_type,
                    currency, internet_domain)
VALUES ('Polska', 38000000, 200000, 'Warsaw', 23200000, 'democratic republic',
        'PLN', 'pl'),
       ('United Kingdom', 80000000, 150000, 'London', 800000000, 'monarchy', 'GBP', 'uk'),
       ('Ukraine', 60000000, 250000, 'Kiev', 15000000, 'democratic republic', 'UAH', 'ua');


INSERT INTO religion(religionName)
VALUES ('kato'),
       ('islam'),
       ('orthodox christian');

INSERT INTO lang(langName)
VALUES ('english'),
       ('polish'),
       ('ukrainian'),
       ('scots'),
       ('russian');


INSERT INTO country_lang (country_id, lang_id)
VALUES (1, 2),
       (2, 1),
       (2, 4),
       (3, 3),
       (3, 5);

INSERT INTO country_religion (country_id, religion_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 3);

