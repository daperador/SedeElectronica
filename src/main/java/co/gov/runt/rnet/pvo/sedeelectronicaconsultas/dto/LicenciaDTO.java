/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto;

import java.util.Date;

/**
 * @author Daniel
 */
public interface LicenciaDTO {
  public Long getNumeroLicencia();

  public String getCategoriaAnterior();

  public Date getFechaExpedicion();

  public String getEstadoLicencia();

  public String getOrganismoTransito();

  public String getRestricciones();

  public Date getFechaInicioSuspension();

  public Date getFechaFinSuspension();
}
