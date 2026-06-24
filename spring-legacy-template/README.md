# Spring Legacy reusable template

This template was derived from `SpringLegacy` while preserving its Gradle WAR,
Java configuration, DispatcherServlet initializer, and JSP view structure.

Template variables:

- `__PROJECT_NAME__`: Gradle root project name
- `__BASE_PACKAGE__`: Java base package
- `__BASE_PACKAGE_PATH__`: Java package directory path

Generate a project from the repository root:

```powershell
.\scripts\New-SpringLegacyProject.ps1 `
  -ProjectName spring-advanced-assignment `
  -BasePackage org.scoula
```

Database settings are externalized in `application.properties` and can be
overridden with `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.
