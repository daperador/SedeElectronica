/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto;

import java.util.Date;

/**
 * @author Daniel
 */
public interface PersonaDTO {
  public Long getId();

  public String getTipoDocumento();

  public String getNumeroDocumento();

  public String getNombres();

  public String getApellidos();

  public Date getFechaRegistro();

  public String getActivo();

  public String getEstado();
}
