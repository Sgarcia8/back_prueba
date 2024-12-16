# Backend del Proyecto

Este documento describe el backend de la aplicación, que proporciona las funcionalidades necesarias para gestionar usuarios, autenticación y roles. El backend está desarrollado en **Spring Boot** y utiliza un enfoque RESTful para la comunicación con el frontend.

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.4.0**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Hibernate y JPA** (para la gestión de base de datos)
- **SQLserver** (base de datos relacional)
- **Maven** (gestión de dependencias)
- **Postman** (para pruebas de la API)

## Instalación

### Prerrequisitos

1. Tener instalado **Java 21**
2. Instalar **Maven**.
3. Configurar un servidor de base de datos MySQL.
4. Configurar variables de entorno si es necesario (ver apartado de configuración).

### Clonar el repositorio

```bash
git clone https://github.com/Sgarcia8/back_prueba.git
cd backend
```

### Configurar el archivo `application.properties`

Ubica el archivo `src/main/resources/application.properties` y ajusta los siguientes valores:

```properties

#configuracion base de datos
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=prueba;encrypt=true;trustServerCertificate=true
spring.datasource.username=USUARIO_BASE_DE_DATOS
spring.datasource.password=CONTRASEÑA
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

#Configuración de JPA

spring.jpa.hibernate.ddl-auto=validate


```

### Construcción y ejecución

1. Compilar el proyecto:
   ```bash
   mvn clean install
   ```

2. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en: `http://localhost:8080`

## Endpoints principales

### Autenticación y registro

- **POST** `/auth/register`: Registrar un nuevo usuario.
  - El role puede ser USER(2) o ADMIN(1)
  - Cuerpo de la petición:
    ```json
    {
      "name": "Nombre Usuario",
      "email": "correo@ejemplo.com",
      "password": "contraseña",
      "address": "address",
      "status": "Avalidable",
      "tokenRevoked": false,
      "createDate": "DATETIME",
      "role": {"id":2} 
    }
    ```

- **POST** `/auth/login`: Iniciar sesión y obtener el token JWT.
  - Cuerpo de la petición:
    ```json
    {
      "email": "correo@ejemplo.com",
      "password": "contraseña"
    }
    ```


### Operaciones protegidas (requieren autenticación con token JWT)

- **GET** `/api/users`: Obtener lista de usuarios (usuario autenticado).
- **DELETE** `/api/users/{email}`: Eliminar un usuario por email (solo ADMIN).
- **PUT** `/api/users/actualizar/{id}`: Actualizar información de un usuario (solo ADMIN).

## Seguridad

La aplicación utiliza JWT para proteger los endpoints sensibles. Asegúrate de incluir el token en las solicitudes autenticadas usando el encabezado `Authorization`:

```
Authorization: Bearer <tu_token_jwt>
```

### Configuración de roles
Los roles principales son:

- **USER**: Acceso básico.
- **ADMIN**: Acceso completo para gestionar usuarios.

## Errores comunes

1. **Error 404 (Not Found)**:
   - Verifica que el endpoint sea correcto.
   - Asegúrate de que el servidor está en ejecución.

2. **Error 405 (Method Not Allowed)**:
   - Confirma que estás utilizando el método HTTP correcto (GET, POST, PUT, DELETE).

3. **Error 403 (Forbidden)**:
   - Asegúrate de incluir el token JWT en las peticiones protegidas.

4. **Conexión a la base de datos fallida**:
   - Revisa la configuración de `application.properties`.
   - Verifica que el servidor MySQL esté activo.


### Pruebas con Postman

1. Importa la colección de Postman adjunta al proyecto (si está disponible).
2. Configura el token JWT como una variable en Postman para las solicitudes autenticadas.


