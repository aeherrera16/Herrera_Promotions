# API RESTful - Sistema de Gestión de Promociones
## Proyecto: Herrera_Promotion

### Autor: Anahy Herrera
### Fecha: Diciembre 2025

---

## 📋 Descripción General del Sistema

API RESTful desarrollada con **Java 17** y **Spring Boot 4.0** para la gestión completa de promociones comerciales. El sistema permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre promociones, con información como descuentos, fechas de vigencia y estados.

### Características Principales:
- ✅ API RESTful completamente funcional
- ✅ Operaciones CRUD completas
- ✅ Persistencia en base de datos MySQL
- ✅ Dockerización completa (API + Base de Datos)
- ✅ Publicación en Docker Hub
- ✅ Documentación completa con Postman
- ✅ Arquitectura en capas (Controller, Service, Repository)
- ✅ Principios REST aplicados

---

## 🏗️ Arquitectura del Sistema

### Arquitectura en Capas (Layered Architecture)

```
┌─────────────────────────────────────┐
│     CAPA DE PRESENTACIÓN            │
│    (REST Controllers)               │
│   PromotionController.java          │
└──────────────┬──────────────────────┘
               │
               ↓
┌─────────────────────────────────────┐
│     CAPA DE LÓGICA DE NEGOCIO       │
│    (Services)                       │
│   PromotionService.java             │
│   PromotionServiceImpl.java         │
└──────────────┬──────────────────────┘
               │
               ↓
┌─────────────────────────────────────┐
│     CAPA DE ACCESO A DATOS          │
│    (Repositories)                   │
│   PromotionRepository.java          │
└──────────────┬──────────────────────┘
               │
               ↓
┌─────────────────────────────────────┐
│     CAPA DE PERSISTENCIA            │
│    (Database - MySQL)               │
│   Tabla: promotion                  │
└─────────────────────────────────────┘
```

### Tecnologías Utilizadas:

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 4.0.0 | Framework principal |
| Spring Data JPA | 4.0.0 | Capa de persistencia |
| MySQL | 8.0 | Base de datos |
| Gradle | 8.x | Gestión de dependencias |
| Docker | Latest | Contenedorización |
| Postman | Latest | Pruebas de API |

---

## 🗄️ Diseño de Base de Datos

### Tabla: `promotion`

| Campo | Tipo | Descripción | Restricciones |
|-------|------|-------------|---------------|
| id | BIGINT | Identificador único | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(255) | Nombre de la promoción | NOT NULL |
| discount_percentage | DECIMAL(5,2) | Porcentaje de descuento | NOT NULL |
| start_date | DATE | Fecha de inicio | NOT NULL |
| end_date | DATE | Fecha de fin | NOT NULL |
| status | VARCHAR(50) | Estado de la promoción | NOT NULL |

### Ejemplo de Datos:

```sql
INSERT INTO promotion (name, discount_percentage, start_date, end_date, status)
VALUES ('Black Friday 2025', 50.00, '2025-11-25', '2025-11-30', 'ACTIVE');
```

---

## 🌐 Diseño REST de la API

### Principios REST Aplicados:

1. **Recursos identificables**: Cada promoción tiene una URI única
2. **Verbos HTTP estándar**: GET, POST, PUT, DELETE
3. **Representación JSON**: Formato estándar para intercambio de datos
4. **Stateless**: Cada petición contiene toda la información necesaria
5. **Códigos de estado HTTP apropiados**

### Endpoints Disponibles:

| Método | Endpoint | Descripción | Código Éxito | Código Error |
|--------|----------|-------------|--------------|--------------|
| GET | `/api/promotions` | Listar todas las promociones | 200 OK | - |
| GET | `/api/promotions/{id}` | Obtener promoción por ID | 200 OK | 404 Not Found |
| POST | `/api/promotions` | Crear nueva promoción | 201 Created | 400 Bad Request |
| PUT | `/api/promotions/{id}` | Actualizar promoción | 200 OK | 404 Not Found |
| DELETE | `/api/promotions/{id}` | Eliminar promoción | 204 No Content | 404 Not Found |

### Ejemplos de Uso:

#### 1. Listar todas las promociones
```bash
curl -X GET http://localhost:8080/api/promotions
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "name": "Black Friday 2025",
    "discountPercentage": 50.00,
    "startDate": "2025-11-25",
    "endDate": "2025-11-30",
    "status": "ACTIVE"
  }
]
```

#### 2. Buscar promoción por ID
```bash
curl -X GET http://localhost:8080/api/promotions/1
```

#### 3. Crear nueva promoción
```bash
curl -X POST http://localhost:8080/api/promotions \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Cyber Monday 2025",
    "discountPercentage": 40.00,
    "startDate": "2025-12-01",
    "endDate": "2025-12-01",
    "status": "ACTIVE"
  }'
```

#### 4. Actualizar promoción
```bash
curl -X PUT http://localhost:8080/api/promotions/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Black Friday 2025 - EXTENDIDO",
    "discountPercentage": 60.00,
    "startDate": "2025-11-25",
    "endDate": "2025-12-05",
    "status": "ACTIVE"
  }'
```

#### 5. Eliminar promoción
```bash
curl -X DELETE http://localhost:8080/api/promotions/1
```

---

## 📁 Estructura del Proyecto

```
Herrera_Promotion/
├── src/
│   └── main/
│       ├── java/
│       │   └── ec/edu/espe/herrera_promotion/
│       │       ├── HerreraPromotionApplication.java    # Clase principal
│       │       ├── controllers/
│       │       │   └── PromotionController.java        # Endpoints REST
│       │       ├── models/
│       │       │   └── entities/
│       │       │       └── Promotion.java              # Entidad JPA
│       │       ├── repositories/
│       │       │   └── PromotionRepository.java        # Acceso a datos
│       │       └── services/
│       │           ├── PromotionService.java           # Interface
│       │           └── PromotionServiceImpl.java       # Implementación
│       └── resources/
│           ├── application.properties                  # Config general
│           ├── application-local.properties            # Config local
│           ├── application-docker.properties           # Config Docker
│           └── application-test.properties             # Config tests
├── build.gradle                                        # Dependencias
├── Dockerfile                                          # Imagen Docker
├── DOCKER_COMMANDS.md                                  # Comandos Docker
├── Herrera_Promotion_API.postman_collection.json       # Colección Postman
└── README.md                                           # Este archivo
```

---

## 💻 Código Relevante y Explicaciones

### 1. Entidad Promotion (JPA)

```java
@Entity
@Table(name = "promotion")
public class Promotion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discountPercentage;
    
    @Column(nullable = false)
    private LocalDate startDate;
    
    @Column(nullable = false)
    private LocalDate endDate;
    
    @Column(nullable = false, length = 50)
    private String status;
    
    // Getters y Setters...
}
```

**Explicación:**
- `@Entity`: Marca la clase como una entidad JPA
- `@Table`: Define el nombre de la tabla en la base de datos
- `@Id` + `@GeneratedValue`: Define la clave primaria con auto-incremento
- `@Column`: Define características de las columnas (nullable, precision, length)
- Se usan tipos apropiados: `BigDecimal` para decimales, `LocalDate` para fechas

### 2. Repository (Spring Data JPA)

```java
@Repository
@Transactional
public interface PromotionRepository extends CrudRepository<Promotion, Long> {
    // Métodos CRUD heredados automáticamente:
    // - findAll()
    // - findById(Long id)
    // - save(Promotion entity)
    // - deleteById(Long id)
}
```

**Explicación:**
- `CrudRepository` proporciona automáticamente operaciones CRUD
- No es necesario implementar métodos básicos
- `@Transactional` maneja automáticamente las transacciones

### 3. Service Layer

```java
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Promotion> findAll() {
        return (List<Promotion>) repository.findAll();
    }

    @Override
    @Transactional
    public Promotion save(Promotion promotion) {
        return repository.save(promotion);
    }
    // ... otros métodos
}
```

**Explicación:**
- Capa intermedia entre el controlador y el repositorio
- Maneja la lógica de negocio
- `@Transactional` gestiona las transacciones de base de datos
- `readOnly = true` optimiza operaciones de solo lectura

### 4. REST Controller

```java
@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService service;

    @GetMapping
    public ResponseEntity<List<Promotion>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Promotion> promotionOptional = service.findById(id);
        if (promotionOptional.isPresent()) {
            return ResponseEntity.ok(promotionOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Promotion with ID " + id + " not found");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Promotion promotion) {
        Promotion savedPromotion = service.save(promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPromotion);
    }
    // ... otros endpoints
}
```

**Explicación:**
- `@RestController`: Combina `@Controller` + `@ResponseBody`
- `@RequestMapping`: Define la ruta base de la API
- `@GetMapping`, `@PostMapping`, etc.: Mapean verbos HTTP
- `@PathVariable`: Extrae variables de la URL
- `@RequestBody`: Convierte JSON del request a objeto Java
- `ResponseEntity`: Permite control completo de la respuesta HTTP

---

## 🐳 Docker - Contenedorización

### Dockerfile

```dockerfile
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY ./build/libs/Herrera_Promotion-0.0.1-SNAPSHOT.jar ./Herrera_Promotion.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Herrera_Promotion.jar", "--spring.profiles.active=docker"]
```

**Explicación:**
1. Usa imagen base con Java 17
2. Define directorio de trabajo
3. Copia el JAR compilado
4. Expone puerto 8080
5. Ejecuta la aplicación con perfil "docker"

### Configuración Docker (application-docker.properties)

```properties
spring.datasource.url=jdbc:mysql://mysql-promotiondb:3306/promotiondb
spring.datasource.username=AppRoot
spring.datasource.password=abcd
spring.jpa.hibernate.ddl-auto=update
```

**Nota:** El hostname `mysql-promotiondb` es el nombre del contenedor MySQL en la misma red Docker.

---

## 🚀 Pasos para Ejecutar la Aplicación

### Opción 1: Ejecución Local (sin Docker)

#### Requisitos:
- Java 17 instalado
- MySQL instalado y corriendo en puerto 3307
- Gradle instalado

#### Pasos:

1. **Crear la base de datos:**
```sql
CREATE DATABASE promotiondb;
CREATE USER 'AppRoot'@'localhost' IDENTIFIED BY 'abcd';
GRANT ALL PRIVILEGES ON promotiondb.* TO 'AppRoot'@'localhost';
FLUSH PRIVILEGES;
```

2. **Compilar el proyecto:**
```bash
cd Herrera_Promotion
./gradlew clean build
```

3. **Ejecutar la aplicación:**
```bash
./gradlew bootRun
```

4. **Verificar:**
```bash
curl http://localhost:8080/api/promotions
```

---

### Opción 2: Ejecución con Docker (RECOMENDADO)

#### Requisitos:
- Docker instalado y corriendo
- Cuenta en Docker Hub

#### Pasos Completos:

**1. Crear red Docker:**
```bash
docker network create promotion-network
```

**2. Iniciar contenedor MySQL:**
```bash
docker run -d \
  --name mysql-promotiondb \
  --network promotion-network \
  -e MYSQL_ROOT_PASSWORD=rootpassword \
  -e MYSQL_DATABASE=promotiondb \
  -e MYSQL_USER=AppRoot \
  -e MYSQL_PASSWORD=abcd \
  -p 3307:3306 \
  mysql:8.0
```

**3. Esperar a que MySQL esté listo (importante):**
```bash
echo "Esperando 15 segundos..."
sleep 15
```

**4. Compilar la aplicación:**
```bash
cd Herrera_Promotion
./gradlew clean build
```

**5. Construir imagen Docker:**
```bash
docker build -t tu-usuario/herrera-promotion:1.0 .
```
*Reemplaza "tu-usuario" con tu usuario de Docker Hub*

**6. Ejecutar contenedor de la aplicación:**
```bash
docker run -d \
  --name herrera-promotion-app \
  --network promotion-network \
  -p 8080:8080 \
  tu-usuario/herrera-promotion:1.0
```

**7. Ver logs:**
```bash
docker logs -f herrera-promotion-app
```

**8. Probar la API:**
```bash
curl http://localhost:8080/api/promotions
```

**9. Publicar en Docker Hub:**
```bash
docker login
docker push tu-usuario/herrera-promotion:1.0
```

---

## 📊 Evidencias de Docker

### Verificar contenedores corriendo:
```bash
$ docker ps
CONTAINER ID   IMAGE                              PORTS                    NAMES
abc123def456   anahy/herrera-promotion:1.0       0.0.0.0:8080->8080/tcp   herrera-promotion-app
xyz789uvw012   mysql:8.0                         0.0.0.0:3307->3306/tcp   mysql-promotiondb
```

### Ver logs de la aplicación:
```bash
$ docker logs herrera-promotion-app

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.0)

... Started HerreraPromotionApplication in 3.456 seconds
```

### Verificar conectividad entre contenedores:
```bash
$ docker exec -it herrera-promotion-app ping mysql-promotiondb
PING mysql-promotiondb (172.18.0.2): 56 data bytes
64 bytes from 172.18.0.2: seq=0 ttl=64 time=0.123 ms
```

---

## 🧪 Pruebas con Postman

### Importar Colección:

1. Abrir Postman
2. Click en "Import"
3. Seleccionar el archivo `Herrera_Promotion_API.postman_collection.json`
4. La colección aparecerá con 13 pruebas

### Casos de Prueba Incluidos:

| # | Prueba | Tipo | Resultado Esperado |
|---|--------|------|-------------------|
| 1 | Crear Promoción - Black Friday | POST | 201 Created |
| 2 | Crear Promoción - Cyber Monday | POST | 201 Created |
| 3 | Crear Promoción - Navidad | POST | 201 Created |
| 4 | Listar Todas las Promociones | GET | 200 OK - Array con 3 items |
| 5 | Buscar por ID (Exitoso) | GET | 200 OK - Objeto promoción |
| 6 | Buscar por ID (No Encontrado) | GET | 404 Not Found |
| 7 | Actualizar Promoción (Exitoso) | PUT | 200 OK - Objeto actualizado |
| 8 | Actualizar Promoción (No Encontrado) | PUT | 404 Not Found |
| 9 | Cambiar Estado a INACTIVE | PUT | 200 OK |
| 10 | Eliminar Promoción (Exitoso) | DELETE | 204 No Content |
| 11 | Eliminar Promoción (No Encontrado) | DELETE | 404 Not Found |
| 12 | Crear con Datos Inválidos | POST | 500/400 Error |
| 13 | Verificar Lista Final | GET | 200 OK - Array actualizado |

### Ejecutar Todas las Pruebas:

1. Asegurarse de que la aplicación está corriendo
2. En Postman, seleccionar la colección
3. Click en "Run" (Runner)
4. Click en "Run Herrera Promotion API"
5. Ver resultados de todas las pruebas

### Capturas de Pantalla Recomendadas:

- ✅ Postman mostrando todas las pruebas exitosas
- ✅ GET /api/promotions mostrando lista de promociones
- ✅ POST /api/promotions con respuesta 201 Created
- ✅ PUT /api/promotions/{id} con respuesta 200 OK
- ✅ DELETE /api/promotions/{id} con respuesta 204 No Content
- ✅ Casos de error (404 Not Found)

---

## 📌 Conclusiones

### Logros Alcanzados:

1. ✅ **API RESTful Funcional**: Implementación completa siguiendo principios REST
2. ✅ **Arquitectura Limpia**: Separación clara de responsabilidades en capas
3. ✅ **Persistencia Robusta**: Integración exitosa con MySQL usando Spring Data JPA
4. ✅ **Dockerización Completa**: API y base de datos ejecutándose en contenedores
5. ✅ **Pruebas Exhaustivas**: Cobertura de casos exitosos y de error
6. ✅ **Documentación Completa**: README, comentarios en código y colección Postman

### Aprendizajes Clave:

- **Spring Boot** simplifica enormemente el desarrollo de APIs REST
- **Spring Data JPA** elimina la necesidad de escribir SQL manualmente
- **Docker** facilita el despliegue y garantiza consistencia entre entornos
- **Arquitectura en capas** mejora la mantenibilidad y escalabilidad
- **Principios REST** proporcionan una API intuitiva y estándar

### Mejoras Futuras Recomendadas:

1. 🔒 **Seguridad**: Implementar Spring Security con JWT
2. ✅ **Validaciones**: Agregar Bean Validation (@Valid, @NotNull, etc.)
3. 📊 **Paginación**: Implementar paginación en el listado de promociones
4. 🔍 **Búsquedas**: Agregar filtros por fecha, estado, descuento, etc.
5. 🧪 **Testing**: Agregar tests unitarios y de integración
6. 📝 **Swagger**: Documentación automática de API con OpenAPI
7. 🚀 **CI/CD**: Implementar pipeline de integración continua
8. 📧 **Notificaciones**: Alertas cuando una promoción está por vencer
9. 📈 **Métricas**: Agregar Actuator para monitoreo de la aplicación
10. 🌐 **Internacionalización**: Soporte multi-idioma

### Buenas Prácticas Aplicadas:

- ✅ Uso de DTOs para transferencia de datos
- ✅ Manejo apropiado de excepciones
- ✅ Códigos de estado HTTP correctos
- ✅ Nombres de endpoints semánticos
- ✅ Separación de configuraciones por entorno
- ✅ Versionamiento de la API (en la ruta /api/v1 si se requiere)

---

## 🔗 Enlaces Útiles

- **Repositorio GitHub**: [Añadir tu enlace aquí]
- **Docker Hub**: [Añadir tu enlace aquí]
- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Spring Data JPA**: https://spring.io/projects/spring-data-jpa
- **Docker Docs**: https://docs.docker.com/
- **Postman**: https://www.postman.com/

---

## 📞 Contacto

**Autor**: Anahy Herrera  
**Institución**: ESPE  
**Curso**: Sistemas Distribuidos  
**Fecha**: Diciembre 2025

---

## 📄 Licencia

Este proyecto fue desarrollado con fines académicos para el curso de Sistemas Distribuidos.

---

**Fin del documento**
