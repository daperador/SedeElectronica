package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto;

import java.util.Date;

public interface RtmDTO {

  public String getTipoRevision();

  public Date getFechaExpedicion();

  public Date getFechaVencimiento();

  public String getEntidad();

  public String getVigente();

  public String getCertificado();

  public String getInformacionConsistente();

  public String getEstado();
}
