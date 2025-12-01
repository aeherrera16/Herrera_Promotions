# Comandos Docker - Proyecto Herrera_Promotion

## 1. CREAR Y EJECUTAR BASE DE DATOS MYSQL EN DOCKER

### Crear red Docker (opcional pero recomendado para comunicación entre contenedores)
```bash
docker network create promotion-network
```

### Crear y ejecutar contenedor MySQL
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

### Verificar que MySQL está corriendo
```bash
docker ps
docker logs mysql-promotiondb
```

### Conectarse a MySQL (opcional - para verificar)
```bash
docker exec -it mysql-promotiondb mysql -uAppRoot -pabcd
```

Dentro de MySQL ejecutar:
```sql
USE promotiondb;
SHOW TABLES;
```

---

## 2. CONSTRUIR LA APLICACIÓN SPRING BOOT

### Limpiar y construir el proyecto con Gradle
```bash
cd Herrera_Promotion
./gradlew clean build
```

O en Windows:
```bash
gradlew.bat clean build
```

### Verificar que el JAR se creó correctamente
```bash
ls -la build/libs/
```

Deberías ver: `Herrera_Promotion-0.0.1-SNAPSHOT.jar`

---

## 3. CONSTRUIR LA IMAGEN DOCKER DE LA APLICACIÓN

### Construir la imagen (reemplaza "tu-usuario" con tu usuario de Docker Hub)
```bash
docker build -t tu-usuario/herrera-promotion:1.0 .
```

Ejemplo:
```bash
docker build -t anahy/herrera-promotion:1.0 .
```

### Verificar que la imagen se creó
```bash
docker images | grep herrera-promotion
```

---

## 4. EJECUTAR LA APLICACIÓN EN DOCKER

### Ejecutar el contenedor de la aplicación
```bash
docker run -d \
  --name herrera-promotion-app \
  --network promotion-network \
  -p 8080:8080 \
  tu-usuario/herrera-promotion:1.0
```

Ejemplo:
```bash
docker run -d \
  --name herrera-promotion-app \
  --network promotion-network \
  -p 8080:8080 \
  anahy/herrera-promotion:1.0
```

### Ver logs de la aplicación
```bash
docker logs -f herrera-promotion-app
```

### Verificar que ambos contenedores están corriendo
```bash
docker ps
```

---

## 5. PROBAR LA API

### Probar endpoint de salud
```bash
curl http://localhost:8080/api/promotions
```

---

## 6. PUBLICAR EN DOCKER HUB

### Iniciar sesión en Docker Hub
```bash
docker login
```

Ingresa tu usuario y contraseña de Docker Hub

### Subir la imagen a Docker Hub
```bash
docker push tu-usuario/herrera-promotion:1.0
```

Ejemplo:
```bash
docker push anahy/herrera-promotion:1.0
```

### Opcional: Crear también tag "latest"
```bash
docker tag tu-usuario/herrera-promotion:1.0 tu-usuario/herrera-promotion:latest
docker push tu-usuario/herrera-promotion:latest
```

---

## 7. COMANDOS ÚTILES

### Detener contenedores
```bash
docker stop herrera-promotion-app mysql-promotiondb
```

### Iniciar contenedores detenidos
```bash
docker start mysql-promotiondb
docker start herrera-promotion-app
```

### Eliminar contenedores
```bash
docker rm -f herrera-promotion-app mysql-promotiondb
```

### Eliminar red
```bash
docker network rm promotion-network
```

### Ver logs en tiempo real
```bash
docker logs -f herrera-promotion-app
```

### Entrar al contenedor de la aplicación
```bash
docker exec -it herrera-promotion-app /bin/sh
```

### Reconstruir todo desde cero
```bash
# 1. Detener y eliminar contenedores
docker rm -f herrera-promotion-app mysql-promotiondb

# 2. Eliminar imagen antigua
docker rmi tu-usuario/herrera-promotion:1.0

# 3. Reconstruir aplicación
./gradlew clean build

# 4. Reconstruir imagen
docker build -t tu-usuario/herrera-promotion:1.0 .

# 5. Iniciar MySQL
docker start mysql-promotiondb
# o si no existe:
docker run -d --name mysql-promotiondb --network promotion-network -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=promotiondb -e MYSQL_USER=AppRoot -e MYSQL_PASSWORD=abcd -p 3307:3306 mysql:8.0

# 6. Esperar 10-15 segundos para que MySQL esté listo
sleep 15

# 7. Iniciar aplicación
docker run -d --name herrera-promotion-app --network promotion-network -p 8080:8080 tu-usuario/herrera-promotion:1.0
```

---

## 8. FLUJO COMPLETO PASO A PASO

```bash
# Paso 1: Crear red
docker network create promotion-network

# Paso 2: Iniciar MySQL
docker run -d --name mysql-promotiondb --network promotion-network -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=promotiondb -e MYSQL_USER=AppRoot -e MYSQL_PASSWORD=abcd -p 3307:3306 mysql:8.0

# Paso 3: Esperar a que MySQL esté listo
echo "Esperando 15 segundos a que MySQL inicie..."
sleep 15

# Paso 4: Construir aplicación
cd /Users/anahy/Desktop/Distribuidas/SEGUNDO_PARCIAL/taller/Herrera_Promotion
./gradlew clean build

# Paso 5: Construir imagen Docker (REEMPLAZA "anahy" con tu usuario)
docker build -t anahy/herrera-promotion:1.0 .

# Paso 6: Ejecutar aplicación
docker run -d --name herrera-promotion-app --network promotion-network -p 8080:8080 anahy/herrera-promotion:1.0

# Paso 7: Ver logs
echo "Esperando 10 segundos a que la aplicación inicie..."
sleep 10
docker logs herrera-promotion-app

# Paso 8: Login en Docker Hub
docker login

# Paso 9: Subir imagen
docker push anahy/herrera-promotion:1.0
```

---

## NOTAS IMPORTANTES:

1. **Reemplaza "tu-usuario" o "anahy"** con tu usuario real de Docker Hub en todos los comandos.

2. **La red promotion-network** permite que los contenedores se comuniquen entre sí usando los nombres de contenedor como hostname.

3. **Puerto MySQL**: Mapeado a 3307 en el host para evitar conflictos si ya tienes MySQL instalado en el puerto 3306.

4. **Puerto Aplicación**: Mapeado a 8080 en el host.

5. **Esperar a MySQL**: Es importante esperar unos 10-15 segundos después de iniciar MySQL antes de iniciar la aplicación.

6. **Perfil Docker**: La aplicación usa automáticamente `application-docker.properties` cuando se ejecuta en Docker.
