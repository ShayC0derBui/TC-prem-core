# Use the specified Maven image
FROM jelastic/maven:3.9.5-openjdk-20.0.2

# Set the working directory inside the container
WORKDIR /app

# Copy only the necessary files for building (improves caching)
COPY pom.xml .
COPY .env .
COPY src ./src

# Build the application with Maven
RUN mvn clean install

# Expose the port on which the Spring Boot application will run
EXPOSE 8173

# Command to run the Spring Boot application
CMD ["java", "-jar", "target/TP-Trading-Core-1.0-SNAPSHOT.jar"]

