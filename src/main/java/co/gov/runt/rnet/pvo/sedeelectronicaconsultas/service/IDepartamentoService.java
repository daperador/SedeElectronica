package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.service;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.Departamento;
import co.gov.runt.utilidades.exception.ElementoNoEncontradoException;
import java.util.List;

/**
 * Interfaz con todos los métodos disponibles para gestionar Departamentos
 *
 * @since 1.0.0
 */
public interface IDepartamentoService {

  /**
   * Método encargado de obtener departamento por ID
   *
   * @param id Identificador único del departamento
   * @return Información del departamento
   * @throws ElementoNoEncontradoException Error cuando no se puede obtener el departamento
   * @since 1.0.0
   */
  public Departamento getDepartamentoById(Long id) throws ElementoNoEncontradoException;

  /**
   * Método encargado de listar departamentos
   *
   * @return Lista de departamentos
   * @throws ElementoNoEncontradoException Error cuando no se pueden consultar los departamentos
   * @since 1.0.0
   */
  public List<Departamento> getDepartamentos() throws ElementoNoEncontradoException;
}
