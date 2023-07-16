package co.gov.runt.rnet.pvo.sedeelectronicaconsultas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal que permite iniciar el microservicio con el framework Spring Boot
 *
 * @since 1.0.0
 */
@EnableFeignClients
@SpringBootApplication
public class SedeElectronicaConsultasApplication {

  /**
   * Método principal que inicializa la aplicación Spring Boot
   *
   * @param args Argumentos de entrada para iniciar la aplicación de Spring Boot
   * @since 1.0.0
   */
  public static void main(String[] args) {
    SpringApplication.run(SedeElectronicaConsultasApplication.class, args);
  }
}
