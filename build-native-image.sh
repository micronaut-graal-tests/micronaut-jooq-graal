#!/bin/bash
./gradlew nativeImage
cp build/native-image/jooq-h2 .
