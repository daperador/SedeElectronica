/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.repository;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.CertificadoCRCDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.DetalleLicenciaDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.LicenciaDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.PersonaDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.SolicitudesDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity.PersonaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Daniel
 */
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

  @Query(name = "Persona.InformacionPersona", nativeQuery = true)
  public PersonaDTO consultaInformacionPersona(String tipoDocumento, String numeroDocumento);

  @Query(name = "Persona.informacionLicencia", nativeQuery = true)
  public List<LicenciaDTO> consultaLicencias(String tipoDocumento, String numeroDocumento);

  @Query(name = "Persona.detalleLicencia", nativeQuery = true)
  public List<DetalleLicenciaDTO> consultaDetalleLicencia(
      String nroLicencia, String tipoDocumento, String numeroDocumento);

  @Query(name = "Persona.solicitudes", nativeQuery = true)
  public List<SolicitudesDTO> consultaSolicitud(String tipoDocumento, String numeroDocumento);

  @Query(name = "Persona.consultaCertificados", nativeQuery = true)
  public List<CertificadoCRCDTO> consultaCertificados(String tipoDocumento, String numeroDocumento);

  @Query(name = "Persona.consultaCertificadosMedicos", nativeQuery = true)
  public List<CertificadoCRCDTO> consultaCertificadosMedicos(String tipoDocumento, String numeroDocumento);
}
