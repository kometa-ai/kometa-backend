FROM gcr.io/distroless/java21-debian12:latest
COPY build/libs/kometa-backend-0.0.1.jar ./kometa-backend.jar
CMD ["kometa-backend.jar"]
