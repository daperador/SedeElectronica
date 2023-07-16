package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.mapper;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.Departamento;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity.DepartamentoEntity;
import java.util.List;
import org.mapstruct.Mapper;

/** Mapeos entre objetos departamento */
@Mapper(componentModel = "spring")
public interface DepartamentoMapper {
  Departamento mapToDto(DepartamentoEntity entidad);

  DepartamentoEntity mapToEntity(Departamento departamento);

  List<Departamento> mapToDto(List<DepartamentoEntity> departamentos);

  List<DepartamentoEntity> mapToEntity(List<Departamento> departamentos);
}
