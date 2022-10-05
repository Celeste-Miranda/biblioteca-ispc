
# BibliotecaApp - Biblioteca Popular Los Lectores

Sistema de gestión de biblioteca. 

**Video Requerido por Cátedra**: https://www.youtube.com/watch?v=zd164JoCByQ&feature=youtu.be

### Funcionamiento: 

El sistema permite crear libros, listarlos, revisar su disponibilidad y reservar los mismos. 
Tiene disponible un *Dashboard* donde el usuario podrá consultar sus prestamos y fechas de devolución.


El **administrador** podrá revisar en su *dashboard* los prestamos pendientes por DNI, y generar el registro de devolución de los mismos.
Podrá tambien generar nuevos libros.  

El **usuario anonimo** podrá consultar libros y disponibilidad desde *Biblioteca* 

El **usuario registrado**, desde *Biblioteca*, podrá reservar libros que se encuentren disponibles. 
Si el usuario ya tiene reservado el libro, no podrá reservarlo nuevamente. 

### Instalación 

Deberá ejecutar el servidor SpringBoot con el archivo .jar que trae este repositorio. 
Por defecto, correrá en el puerto **8090** (Asegurese no estar utilizando dicho puerto)

@  java - jar bibliotecaApp-0.0.1-SNAPSHOT.jar 


Para el FRONT: Deberá descargar las dependencias de nodejs. En su terminal , situese sobre la carpeta "Front-Angular"

@cd /Front-Angular/

Luego ejecute el siguiente comando y aguarde la descarga de las dependencias: 

@ npm install 

Por último, corra el proyecto sobre el puerto 4200

@ng serve 

### Cuentas para TESTING: 

**admin**: celeste@hot.com // password: password123
**user** : jasoncito@hot.com // password: password123 
## Authors

- [@Celeste-Miranda](https://www.github.com/Celeste-Miranda) 
- [@rebofel](https://www.github.com/rebofel)


## Frameworks 

#### SpringBoot 

Para el back

#### Angular 14

Para el Front 

#### PrimeNG
Para Angular y efectos visuales. 

#### MySQL
Para base de datos. Se encuentra remoteada en la config del back. Se deja una copia de la misma en el repositorio.  "db.sql" 





