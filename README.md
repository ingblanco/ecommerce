## Responsabilidad
 
 aplicación/servicio en SpringBoot que provee un end point rest de consulta  tal que:
 
Acepta como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelve como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
              

## Arquitectura

Arquitectura hexagonal 

## BD
Este servicio utiliza H2 en memoria. 
Para activar la consola de administracion, settear en el archivo application.properties :
spring.h2.console.enabled=true
user-> capitole
password -> capitole

## Server Context

La Aplicacion esta pre seteada para que el servidor corra en el puerto 8082 
Para modificarlo , ingresar al archivo application.properties y setear server.port={valor}

## Requisitos

- Java 17
- Maven ^3


## Compilacion

mvn package

## Test

mvn test

## Ejecucion

mvn spring-boot:run

## URL Base

http://localhost:8082/ecommerce

## Swagger

http://localhost:8082/ecommerce/swagger-ui/index.html
