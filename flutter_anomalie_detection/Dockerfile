# Use the official Flutter image as the base image
FROM cirrusci/flutter:stable

# Set the working directory
WORKDIR /app

# Copy the pubspec files and get dependencies
COPY pubspec.yaml pubspec.lock ./
RUN flutter pub get

# Copy the rest of the application code
COPY . .

# Ensure the Flutter dependencies are up to date
RUN flutter pub get

# Expose the port the app runs on
EXPOSE 8080

# Run the Flutter application
CMD ["flutter", "run", "-d", "web-server", "--web-port", "8080", "--web-hostname", "0.0.0.0"]