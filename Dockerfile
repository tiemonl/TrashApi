FROM gradle:7.5-jdk17-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM alpine as git
WORKDIR /tmp
RUN apk update
RUN apk add git
RUN git clone https://github.com/TRaSH-/Guides.git

FROM openjdk:17-jdk-alpine
WORKDIR /app
EXPOSE 4444
COPY --from=builder /builder/build/libs/trashApi.jar .
COPY --from=git /tmp/Guides ./guide
CMD ["java", "-jar", "trashApi.jar"]