package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto;

import java.util.Date;

public interface SoatDTO {
  public String getOrigen();

  public String getSoat();

  public Date getFechaExpedicion();

  public Date getFechaInicioPoliza();

  public Date getFechaVencimientoPoliza();

  public String getAseguradora();

  public String getEstado();
}
