#!/bin/bash

if [ ! -z "${CI}" ]; then
  ./gradlew -PdatabaseUrl='jdbc:postgresql://postgreshost:5432/devDb' nativeCompile
else
  ./gradlew nativeCompile
fi
cp build/native/nativeCompile/jooq-postgres .
