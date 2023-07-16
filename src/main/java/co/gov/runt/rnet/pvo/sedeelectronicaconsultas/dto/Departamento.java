package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO Departamento donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class Departamento {
  private Long id;
  private String nombre;
}
