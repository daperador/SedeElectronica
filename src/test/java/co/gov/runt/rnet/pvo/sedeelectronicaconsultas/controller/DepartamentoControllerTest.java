package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.Departamento;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.service.IDepartamentoService;
import co.gov.runt.utilidades.exception.ElementoNoEncontradoException;
import co.gov.runt.utilidades.exception.ErrorGeneralException;
import co.gov.runt.utilidades.utilities.UtilidadesJson;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@Slf4j
@ExtendWith(MockitoExtension.class)
class DepartamentoControllerTest {

  @Mock private IDepartamentoService departamentoService;
  @InjectMocks private DepartamentoController departamentoController;

  private List<Departamento> departamentoList;

  @BeforeEach
  public void setup() throws ErrorGeneralException {
    departamentoList =
        UtilidadesJson.cargarJsonDesdeArchivo("departamentos.json", new TypeReference<>() {});
  }

  @ParameterizedTest
  @CsvSource({"86"})
  @DisplayName("Obtener departamento por id exitoso")
  void obtenerDepartamentoIdExitoso(Long departamentoId) throws ElementoNoEncontradoException {
    Departamento departamento =
        departamentoList.stream()
            .filter(depto -> departamentoId.equals(depto.getId()))
            .findAny()
            .orElse(null);

    when(departamentoService.getDepartamentoById(departamentoId)).thenReturn(departamento);
    var departamentoResp = departamentoController.obtenerDepartamentoId(departamentoId);
    assertEquals(ResponseEntity.ok(departamento), departamentoResp);
  }

  @ParameterizedTest
  @CsvSource({"-1"})
  @DisplayName("Obtener departamento por id fallido")
  void obtenerDepartamentoIdNoExiste(Long departamentoId) throws ElementoNoEncontradoException {
    when(departamentoService.getDepartamentoById(departamentoId))
        .thenThrow(
            new ElementoNoEncontradoException("No se encontró el departamento " + departamentoId));
    assertThrows(
        ElementoNoEncontradoException.class,
        () -> departamentoController.obtenerDepartamentoId(departamentoId));
    verify(departamentoService).getDepartamentoById(departamentoId);
  }

  @Test
  @DisplayName("Obtener departamentos exitoso")
  void getDepartamentosExitoso() throws ElementoNoEncontradoException {
    when(departamentoService.getDepartamentos()).thenReturn(departamentoList);
    var departamentosResp = departamentoController.getDepartamentos();
    assertEquals(ResponseEntity.ok(departamentoList), departamentosResp);
  }

  @Test
  @DisplayName("Obtener departamentos fallido")
  void getDepartamentosNotFound() throws ElementoNoEncontradoException {
    when(departamentoService.getDepartamentos())
        .thenThrow(new ElementoNoEncontradoException("No se encontraron departamentos"));
    assertThrows(
        ElementoNoEncontradoException.class, () -> departamentoController.getDepartamentos());
    verify(departamentoService).getDepartamentos();
  }
}
