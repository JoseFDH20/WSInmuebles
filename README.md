## WSInmuebles
Servicio web REST usando Spring boot.

Ejecutar el proyecto con Eclipse IDE o usar maven para poder construirlo y generar el jar.

Crear una base de datos en PostgreSQL con el nombre "INMUEBLESDB"

Para construir el proyecto con Maven usando la consola, ubicarse dentro de la carpeta del proyecto
 ```
 cd WSInmuebles
 ```

Ejecute lo siguiente para construir el proyecto
```
mvnw package
 ```


Use el siguiente comando para conocer el nombre del pod de la base postgres
```
kubectl get pods
```

Del resultado anterior, reemplace el nombre del pod en el siguiente comando
```
kubectl port-forward <<colocarNombrePod>> 5433:5432
```
Después de esto, puede conectarse con su aplicación GUI algo como dbbeaver o pgadmin, con una GUI podra ejecutar facilmente el siguiente archivo sql

En la carpeta estan el siguiente archivo para crear los objetos de DataBase PostgreSQL:
Contiene los objetos requeridos para la aplicacion.
```
WSInmueblesDB_objetosNecesarios.sql
```

Cuando haya iniciado la app, podra ingresa usando como ejemplo el siguiente path

Puede revisar los servicios REST disponibles usando la interfaz de Swagger
```
http://localhost:8080/WSInmuebles/swagger-ui.html
```

