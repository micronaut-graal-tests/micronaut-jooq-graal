# Micronaut JOOQ Graal #

Test application for Micronaut JOOQ and GraalVM that uses Postgres.

To run Postgres in Docker:

```
docker run -it --rm -p 5432:5432 -e POSTGRES_USER=devDb -e POSTGRES_PASSWORD=devDb -e POSTGRES_DB=devDb postgres:alpine
```

To test the application:

```
curl localhost:8080/owners
curl localhost:8080/owners/Fred

curl localhost:8080/pets
curl localhost:8080/pets/Dino
```
