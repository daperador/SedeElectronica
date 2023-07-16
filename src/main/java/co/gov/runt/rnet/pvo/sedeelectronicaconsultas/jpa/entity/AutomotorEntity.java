/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel
 */
@Entity
@NamedNativeQueries({
  @NamedNativeQuery(
      name = "Automotor.PorPropietario",
      query =
          """
        SELECT ra.AUTOMOTOR_PLACA_NUMPLACA placa,pc.CLASVEHIC_NOMBRE clase, pm.MARCA_NOMBRE marca, pl.LINEA_NOMBRE linea,  pc2.COLOR_PRIMARIO color, ra.AUTOMOTOR_ESTAVEHIC_NOMBRE estado
        FROM runtprod.TR_PROPIETAR tp
        JOIN runtprod.TR_PERSONA tp2 ON tp.PROPIETAR_PERSONA_IDPERSONA = tp2.PERSONA_IDPERSONA
        JOIN runtprod.RA_AUTOMOTOR ra ON ra.AUTOMOTOR_NROREGVEH = tp.PROPIETAR_AUTOMOTOR_NOREGVEHI
        LEFT JOIN runtprod.PA_MARCA pm ON pm.MARCA_IDMARCA = ra.AUTOMOTOR_MARCA_IDMARCA
        LEFT JOIN runtprod.PA_LINEA pl ON pl.LINEA_IDLINEA = ra.AUTOMOTOR_LINEA_IDLINEA
        LEFT JOIN runtprod.PA_CLASVEHIC pc ON pc.CLASVEHIC_IDCLASE = ra.AUTOMOTOR_CLASVEHIC_IDCLASE
        LEFT JOIN runtprod.PA_COLOR pc2 ON pc2.COLOR_IDCOLOR = ra.AUTOMOTOR_COLOR_IDCOLOR
        WHERE tp2.PERSONA_TIPOIDENT_IDTIPDOC = :tipoDocumento
          AND tp2.PERSONA_NRODOCUME = :numeroDocumento
          OR ra.AUTOMOTOR_PLACA_NUMPLACA ='S59975'
          OR ra.AUTOMOTOR_PLACA_NUMPLACA ='MC094473'
                                       """)
})
@Table(name = "ra_automotor", schema = "runtprod")
@Getter
@Setter
public class AutomotorEntity {
  @Id
  @Column(name = "AUTOMOTOR_NOREGVEHI")
  private Long id;

  @Column(name = "AUTOMOTOR_PLACA_NUMPLACA")
  private String placa;
}
