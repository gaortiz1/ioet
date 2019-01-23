# ioet

### Architecture

ACME uses Domain Driven Design(DDD)

### Tech

Acme uses a number of open source projects:

* [Spring boot]
* [Lombok] 

### Installation

ACME requires Java 8 to run.

Install the project.
```sh
$ cd payments
$ mvn clean install
```
### Run project

```sh
$ cd target
$ java -jar payment-core-1.0-SNAPSHOT.jar "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00"
```
