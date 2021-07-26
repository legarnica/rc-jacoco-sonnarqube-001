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
Se genera el reporte en sites.

Para la integración de sonarqube, hay que ir a generar el token en el servidor de sonarquebe, con eso en propiedades (cmd + ,), buscar plugins y conectar con nuestro servidor. Ese token hay que agregarlo en el ide. Luego en el servidor, hay que agregar un nuevo proyecto y con el token lo conectamos y nos entrega el srcript de ejecución por consola;

```shell
mvn sonar:sonar \
  -Dsonar.projectKey=integrationjacoco \
  -Dsonar.host.url=http://localhost:8888 \
  -Dsonar.login=b60d3e5c2d44a6d20bfa762438a7c6b81f87e3b6
```
Entregado por `http://localhost:8888/dashboard?id=integrationjacoco&selectedTutorial=manual`

### Actualización LUN 26 Julio 2021

`<sonar.jacoco.reportPath>` se cambia por `<sonar.coverage.jacoco.xmlReportPaths>`, es básicamente, de donde se cuelga `SonarQube`, para mostrar el reporte. 

```xml
    <properties>
    <java.version>11</java.version>
    <!-- JaCoCo Properties -->
    <jacoco.version>0.8.6</jacoco.version>
    <sonar.java.coveragePlugin>jacoco</sonar.java.coveragePlugin>
    <sonar.dynamicAnalysis>reuseReports</sonar.dynamicAnalysis>
    <sonar.exclusions>**/JacocoApp*.java, **/Principal*.java</sonar.exclusions>
    <sonar.coverage.jacoco.xmlReportPaths>${project.basedir}/target/site/jacoco/jacoco.xml</sonar.coverage.jacoco.xmlReportPaths>
    <sonar.language>java</sonar.language>
</properties>
```
### Exclusiones

Como se pudo ver, las exclusiones de archivos se detallan en: 
`<sonar.exclusions>**/JacocoApp*.java, **/Principal*.java</sonar.exclusions>`, de esta forma, no son considerados en el reporte de `JaCoCo`, entonces no son evaluados tanto en calidad como en covertura.

#### Documentaciones consultadas:

+ [Solución Stack Over Flow Para excluir sonar.](https://stackoverflow.com/questions/12135939/how-to-make-sonar-ignore-some-classes-for-codecoverage-metric)
+ [Solución exclusión Baeldung (Solución con archivo properties).](https://www.baeldung.com/sonar-exclude-violations)