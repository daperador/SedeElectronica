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
@Table(name = "tr_persona", schema = "runtprod")
@NamedNativeQueries({
  @NamedNativeQuery(
      name = "Persona.InformacionPersona",
      query =
          """
        SELECT PERSONA_IDPERSONA id,
           TIPOIDENT_VISUALIZA tipoDocumento,
           PERSONA_NRODOCUME numeroDocumento,
           PERSNATUR_NOMBRE1||' '||
           PERSNATUR_NOMBRE2 AS NOMBRES,
           PERSNATUR_APELLIDO1||' '||
           PERSNATUR_APELLIDO2 AS APELLIDOS,
           CASE PERSNATUR_MIGRADO
             WHEN 'M' THEN NULL
             ELSE PERSONA_FECHAREGI
           END AS fechaRegistro,
         (CASE SANPERNAT_TIPNOVSUS_NOMBRE WHEN 'CANCELACION' THEN 'CANCELADO' WHEN 'SUSPENSION' THEN 'SUSPENDIDO' ELSE 'ACTIVO' END)
           AS ACTIVO,
         PERSONA_ESTAPERSO_NOMBRE estado
          FROM RUNTPROD.TR_PERSONA
           LEFT JOIN RUNTPROD.TR_PERSNATUR ON PERSONA_IDPERSONA=PERSNATUR_PERSONA_IDPERSONA
           LEFT JOIN RUNTPROD.PA_TIPOIDENT ON PERSONA_TIPOIDENT_IDTIPDOC=TIPOIDENT_IDTIPIDE
           LEFT JOIN RUNTPROD.GE_SANPERNAT ON PERSONA_TIPOIDENT_IDTIPDOC=SANPERNAT_TIPOIDENT_IDTIPDOC
           AND PERSONA_NRODOCUME=SANPERNAT_NRODOCUME
           AND SANPERNAT_ESTADO_NOMBRE ='ACTIVO'
           and (SANPERNAT_TIPNOVSUS_NOMBRE='SUSPENSION'or SANPERNAT_TIPNOVSUS_NOMBRE='CANCELACION')
        WHERE persona_tipoident_idtipdoc =:tipoDocumento
          AND persona_nrodocume =:numeroDocumento
          AND PERSONA_ESTAPERSO_NOMBRE IN ('ACTIVA', 'SIN REGISTRO')
                                     """),
  @NamedNativeQuery(
      name = "Persona.informacionLicencia",
      query =
          """
        SELECT LICECONDU_NROLICENC numeroLicencia,
         LICECONDU_CATEANTER categoriaAnterior,
         LICECONDU_FECHEXPED fechaExpedicion,
         LICECONDU_ESTLICCON_NOMBRE estadoLicencia,
         EMPRESA_RAZOSOCIA organismoTransito,
         (
         	SELECT regexp_replace(rtrim(xmlagg(xmlelement(K,RESTRICIO_DESCRIPCI,', ').extract('//text()') order by RESTCONDU_RESTRICIO_NOMBRE),','),'..$') AS RESTRICCIONES
         	FROM (
        		SELECT DISTINCT RESTRICIO_DESCRIPCI,RESTCONDU_RESTRICIO_NOMBRE,RESTCONDU_LICECONDU_NROLICENC
        		FROM RUNTPROD.RC_RESTCONDU
         		JOIN RUNTPROD.PA_RESTRICIO ON RUNTPROD.PA_RESTRICIO.RESTRICIO_NOMBRE=RESTCONDU_RESTRICIO_NOMBRE and restricio_estado_nombre='ACTIVO'
         		)
        	WHERE RESTCONDU_LICECONDU_NROLICENC = LICECONDU_NROLICENC
         ) AS RESTRICCIONES,
         (
         	SELECT CANCLICEN_FECINIMED
        	FROM RUNTPROD.RC_CANCLICEN
         	WHERE LICECONDU_NROLICENC = CANCLICEN_LICECONDU_NROLICENC
         	  AND CANCLICEN_ESTADO_NOMBRE='ACTIVO' AND CANCLICEN_ESTLICCON_NOMBRE = LICECONDU_ESTLICCON_NOMBRE
         	  AND (CANCLICEN_FECHMEDID=(
         	  	SELECT MAX(CANCLICEN_FECHMEDID)
                FROM RUNTPROD.RC_CANCLICEN
                WHERE CANCLICEN_LICECONDU_NROLICENC = LICECONDU_NROLICENC
                )
              OR CANCLICEN_FECHMEDID IS NULL)
              AND ROWNUM <= 1
         ) AS fechaInicioSuspension,
         (
         SELECT CANCLICEN_FECHMEDID
         FROM RUNTPROD.RC_CANCLICEN
         WHERE LICECONDU_NROLICENC = CANCLICEN_LICECONDU_NROLICENC
           AND CANCLICEN_ESTADO_NOMBRE='ACTIVO' AND CANCLICEN_ESTLICCON_NOMBRE = LICECONDU_ESTLICCON_NOMBRE
           AND CANCLICEN_FECHMEDID=(
             SELECT MAX(CANCLICEN_FECHMEDID)
             FROM RUNTPROD.RC_CANCLICEN
             WHERE CANCLICEN_LICECONDU_NROLICENC = LICECONDU_NROLICENC
           )
           AND ROWNUM <= 1
         ) AS fechaFinSuspension
        FROM RUNTPROD.EV_LICECONDU LIC
        JOIN runtprod.tr_persona tp ON tp.PERSONA_IDPERSONA = lic.LICECONDU_PERSNATUR_IDPERSONA
        LEFT JOIN RUNTPROD.GE_AUTOTRANS AU ON AU.AUTOTRANS_IDAUTTRA = LIC.LICECONDU_AUTOTRANS_IDAUTTRA
        LEFT JOIN RUNTPROD.GE_EMPRESA EM ON EM.EMPRESA_PERSONA_IDPERSONA = AU.AUTOTRANS_EMPRESA_PERSONA
        WHERE persona_tipoident_idtipdoc = :tipoDocumento
          AND persona_nrodocume = :numeroDocumento
        order by LICECONDU_FECHEXPED DESC
                                    """)
})
@Getter
@Setter
public class PersonaEntity {

  @Id
  @Column(name = "PERSONA_IDPERSONA")
  private Long id;
}
