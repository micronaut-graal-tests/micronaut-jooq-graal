#!/bin/bash

if [ ! -z "${CI}" ]; then
  ./gradlew -PdatabaseUrl='jdbc:postgresql://postgreshost:5432/devDb' nativeImage
else
  ./gradlew nativeImage
fi
cp build/native-image/jooq-postgres .
