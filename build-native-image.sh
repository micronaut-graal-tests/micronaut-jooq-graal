if [ ! -z "${CI}" ]; then
  ./gradlew -PdatabaseUrl='jdbc:postgresql://postgreshost:5432/devDb' assemble
else
  ./gradlew assemble
fi
native-image --no-server --no-fallback --class-path build/libs/micronaut-jooq-graal-*-all.jar
