# angler-server

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info

## Technologies
#### Project is created with:
* Spring Boot Web Starter           2.1.7.RELEASE
* Spring Boot Security Starter      2.1.7.RELEASE
* Spring Data JPA with Hibernate    2.1.7.RELEASE
* Spring Security JWT               1.0.7.RELEASE
* Spring Security OAuth2            2.1.0.RELEASE
* Spring Boot Mail                  2.1.0.RELEASE
* Spring Boot Thymeleaf             2.1.0.RELEASE
* H2 Database                       1.4.199
* Jackson Databind                  2.10.0
	
## Setup
### Build Spring Boot Project with Maven
```
$ maven package
$ mvn install
```

### Run Spring Boot app with java -jar command
```
$ java -jar target/fishlog-0.0.1-SNAPSHOT.jar
```

### Run Spring Boot app using Maven
```
$ mvn spring-boot:run
```