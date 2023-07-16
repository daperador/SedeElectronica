package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.controller;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.Departamento;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.service.IDepartamentoService;
import co.gov.runt.rnf.procesadorsolicitudes.annotations.PrintLogs;
import co.gov.runt.utilidades.exception.ElementoNoEncontradoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase donde se implementan servicios REST para la gestión de departamentos
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("departamentos")
public class DepartamentoController {

  @Autowired private IDepartamentoService iDepartamentoService;

  /**
   * Servicio para obtener departamento por su ID
   *
   * @param id Identificador único del departamento a consultar
   * @return Departamento
   * @throws ElementoNoEncontradoException Error cuando no se puede obtener el departamento
   */
  @PrintLogs
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Departamento> obtenerDepartamentoId(@PathVariable Long id)
      throws ElementoNoEncontradoException {
    return new ResponseEntity<>(iDepartamentoService.getDepartamentoById(id), HttpStatus.OK);
  }

  /**
   * Servicio para obtener todos los departamentos
   *
   * @return Lista de departamentos
   * @throws ElementoNoEncontradoException Error cuando no se pueden consultar los departamentos
   */
  @PrintLogs(methodName = "Obtener Departamentos")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Departamento>> getDepartamentos()
      throws ElementoNoEncontradoException {
    return new ResponseEntity<>(iDepartamentoService.getDepartamentos(), HttpStatus.OK);
  }
}
