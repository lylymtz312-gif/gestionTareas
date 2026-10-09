# Task Manager REST API

Servicio REST desarrollado con **Java 21**, **Spring Boot 3** y **MySQL**.

## Requisitos previos
- JDK 21 instalado.
- Maven o Wrapper de Maven (`./mvnw`).
- Servidor MySQL activo localmente o mediante Docker.

## Cómo compilar y ejecutar localmente
1. Clonar el repositorio.
2. Ejecutar el script SQL proporcionado (`schema.sql`) en tu instancia MySQL.
3. Configurar tus credenciales en `src/main/resources/application.yml`.
4. Compilar y correr la aplicación:
   ```bash
   ./mvnw clean spring-boot:run