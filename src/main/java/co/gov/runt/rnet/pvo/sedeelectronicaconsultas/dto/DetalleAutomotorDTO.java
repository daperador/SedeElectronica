package co.gov.runt.rnet.pvo.sedeelectronicaconsultas.dto;

import java.util.Date;

public interface DetalleAutomotorDTO {
    
    public String getNoLicenciaTransito();
    public String getPlaca();
    public String getEstadoVehiculo();
    public String getTipoServicio();
    public String getClaseVehiculo();
    

    
    public String getMarca();
    public String getLinea();
    public String getModelo();
    public String getColor();
    public String getNoSerie();
    public String getNoMotor();
    public String getNoChasis();
    public String getNoVin();
    public String getCilidraje();
    public String getTipoCarroceria();
    public Date getFechaMatricula();
    public String getTipoCombustible();
    public String getOrganismoTransito();
    public String getTieneGravamenes();
    public String getClasicoAntiguo();

    public String getClasificacion();
    public String getRepotenciado();
    public String getEsRegrabadoMotor();
    public String getNumeroRegrabacionMotor();
    public String getEsRegrabadoChasis();
    public String getNumeroRegrabacionChasis();
    public String getEsRegrabadoSerie();
    public String getNumeroRegrabacionSerie();
    public String getEsRegrabadoVin();
    public String getNumeroRegrabacionVin();
    public String getPasajerosTotal();
    public String getPasajerosSentados();
    public String getTarjetaServicio();
    public String getVehiculoEnsenanza();
    public Integer getPuertas();

}
