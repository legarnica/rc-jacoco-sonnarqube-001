package cl.lherrera.jacoco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class Principal {

    /**
     * Clase de prueba, esta se encuentra excluida del sondeo de calidad en sonnarqube
     *
     * En el pom:
     *
     *
     *
     */
    //<sonar.exclusions>**/JacocoApp*.java, **/Principal*.java</sonar.exclusions>
    @GetMapping("isalive")
    public ResponseEntity<String> isAlive() {
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
