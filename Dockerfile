# Usar Java 17 JDK
FROM eclipse-temurin:17-jdk

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR construido desde el directorio build/libs
COPY ./build/libs/Herrera_Promotion-0.0.1-SNAPSHOT.jar ./Herrera_Promotion.jar

# Puerto donde escucha la aplicación
EXPOSE 8080

# Ejecutar la aplicación con el perfil docker
ENTRYPOINT ["java", "-jar", "Herrera_Promotion.jar", "--spring.profiles.active=docker"]
