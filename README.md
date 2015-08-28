In PSQL:
CREATE DATABASE hair_salon;
\c hair_salon;
CREATE TABLE clients (id serial PRIMARY KEY, clientName varchar, stylistId int);
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;


Program name: Hair Salon Author name: Jennifer Morkunas This is a web application that lets a user enter stylist names and add clients for each stylist and stores them both in a database. Users are able to add multiple clients for each stylist. Simply follow instructions above each of the submittal forms to start using the app.
