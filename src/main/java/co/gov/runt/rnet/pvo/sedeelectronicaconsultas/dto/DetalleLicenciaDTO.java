package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto;

import java.util.Date;

public interface DetalleLicenciaDTO {

  public String getCategoria();

  public String getCategoriaAnterior();

  public Date getFechaExpedicion();

  public Date getFechaVencimiento();
}
