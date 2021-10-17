#
# Build react for 'prod'
#
FROM node:16-alpine3.14 as build_npm
COPY /frontend /home/app/frontend/
WORKDIR /home/app/frontend
RUN npm run build
RUN ls
WORKDIR /home/app/frontend/build
RUN ls

#
# Build spring boot jar
#
FROM maven:3.6.0-jdk-11-slim AS build_maven
COPY /backend /home/app/backend
WORKDIR /home/app/backend
COPY --from=build_npm /home/app/frontend/build /home/app/backend/target/classes/static
RUN mvn package


FROM openjdk:11-jre-slim
COPY --from=build_maven /home/app/backend/target/cake-code-test-0.0.1-SNAPSHOT.jar /usr/local/lib/cake-code-test.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/cake-code-test.jar"]
