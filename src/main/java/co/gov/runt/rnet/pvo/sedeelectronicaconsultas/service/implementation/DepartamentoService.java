package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.service.implementation;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.Departamento;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity.DepartamentoEntity;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.repository.DepartamentoRepository;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.mapper.DepartamentoMapper;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.service.IDepartamentoService;
import co.gov.runt.utilidades.exception.ElementoNoEncontradoException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa las funcionalidades de la interfaz DepartamentoPersistenceOutPort
 *
 * @since 1.0.0
 */
@Slf4j
@Service
public class DepartamentoService implements IDepartamentoService {

  @Autowired private DepartamentoMapper departamentoMapper;
  @Autowired private DepartamentoRepository departamentoRepository;

  @Override
  public Departamento getDepartamentoById(Long id) throws ElementoNoEncontradoException {
    Optional<DepartamentoEntity> optDepartamento = departamentoRepository.findById(id);
    if (optDepartamento.isPresent()) {
      return departamentoMapper.mapToDto(optDepartamento.get());
    } else {
      log.info("No se encontró el departamento {}", id);
      throw new ElementoNoEncontradoException("No se encontró el departamento " + id);
    }
  }

  @Override
  public List<Departamento> getDepartamentos() throws ElementoNoEncontradoException {
    List<DepartamentoEntity> departamentoEntityList = departamentoRepository.findAll();
    if (Objects.isNull(departamentoEntityList) || departamentoEntityList.isEmpty()) {
      log.info("No se encontraron departamentos");
      throw new ElementoNoEncontradoException("No se encontraron departamentos");
    } else {
      return departamentoMapper.mapToDto(departamentoEntityList);
    }
  }
}
