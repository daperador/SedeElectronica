package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.service.implementation;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.Departamento;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity.DepartamentoEntity;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.repository.DepartamentoRepository;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.mapper.DepartamentoMapper;
import co.gov.runt.utilidades.exception.ElementoNoEncontradoException;
import co.gov.runt.utilidades.exception.ErrorGeneralException;
import co.gov.runt.utilidades.utilities.UtilidadesJson;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class DepartamentoServiceTest {

  @Spy private DepartamentoMapper departamentoMapper = Mappers.getMapper(DepartamentoMapper.class);

  @Mock private DepartamentoRepository departamentoRepository;

  @InjectMocks private DepartamentoService departamentoService;

  private List<DepartamentoEntity> departamentoEntityList;

  @BeforeEach
  public void setup() throws ErrorGeneralException {
    departamentoEntityList =
        UtilidadesJson.cargarJsonDesdeArchivo("departamentos.json", new TypeReference<>() {});
  }

  @ParameterizedTest
  @CsvSource({"86", "23"})
  @DisplayName("Obtener departamento por id exitoso")
  void getDepartamentoByIdExitoso(Long departamentoId) throws ElementoNoEncontradoException {
    DepartamentoEntity departamento =
        departamentoEntityList.stream()
            .filter(depto -> departamentoId.equals(depto.getId()))
            .findAny()
            .orElse(null);

    Mockito.when(departamentoRepository.findById(departamentoId))
        .thenReturn(Optional.ofNullable(departamento));

    Departamento respuesta = departamentoService.getDepartamentoById(departamentoId);
    Assertions.assertNotNull(respuesta);
    Assertions.assertEquals(departamentoId, respuesta.getId());
  }

  @ParameterizedTest
  @CsvSource({"-1"})
  @DisplayName("Obtener departamento por id fallido")
  void getDepartamentoByIdNotFound(Long departamentoId) {
    Mockito.when(departamentoRepository.findById(departamentoId)).thenReturn(Optional.empty());
    ElementoNoEncontradoException ex =
        Assertions.assertThrows(
            ElementoNoEncontradoException.class,
            () -> departamentoService.getDepartamentoById(departamentoId));
    Assertions.assertNotNull(ex.getMessage());
    Assertions.assertEquals("No se encontró el departamento " + departamentoId, ex.getMessage());
  }

  @Test
  @DisplayName("Obtener departamentos exitoso")
  void getDepartamentos() throws ElementoNoEncontradoException {
    Mockito.when(departamentoRepository.findAll()).thenReturn(departamentoEntityList);
    List<Departamento> respuesta = departamentoService.getDepartamentos();
    Assertions.assertEquals(departamentoEntityList.size(), respuesta.size());
  }

  @Test
  @DisplayName("Obtener departamentos fallido")
  void getDepartamentosNotFound() {
    Mockito.when(departamentoRepository.findAll()).thenReturn(Collections.emptyList());
    ElementoNoEncontradoException ex =
        Assertions.assertThrows(
            ElementoNoEncontradoException.class, () -> departamentoService.getDepartamentos());
    Assertions.assertNotNull(ex.getMessage());
    Assertions.assertEquals("No se encontraron departamentos", ex.getMessage());
  }
}
