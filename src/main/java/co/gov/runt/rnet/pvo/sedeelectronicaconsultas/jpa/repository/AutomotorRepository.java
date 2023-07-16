/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.repository;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto.AutomotorDTO;
import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity.AutomotorEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Daniel
 */
public interface AutomotorRepository extends JpaRepository<AutomotorEntity, Long> {

  @Query(name = "Automotor.PorPropietario", nativeQuery = true)
  public List<AutomotorDTO> consultarPorPropietario(String tipoDocumento, String numeroDocumento);
}