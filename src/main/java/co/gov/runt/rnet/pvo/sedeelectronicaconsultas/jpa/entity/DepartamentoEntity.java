package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad Departamento. RUNTPROD.PA_DEPARTAME
 *
 * @since 1.0.0
 */
@Entity
@Table(schema = "RUNTPROD", name = "PA_DEPARTAME")
@NoArgsConstructor
@Getter
@Setter
public class DepartamentoEntity {

  @Id
  @Column(name = "DEPARTAME_CODDPTO")
  private Long id;

  @Column(name = "DEPARTAME_NOMBRE")
  private String nombre;

  @Column(name = "DEPARTAME_PAIS_IDPAIS")
  private Long idPais;
}
