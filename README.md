# Proyecto Creacion de Usuario - Implementando Clean Architecture

## Descripcion de Requerimiento
Desarrollar una aplicación que exponga una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive para los mensajes de
error.
Todos los mensajes deben seguir el formato:
``` json
{"mensaje": "mensaje de error"}
```
## Registro
El endpoint deberá recibir un usuario con los campos "nombre", "correo",
"contraseña", más un listado de objetos "teléfono", respetando el siguiente
formato:

``` json
{
	"name":"Juan Rodriguez",
	"email":"juan@rodriguez.org",
	"password":"hunter2",
	"phones":[
		{
			"number":"3104203522",
			"cityCode":"1",
			"countryCode":"57"
		}
	]
}
```
- Responder el código de status HTTP adecuado.
- En caso de éxito, retornar el usuario y los siguientes campos:
    - **id:** id del usuario (del banco de datos o un UUID).
    - **created:** fecha de creación del usuario.
    - **modified:** fecha de la última actualización de usuario.
    - **last_login:** fecha del último ingreso (en caso de ser un nuevo usuario, va a coincidir con la fecha de creación).
    - **token:** token de acceso de la API (puede ser UUID).
    - **isActive:** indica si el usuario sigue habilitado dentro del sistema.
- En caso de que el correo se encuentre registrado en la base de datos, deberá retornar un mensaje de error "El correo ya se encuentra registrado".
- El correo debe seguir una expresión regular para validar que el formato sea el correcto (aaaaaaa@dominio.cl).
- La clave debe seguir una expresión regular para validar que el formato sea el correcto. (El valor de la expresión regular debe ser configurable; la clave debe seguir el formato:
- al menos un digito, longitud mayor o igual a 8 Y al menos una letra mayuscula)
- El token deberá ser persistido junto con el usuario.

## Base de datos
Base de datos en memoria H2 con persistencia JPA.

## Diagrama de la Solucion
![Diagrama de la Solucion](/applications/app-service/src/main/resources/imagen/diagrama.drawio.png)

## Diagrama de Flujo - API Token
![Diagrama de Flujo Token](/applications/app-service/src/main/resources/imagen/token.drawio%20.png)

## Diagrama de Flujo - API Save User
![Diagrama de Flujo Save User](/applications/app-service/src/main/resources/imagen/save.drawio.png)

## Diagrama de Flujo - API Get User
![Diagrama de Flujo Get User](/applications/app-service/src/main/resources/imagen/getUser.drawio.png)

## Diagrama de Flujo - API Get Users
![Diagrama de Flujo Get Users](/applications/app-service/src/main/resources/imagen/getUsers.drawio.png)

## Diagrama de Flujo - API Update User
![Diagrama de Flujo Update User](/applications/app-service/src/main/resources/imagen/updateUser.drawio.png)

## Diagrama de Flujo - API Disable User
![Diagrama de Flujo Disable User](/applications/app-service/src/main/resources/imagen/disableUser.drawio.png)

# Manual de Explotacion
Para consumir correctamente las Apis de creacion de usuario primero debe ejecutar la Api de generacion de Token
y copiar el token generado en ella y pasarlo a las demas Apis por medio del Header en campo Token y luego consumir la 
API deseada; el Token genrado tiene como duracion una hora para luego expirar.
- Se adjunta Coleccion de postman para poder realizar el consumo de la API; Exporte y consuma cada una de ellas.

## Antes de Iniciar
Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por ultimo el inicio y configuracion de la aplicacion.

Lee el articulo [Clean Architecture � Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el modulo mas interno de la arquitectura, pertenece a la capa del dominio y encapsula la logica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este modulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define logica de aplicacion y reacciona a las invocaciones desde el m�dulo de entry points, orquestando los flujos hacia el modulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no estan arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genaricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patron de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicacion o el inicio de los flujos de negocio.

## Application

Este modulo es el mas externo de la arquitectura, es el encargado de ensamblar los distintos modulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automatica, inyectando en estos instancias concretas de las dependencias declaradas. Adem�s inicia la aplicacion (es el unico modulo del proyecto donde encontraremos la funcion public static void main(String[] args).

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**
