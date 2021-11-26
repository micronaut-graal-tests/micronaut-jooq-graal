#!/bin/bash

./gradlew nativeCompile
cp build/native/nativeCompile/jooq-h2 .
