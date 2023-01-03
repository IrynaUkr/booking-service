FROM adoptopenjdk/openjdk11:ubi

COPY ["build/libs/booking-service-cinema.jar", "booking-service-cinema.jar" ]
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "booking-service-cinema.jar"]

