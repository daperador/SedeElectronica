package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.repository;

import co.gov.runt.rnet.pvo.sedeelectronicaconsultas.jpa.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar la información de la
 * entidad departamento
 *
 * @since 1.0.0
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> {}
