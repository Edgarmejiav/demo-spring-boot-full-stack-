# Descripción del Proyecto

Crear una aplicación de gestión de pedidos para un sistema de comercio electrónico. La aplicación debe permitir:

- Crear, leer, actualizar y eliminar pedidos.
- Listar todos los pedidos y obtener detalles de un pedido específico.

## Estructura de los Pedidos
- Un pedido contiene un cliente, una lista de productos y un total.
- Los productos y clientes deben almacenarse en bases de datos relacionales (PostgreSQL).
- Los pedidos deben almacenarse en una base de datos no relacional (MongoDB).

## Requisitos Técnicos
- **Base de Datos Relacional**: PostgreSQL
- **Base de Datos No Relacional**: MongoDB
- **Backend**: Java Spring Boot
- **Reactivo**: Spring WebFlux
- **Frontend**: Angular 15 o superior

## Task List

- [ ] **Transacciones**: Asegurar que las operaciones de creación y actualización de pedidos sean transaccionales y consistentes entre ambas bases de datos.
- [ ] **Patrones de Diseño**: Aplicar patrones de diseño adecuados para la estructura del código.
- [ ] **Aplicación Reactiva**: Implementar la aplicación de manera completamente reactiva utilizando Spring WebFlux.
- [ ] **Buenas Prácticas**: Seguir las mejores prácticas de desarrollo y arquitectura.
- [ ] **Manejo de Errores**: Implementar manejo adecuado de errores.
- [ ] **Validación de Entradas**: Validar entradas de usuario para garantizar la integridad de los datos.
- [ ] **Tests Unitarios**: Escribir tests unitarios que cubran las funcionalidades clave del código.
- [ ] **Docker**: Configurar la aplicación para ser ejecutada en un entorno Docker.
