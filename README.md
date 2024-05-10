## Scrabble Score Calculator

A very simple scrabble score calculator built with Next.js, Nest.js, and MongoDB.

## Running the db with Docker

```bash
# docker-compose up
$ docker-compose -p scrabble -f docker-compose.local.yml up -d

# docker-compose down
$ docker-compose -p scrabble -f docker-compose.local.yml down

# docker-compose down with volume cleaned
$ docker-compose -p scrabble -f docker-compose.local.yml down -v
```

## Running the app locally

```bash
# local profile
$ ./gradlew bootRun --args='--spring.profiles.active=local'
```

## Installation

```bash
$ ./gradlew build
```

## Checkstyle

```bash
$ ./gradlew checkstyleMain
```

## Test

```bash
# unit tests
$ ./gradlew test
```


