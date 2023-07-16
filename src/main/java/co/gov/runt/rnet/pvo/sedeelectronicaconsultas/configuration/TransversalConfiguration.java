package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase que agrupa configuración de proyectos transversales del proyecto
 *
 * @since 1.0.0
 */
@Configuration
@ComponentScan(basePackages = {"co.gov.runt"})
@EnableJpaRepositories(basePackages = {"co.gov.runt"})
@EntityScan(basePackages = {"co.gov.runt"})
public class TransversalConfiguration {}
