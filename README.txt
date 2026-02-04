# Inventory Config Service - Examen 003
**Autor:** Esteban Quijia

## Descripción
Microservicio desarrollado en Kotlin con Spring Boot para la gestión de reglas de inventario, integrado con AWS Cognito para seguridad y auditoría.

## Configuración de Variables de Entorno
Para ejecutar este servicio, asegúrate de configurar las siguientes propiedades en `application.properties`:

- `spring.datasource.url`:
- `spring.security.oauth2.resourceserver.jwt.issuer-uri`: https://cognito-idp.us-east-2.amazonaws.com/us-east-2_jlVpHFybW/.well-known/jwks.json.

## Seguridad y Roles
- **Claim para Auditoría:** Se utiliza el claim `sub` del JWT para el campo `updatedBy`.
- **Claim para Roles:** Se utiliza el claim `cognito:groups` para validar el rol `ADMIN`.

## Endpoints
1. **Público:** `GET /api/rules/public/health` - Estado del servicio.
2. **Autenticado:** `GET /api/rules` - Listado de configuraciones.
3. **Admin:** `POST /api/rules` y `PATCH /api/rules/{id}/toggle` - Gestión de reglas.

## Ejecución
```bash
./gradlew bootRun