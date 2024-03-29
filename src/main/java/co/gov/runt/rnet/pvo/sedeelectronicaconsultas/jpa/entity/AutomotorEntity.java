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
        SELECT ra.AUTOMOTOR_PLACA_NUMPLACA placa,pc.CLASVEHIC_NOMBRE clase,
          pm.MARCA_NOMBRE marca, pl.LINEA_NOMBRE linea,
          pc2.COLOR_PRIMARIO color, ra.AUTOMOTOR_ESTAVEHIC_NOMBRE estado,
          ra.AUTOMOTOR_NROREGVEH idAutomotor
        FROM runtprod.TR_PROPIETAR tp
        JOIN runtprod.TR_PERSONA tp2 ON tp.PROPIETAR_PERSONA_IDPERSONA = tp2.PERSONA_IDPERSONA
        JOIN runtprod.RA_AUTOMOTOR ra ON ra.AUTOMOTOR_NROREGVEH = tp.PROPIETAR_AUTOMOTOR_NOREGVEHI
        LEFT JOIN runtprod.PA_MARCA pm ON pm.MARCA_IDMARCA = ra.AUTOMOTOR_MARCA_IDMARCA
        LEFT JOIN runtprod.PA_LINEA pl ON pl.LINEA_IDLINEA = ra.AUTOMOTOR_LINEA_IDLINEA
        LEFT JOIN runtprod.PA_CLASVEHIC pc ON pc.CLASVEHIC_IDCLASE = ra.AUTOMOTOR_CLASVEHIC_IDCLASE
        LEFT JOIN runtprod.PA_COLOR pc2 ON pc2.COLOR_IDCOLOR = ra.AUTOMOTOR_COLOR_IDCOLOR
        WHERE tp2.PERSONA_TIPOIDENT_IDTIPDOC = :tipoDocumento
          AND tp2.PERSONA_NRODOCUME = :numeroDocumento
                                       """),
  @NamedNativeQuery(
      name = "Automotor.datosGenerales",
      query =
          """
    SELECT LIC.LICETRANS_NUMLICTRA AS noLicenciaTransito,
    VE.AUTOMOTOR_PLACA_NUMPLACA AS placa,
    VE.AUTOMOTOR_TIPOSERVI_IDETIPSER AS IDTIPOSERVICIO,
    VE.AUTOMOTOR_CLASVEHIC_IDCLASE AS IDCLASE,
    VE.AUTOMOTOR_COLOR_IDCOLOR AS IDCOLOR,
    VE.AUTOMOTOR_LINEA_IDLINEA AS IDLINEA,
    VE.AUTOMOTOR_MARCA_IDMARCA AS IDMARCA,
    VE.AUTOMOTOR_NIVESERVI_IDNIVSER AS IDNIVELSERVICIO,
    VE.AUTOMOTOR_MODASERVI_IDEMODSER AS IDMODALIDADSERVICIO,
    VE.AUTOMOTOR_TIPOCARRO_IDCARROCE AS IDTIPOCARROCERIA,
    VE.AUTOMOTOR_TIPOMOTOR_IDTIPMOT AS IDTIPOMOTOR,
    VE.AUTOMOTOR_PAIS_IDPAISORI AS IDPAISORIGEN,
    VE.AUTOMOTOR_MODELO AS MODELO,
    VE.AUTOMOTOR_FECHREGIS AS fechaMatricula,
    VE.AUTOMOTOR_AUTOTRANS_IDAUTTRA AS ID_AUTORIDADTRANSITO,
    VE.AUTOMOTOR_IDVEUNINT AS noVin,
    VE.AUTOMOTOR_IMPORTADO_NOMBRE AS AUTOMOTORIMPORTADO,
    VE.AUTOMOTOR_NROCHASIS AS noChasis,
    VE.AUTOMOTOR_NROMOTOR AS noMotor,
    VE.AUTOMOTOR_NROREGCHA AS NumeroRegrabacionChasis,
    VE.AUTOMOTOR_NROREGMOT AS NumeroRegrabacionMotor,
    VE.AUTOMOTOR_NROREGSER AS NumeroRegrabacionSerie,
    VE.AUTOMOTOR_NROREGVIN AS NumeroRegrabacionVin,
    CASE WHEN VE.AUTOMOTOR_NROREGCHA IS NULL THEN 'NO' ELSE 'SI' END AS ESREGRABADOCHASIS,
    CASE WHEN VE.AUTOMOTOR_NROREGMOT IS NULL THEN 'NO' ELSE 'SI' END AS ESREGRABADOMOTOR,
    CASE WHEN VE.AUTOMOTOR_NROREGSER IS NULL THEN 'NO' ELSE 'SI' END AS ESREGRABADOSERIE,
    CASE WHEN VE.AUTOMOTOR_NROREGVIN IS NULL THEN 'NO' ELSE 'SI' END AS ESREGRABADOVIN,
    VE.AUTOMOTOR_NROSERIE AS noSerie,
    VE.AUTOMOTOR_CILINDRAJ AS CILINDRAJE,
    CASE WHEN VE.AUTOMOTOR_SEGUESTAD = 'SI' THEN VE.AUTOMOTOR_SEGUESTAD
       ELSE (SELECT CASE WHEN COUNT(1) > 0 THEN 'SI' ELSE 'NO' END FROM RUNTPROD.TR_PROPIETAR WHERE PROPIETAR_TIPOPROPI_CODTIPPRO = 2 AND PROPIETAR_ESTADO_NOMBRE = 'ACTIVO' AND PROPIETAR_FECFINPRO IS NULL AND PROPIETAR_AUTOMOTOR_NOREGVEHI = VE.AUTOMOTOR_NROREGVEH)
          END AS SEGURIDAD_ESTADO,
    VE.AUTOMOTOR_ESTAVEHIC_NOMBRE AS estadoVehiculo,
    VE.AUTOMOTOR_UNIMEDCIL_ID AS AUTOMOTOR_UNIMEDCIL_CODIGO,
    VE.AUTOMOTOR_PLAQUETA AS NUM_PLAQUETA,
    VE.AUTOMOTOR_FECHCANCE AS FECHA_CANCELACION_MATRICULA,
    VE.AUTOMOTOR_MOTIVO_NOMBRE AS MOTIVO_CANCELACION_MATRICULA,
    CASE WHEN VE.AUTOMOTOR_REPOTENCI ='S' THEN 'SI' ELSE 'NO' END AS REPOTENCIADO,
    VE.AUTOMOTOR_ESTAREPOS AS VEHICULO_REPUESTO,
    VE.AUTOMOTOR_ENSENAZA AS ADAPTACION_ENSENANSA,
    CASE WHEN VE.AUTOMOTOR_ANTICLASI_NOMBRE IN ('ANTIGUO','CLASICO') THEN 'SI' ELSE 'NO' END AS clasicoAntiguo,
    VE.AUTOMOTOR_NROREGPLA AS NUM_REGRA_PLAQUETA,
    VE.AUTOMOTOR_ANOFABRIC AS ANO_FABRICACION,
    VE.AUTOMOTOR_MIGRADO AS MIGRADO,
    VE.AUTOMOTOR_TIPACTREM_NOMBRE AS TIPO_ACTA_VEHICULO,
    VE.AUTOMOTOR_ORIGREGIS_IDEORGREG AS ID_ORIGEN_REGISTRO,
    VE.AUTOMOTOR_ANTIDECLA AS NRO_ACEPTACION,
    VE.AUTOMOTOR_FECACEDEC AS FECHA_ACEPTACION,
    CC.CLASVEHIC_NOMBRE AS claseVehiculo,
    CLC.CLASCLASE_CLASE_NOMBRE AS CLASIFICACION,
   (
   SELECT CASE WHEN COUNT(*) >= 1 THEN 'SI' ELSE 'NO' END AS MEDIDAS
    FROM runtprod.RA_MEDICAUTE ME
    WHERE ME.MEDICAUTE_AUTOMOTOR_NROREGVEH = AUTOMOTOR_NROREGVEH
    AND ME.MEDICAUTE_ESTMEDCAU_NOMBRE = 'INSCRITA'
   ) AS tieneGravamenes,
   (
   SELECT CASE WHEN COUNT(*) >= 1 THEN 'SI' ELSE 'NO' END AS PRENDA
     FROM  RUNTPROD.RA_PRENDA
     WHERE PRENDA_AUTOMOTOR_NROREGVEH=AUTOMOTOR_NROREGVEH
     AND PRENDA_ESTADO = 'INSCRITA'
   ) AS PRENDAS,
    CO.COLOR_CODCOLMIN,
    CO.COLOR_TIPOCOLOR,
    CO.COLOR_PRIMARIO color,
    CO.COLOR_ESTADO,
    CO.COLOR_GAMACOLOR_NOMBRE,
    CASE WHEN TA.TECNAUTOM_CAPACARGA IS NOT NULL THEN TA.TECNAUTOM_CAPACARGA||' '||UC.UNIMEDCAR_NOMBRE ELSE '' END AS CAPACIDAD_CARGA,
    TA.TECNAUTOM_UNIMEDCAR_CODIGO AS COD_UNIDAD_MEDIDA_CARGA,
    TA.TECNAUTOM_CAPAPASAJ AS pasajerosTotal,
    TA.TECNAUTOM_PASASENTA AS PASAJEROSSENTADOS,
    TA.TECNAUTOM_PUERTAS AS NUMERO_PUERTAS,
    TA.TECNAUTOM_PESO AS PESO_BRUTO,
    TA.TECNAUTOM_NROEJES AS NUMERO_EJES,
    TS.TIPOSERVI_NOMBRE AS tipoServicio,
    TS.TIPOSERVI_ESTADO AS ESTADO_TIPO_SERVICIO,
    LI.LINEA_NOMBRE AS LINEA,
    MA.MARCA_NOMBRE AS MARCA,
    MA.MARCA_ABREVIATU,
    TC.TIPOCARRO_NOMBRE    AS tipoCarroceria,
    TC.TIPOCARRO_ABREVIATU,
    EM.EMPRESA_RAZOSOCIA    AS organismoTransito,
    MS.MODASERVI_NOMBRE,
    UC.UNIMEDCIL_NOMBRE,
    IM.IMPORTADO_DESCRIPCI,
    NS.NIVESERVI_NOMBRE   AS NIVEL_SERVICIO,
    NS.NIVESERVI_ESTADO   AS ESTADO_NIVEL_SERVICIO,
    MT.TIPOMOTOR_NOMBRE   AS TIPO_MOTOR,
    PA.PAIS_NOMBRE   AS PAIS_ORIGEN,
    TA.TECNAUTOM_POTENCIA   AS POTENCIA,
    ORE.ORIGREGIS_NOMBRE   AS ORIGEN_REGISTRO,
    UC.UNIMEDCAR_NOMBRE   AS UNIDAD_MEDIDA_CARGA,
    TS.TIPOSERVI_IDETIPSER AS ID_TIPO_SERVICIO,
    CC.CLASVEHIC_IDCLASE  AS ID_CLASE_VEHICULO,
    extract(day FROM ( CURRENT_TIMESTAMP - VE.AUTOMOTOR_FECHREGIS)) AS DIAS_MATRICULADO,
    TCOM.TIPOCOMBU_DESCRIPCI AS tipoCombustible,
    TA.TECNAUTOM_CAPAPASAJ AS PASAJEROS_TOTAL,
    TA.TECNAUTOM_PASASENTA AS PASAJEROS_SENTADOS,
    (
    SELECT CASE WHEN COUNT(*) >= 1 THEN 'SI' ELSE 'NO' END AS TSERV
    FROM RUNTPROD.EV_TARJSERVI TARSERV
    WHERE VE.AUTOMOTOR_NROREGVEH = TARSERV.TARJSERVI_AUTOMOTOR_NROREGVEH
    AND TARSERV.TARJSERVI_ESTLICTRA_NOMBRE = 'ACTIVO'
    ) AS VehiculoEnsenanza,
    LICI.LICTRAIMP_NUMLICTRA AS LICTRAIMP_NUMLICTRA,
    TO_CHAR(LICI.LICTRAIMP_FECHEXPED, 'DD/MM/YYYY') AS LICTRAIMP_FECHEXPED,
    TO_CHAR(LICI.LICTRAIMP_FECHVENCI, 'DD/MM/YYYY') AS LICTRAIMP_FECHVENCI,
    TA.TECNAUTOM_PUERTAS AS PUERTAS
  FROM runtprod.RA_AUTOMOTOR VE,
    runtprod.PA_CLASVEHIC CC,
    runtprod.PA_COLOR CO,
    runtprod.PA_TIPOSERVI TS,
    runtprod.PA_LINEA LI,
    runtprod.PA_MARCA MA,
    runtprod.PA_TIPOCARRO TC,
    runtprod.GE_AUTOTRANS OT,
    runtprod.GE_EMPRESA EM,
    runtprod.PA_MODASERVI MS,
    runtprod.PA_IMPORTADO IM,
    runtprod.PA_UNIMEDCIL UC,
    runtprod.RA_TECNAUTOM TA,
    runtprod.PA_NIVESERVI NS,
    runtprod.PA_CLASCLASE CLC,
    runtprod.PA_TIPOMOTOR MT,
    runtprod.PA_PAIS PA,
    runtprod.PA_ORIGREGIS ORE,
    runtprod.PA_UNIMEDCAR UC,
    RUNTPROD.EV_LICETRANS LIC,
    RUNTPROD.EV_LICTRAIMP LICI,
    RUNTPROD.RA_TIPCOMVEH TCOMV,
    RUNTPROD.PA_TIPOCOMBU TCOM,
    RUNTPROD.TR_PROPIETAR pro,
    RUNTPROD.TR_PERSONA perso
  WHERE VE.AUTOMOTOR_CLASVEHIC_IDCLASE = CC.CLASVEHIC_IDCLASE
    AND VE.AUTOMOTOR_COLOR_IDCOLOR = CO.COLOR_IDCOLOR(+)
    AND VE.AUTOMOTOR_TIPOSERVI_IDETIPSER = TS.TIPOSERVI_IDETIPSER(+)
    AND VE.AUTOMOTOR_LINEA_IDLINEA = LI.LINEA_IDLINEA(+)
    AND VE.AUTOMOTOR_MARCA_IDMARCA = MA.MARCA_IDMARCA(+)
    AND VE.AUTOMOTOR_TIPOMOTOR_IDTIPMOT = MT.TIPOMOTOR_IDTIPMOTO(+)
    AND VE.AUTOMOTOR_NIVESERVI_IDNIVSER = NS.NIVESERVI_IDNIVSERV(+)
    AND VE.AUTOMOTOR_TIPOCARRO_IDCARROCE = TC.TIPOCARRO_IDCARROCE(+)
    AND VE.AUTOMOTOR_AUTOTRANS_IDAUTTRA = OT.AUTOTRANS_IDAUTTRA(+)
    AND VE.AUTOMOTOR_ORIGREGIS_IDEORGREG = ORE.ORIGREGIS_IDEORGREG(+)
    AND OT.AUTOTRANS_EMPRESA_PERSONA = EM.EMPRESA_PERSONA_IDPERSONA(+)
    AND VE.AUTOMOTOR_PAIS_IDPAISORI = PA.PAIS_IDPAIS(+)
    AND CC.CLASVEHIC_IDCLASE = CLC.CLASCLASE_CLASVEHIC_IDCLASE(+)
    AND VE.AUTOMOTOR_MODASERVI_IDEMODSER = MS.MODASERVI_IDEMODSER(+)
    AND VE.AUTOMOTOR_IMPORTADO_NOMBRE = IM.IMPORTADO_NOMBRE(+)
    AND VE.AUTOMOTOR_UNIMEDCIL_ID = UC.UNIMEDCIL_ID(+)
    AND VE.AUTOMOTOR_NROREGVEH = TA.TECNAUTOM_AUTOMOTOR_NROREGVEH(+)
    AND TA.TECNAUTOM_UNIMEDCAR_CODIGO = UC.UNIMEDCAR_CODIGO(+)
    AND LIC.LICETRANS_AUTOMOTOR_NROREGVEH(+) = VE.AUTOMOTOR_NROREGVEH
    AND TCOMV.TIPCOMVEH_AUTOMOTOR_NROREGVEH(+) = VE.AUTOMOTOR_NROREGVEH
    AND TCOMV.TIPCOMVEH_ESTADO_NOMBRE(+)='ACTIVO'
    AND TCOMV.TIPCOMVEH_TIPOCOMBU_NOMBRE=TCOM.TIPOCOMBU_NOMBRE(+)
    AND LICI.LICTRAIMP_AUTOMOTOR_NROREGVEH(+) = VE.AUTOMOTOR_NROREGVEH
    AND pro.PROPIETAR_AUTOMOTOR_NOREGVEHI = ve.AUTOMOTOR_NROREGVEH
    AND perso.PERSONA_IDPERSONA = pro.PROPIETAR_PERSONA_IDPERSONA
    AND pro.PROPIETAR_ESTADO_NOMBRE = 'ACTIVO'
    AND perso.PERSONA_TIPOIDENT_IDTIPDOC = :tipoDocumento
    AND perso.PERSONA_NRODOCUME = :numeroDocumento
    AND VE.AUTOMOTOR_NROREGVEH = :idAutomotor
      """),
  @NamedNativeQuery(
      name = "Automotor.consultaSOAT",
      query =
          """
        ( SELECT
        'NACIONAL'           AS ORIGEN,
        PS.POLISEGUR_POLIZASEG    AS soat,
        PS.POLISEGUR_FECEXPPOL       AS fechaExpedicion,
        PS.POLISEGUR_FECINIPOL fechaInicioPoliza,
        (CASE WHEN PS.POLISEGUR_FECINIPOL < (SELECT TO_DATE(CONSTANTE_VALOR,'dd/MM/yyyy') FROM RUNTPROD.COM_CONSTANTE WHERE CONSTANTE_NOMBRE = 'FECHA_INICIO_VALIDACION_SOAT' AND CONSTANTE_GRUPO='RNA')
        THEN 1 ELSE 0
        END)      AS FECHA_INICIO_POLIZA,
        PS.POLISEGUR_FECVENPOL AS fechaVencimientoPoliza,
        CASE
        WHEN PS.POLISEGUR_ESTAPOLIZ_NOMBRE IS NULL OR LENGTH(PS.POLISEGUR_ESTAPOLIZ_NOMBRE)=0
        THEN PN.persnatur_nombre1 ||' '||PN.persnatur_nombre2||PN.persnatur_apellido1||' '||PN.persnatur_apellido2
        ELSE EMA.EMPRESA_RAZOSOCIA END AS aseguradora,
        CASE WHEN (PS.POLISEGUR_FECVENPOL>=TRUNC(SYSDATE) AND PS.POLISEGUR_FECINIPOL <= TRUNC(SYSDATE)) THEN 'VIGENTE' ELSE 'NO VIGENTE' END AS ESTADO
        FROM RUNTPROD.GE_EMPRESA EMA,
        RUNTPROD.RS_POLISEGUR PS,
        RUNTPROD.TR_PERSNATUR PN
        WHERE PS.POLISEGUR_ASEGURADO_NROREGASE = EMA.EMPRESA_PERSONA_IDPERSONA(+)
        AND PS.POLISEGUR_ASEGURADO_NROREGASE = PN.PERSNATUR_PERSONA_IDPERSONA(+)
        AND PS.POLISEGUR_ESTAPOLIZ_NOMBRE IN ('EMITIDA','REEMPLAZO')
        AND PS.POLISEGUR_TIPOSEGUR_IDTIPSEG = 1
        AND POLISEGUR_NROPLACA=:placa)
       UNION
       (SELECT
       'INTERNACIONAL'           AS ORIGEN,
       PS.POLSEGEXT_POLIZASEG       AS soat,
       PS.POLSEGEXT_FECEXPPOL       AS fechaExpedicion,
       PS.POLSEGEXT_FECINIPOL  fechaInicioPoliza,
       (CASE WHEN PS.POLSEGEXT_FECINIPOL < (SELECT TO_DATE(CONSTANTE_VALOR,'dd/MM/yyyy') FROM RUNTPROD.COM_CONSTANTE WHERE CONSTANTE_NOMBRE = 'FECHA_INICIO_VALIDACION_SOAT' AND CONSTANTE_GRUPO='RNA')
       THEN 1 ELSE 0
       END)        AS FECHA_INICIO_POLIZA,
       PS.POLSEGEXT_FECVENPOL AS fechaVencimientoPoliza,
       EMP.EMPRESA_RAZOSOCIA AS aseguradora,
       CASE WHEN (PS.POLSEGEXT_FECVENPOL>=TRUNC(SYSDATE) AND PS.POLSEGEXT_FECINIPOL <= TRUNC(SYSDATE)) THEN 'VIGENTE' ELSE 'NO VIGENTE' END AS ESTADO
       FROM RUNTPROD.GE_EMPRESA EMP,
            RUNTPROD.RS_POLSEGEXT PS
       WHERE PS.POLSEGEXT_ASEGURADO_NROREGASE = EMP.EMPRESA_PERSONA_IDPERSONA
         AND PS.POLSEGEXT_ESTAPOLIZ_NOMBRE IN ('EMITIDA','REEMPLAZO')
         AND PS.POLSEGEXT_TIPOSEGUR_IDTIPSEG = 1
         AND POLSEGEXT_POLIZASEG=:placa)
       ORDER BY ESTADO DESC, FECHAEXPEDICION DESC, soat DESC
          """),
  @NamedNativeQuery(
      name = "Automotor.consultaRTM",
      query =
          """
            SELECT * FROM (
              SELECT
              RETEMEGAS_FECHEXPED AS fechaExpedicion,
              RETEMEGAS_FECHVENCI   AS fechaVencimiento,
              EMPRESA_RAZOSOCIA AS entidad,
              RETEMEGAS_ESTAPRUEB_NOMBRE AS estado,
              RETEMEGAS_REVTECADA_NOMBRE AS tipoRevision,
              CASE WHEN (TRUNC(SYSDATE)-RETEMEGAS_FECHVENCI)<=0 AND RETEMEGAS_ESTAPRUEB_NOMBRE= 'APROBADA' THEN 'SI' ELSE 'NO' END AS VIGENTE,
              RETEMEGAS_NROCERREV AS certificado,
              RV.RETEMEGAS_NUMPLACA AS NUMERO_PLACA,
              CASE
              WHEN TM.TRAREVTEC_INDIEXTRA = 'NO' THEN 'SI'
              WHEN TM.TRAREVTEC_INDIEXTRA IS NULL THEN TM.TRAREVTEC_INDIEXTRA
              ELSE 'NO' END AS informacionConsistente
              FROM RUNTPROD.RA_RETEMEGAS RV
              INNER JOIN RUNTPROD.GE_EMPRESA EM ON RV.RETEMEGAS_EMPRESA_IDPERSONA = EM.EMPRESA_PERSONA_IDPERSONA
              LEFT JOIN RUNTPROD.GE_SOLICITUD SOL  ON RV.RETEMEGAS_SOLICITUD_IDENSOLIC  = SOL.SOLICITUD_IDENSOLIC
              LEFT JOIN RUNTPROD.GE_TRAMITE GETRA  ON GETRA.TRAMITE_SOLICITUD_IDENSOLIC = SOL.SOLICITUD_IDENSOLIC
              LEFT JOIN RUNTPROD.TM_TRAREVTEC TM   ON TM.TRAREVTEC_TRAMITE_IDTRAMITE    = GETRA.TRAMITE_IDTRAMITE
              WHERE RETEMEGAS_ESTAPRUEB_NOMBRE='APROBADA'
                AND RETEMEGAS_NUMPLACA=:placa
              ORDER BY RETEMEGAS_FECHVENCI DESC
              )
              WHERE ROWNUM<=(SELECT CAST(CONSTANTE_VALOR AS INT) FROM RUNTPROD.COM_CONSTANTE WHERE CONSTANTE_NOMBRE = 'CANTIDAD_RTM_A_PRESENTAR' AND CONSTANTE_GRUPO='RNA')
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
