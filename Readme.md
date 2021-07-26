# Ejemplo de configuración JaCoCo

El siguiente proyecto, muestra en su historial de git, lo que hay que incluir en el `pom.xml`, para que funcione el reporte de covertura de código, utilizando test unitarios.

### Configuración de las dependencias en el Pom.

En `properties`, agregar lo siguiente:

```xml
<!-- JaCoCo Properties -->
<jacoco.version>0.8.6</jacoco.version>
<sonar.java.coveragePlugin>jacoco</sonar.java.coveragePlugin>
<sonar.dynamicAnalysis>reuseReports</sonar.dynamicAnalysis>
<sonar.jacoco.reportPath>${project.basedir}/../target/jacoco.exec</sonar.jacoco.reportPath>
<sonar.language>java</sonar.language>
```

Esto quedará de la siguiente forma:

```xml
<properties>
    <java.version>11</java.version>
    <!-- JaCoCo Properties -->
    <jacoco.version>0.8.6</jacoco.version>
    <sonar.java.coveragePlugin>jacoco</sonar.java.coveragePlugin>
    <sonar.dynamicAnalysis>reuseReports</sonar.dynamicAnalysis>
    <sonar.jacoco.reportPath>${project.basedir}/../target/jacoco.exec</sonar.jacoco.reportPath>
    <sonar.language>java</sonar.language>
</properties>
```

Luego en `dependencies`, hay que agregar la siguiente dependencia:

```xml
<dependency>
    <groupId>org.jacoco</groupId> 
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.6</version>
</dependency>
```

Para un proyecto nuevo, que solamente posea la dependencia web, quedaría de la siguiente forma:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.6</version>
    </dependency>
</dependencies>
```

Finalmente Agregamos el plugin a la sección de `plugins`:

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>${jacoco.version}</version>
    <executions>
        <execution>
            <id>jacoco-initialize</id>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>jacoco-site</id>
            <phase>package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Con esto al ejecutar:

```shell
mvn clean verify
```



