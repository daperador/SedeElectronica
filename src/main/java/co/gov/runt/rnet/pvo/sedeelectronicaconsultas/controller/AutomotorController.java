/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.controller;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.AutomotorDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.AutomotorInDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.DetalleAutomotorDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.SoatDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.repository.AutomotorRepository;
import co.gov.runt.rnf.procesadorsolicitudes.annotations.PrintLogs;
import co.gov.runt.utilidades.exception.ElementoNoEncontradoException;
import co.gov.runt.utilidades.utilities.InformacionUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel
 */
@RestController
@RequestMapping("automotor")
public class AutomotorController {

  @Autowired private AutomotorRepository automotorRepository;
  @Autowired private InformacionUsuario informacionUsuario;

  /**
   * Servicio para obtener la informacion de las propiedades de los vehiculos
   *
   * @return Lista de departamentos
   * @throws ElementoNoEncontradoException Error cuando no se pueden consultar los departamentos
   */
  @PrintLogs(methodName = "Obtener automotores por propietario")
  @GetMapping(path = "/propiedad", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<AutomotorDTO> consultaPorPropietario() throws ElementoNoEncontradoException {
    return automotorRepository.consultarPorPropietario(
        informacionUsuario.getTipoDocUsuario(), informacionUsuario.getNumDocUsuario());
  }

  /**
   * Servicio para obtener la informacion general del vehiculo
   *
   * @return Lista de detalle de automotorres
   */
  @PrintLogs(methodName = "Obtener automotores por propietario")
  @PostMapping(path = "/datosGenerales", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<DetalleAutomotorDTO> consultaGeneralAutomotor(@RequestBody AutomotorInDTO entrada ) {
    return automotorRepository.consultarPorId(entrada.getIdAutomotor(),
        informacionUsuario.getTipoDocUsuario(), informacionUsuario.getNumDocUsuario());
  }

  /**
   * Servicio para obtener la informacion general del vehiculo
   *
   * @return Lista de detalle de automotorres
   */
  @PrintLogs(methodName = "Obtener automotores por propietario")
  @PostMapping(path = "/consultaSoat", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<SoatDTO> consultaSoat(@RequestBody AutomotorInDTO entrada ) {
    return automotorRepository.consultarPorSoat(entrada.getPlaca());
  } 
}
