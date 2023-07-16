/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.controller;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.LicenciaDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.PersonaDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.repository.PersonaRepository;
import co.gov.runt.rnf.procesadorsolicitudes.annotations.PrintLogs;
import co.gov.runt.utilidades.exception.ElementoNoEncontradoException;
import co.gov.runt.utilidades.utilities.InformacionUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel
 */
@RestController
@RequestMapping("persona")
public class PersonaController {

  @Autowired private PersonaRepository personaRepository;
  @Autowired private InformacionUsuario informacionUsuario;

  /**
   * Servicio para obtener la informacion de los datos basicos de la persona
   *
   * @return Informacion de la persona
   * @throws ElementoNoEncontradoException Error cuando no se pueden consultar los departamentos
   */
  @PrintLogs(methodName = "Obtener automotores por propietario")
  @GetMapping(path = "/informacionPersona", produces = MediaType.APPLICATION_JSON_VALUE)
  public PersonaDTO consultaInformacionPersona() throws ElementoNoEncontradoException {
    return personaRepository.consultaInformacionPersona(
        informacionUsuario.getTipoDocUsuario(), informacionUsuario.getNumDocUsuario());
  }

  /**
   * Servicio para obtener la informacion de los datos basicos de la persona
   *
   * @return Informacion de la persona
   * @throws ElementoNoEncontradoException Error cuando no se pueden consultar los departamentos
   */
  @PrintLogs(methodName = "Obtener automotores por propietario")
  @GetMapping(path = "/informacionLicencia", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<LicenciaDTO> consultaInformacionLicencias() throws ElementoNoEncontradoException {
    return personaRepository.consultaLicencias(
        informacionUsuario.getTipoDocUsuario(), informacionUsuario.getNumDocUsuario());
  }
}
