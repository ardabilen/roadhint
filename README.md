# roadhint
This project provides RESTful API to serve an application such that users share road conditions and photos.
There is mockup on marvel for this application: https://marvelapp.com/306hd0b

<img src="http://gdurl.com/ZvyD" width="200">

## Tech/Framework Used
- Spring Boot
- Hibernate
- PostgreSql
- JUnit
- Mockito

## Features
Provides RESTful web services for persist spatial data and retrieve clustered locations inside a given area.
It clusters map markers(locations) on server side by using Postgis DBSCAN(density-based spatial cluster of applications with noise) algorithm.


It is a density-based clustering algorithm: given a set of points in some space, 
it groups together points that are closely packed together.
https://en.wikipedia.org/wiki/DBSCAN

## Api Documentation
Api documentation is written in swagger format.
Inorder to visualize it, put **swagger.json** file into https://editor.swagger.io/

## System Requirements
- Java 1.8 JDK
- PostgreSQL 9.5
- PostGIS 2.3.2
