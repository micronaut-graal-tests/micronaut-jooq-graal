./gradlew assemble
native-image --no-fallback --class-path build/libs/micronaut-jooq-graal-*-all.jar
