package ar.edu.unahur.obj2.catering.AdicionalesYContratos;

import ar.edu.unahur.obj2.catering.ServicioYMenu.Propuesta;

public class Contrato {

    private final String nombreCliente;
    private final Double importe;
    private final String descripcion;

    public Contrato(String cliente, Propuesta propuesta) {
        this.nombreCliente = cliente;
        this.importe = propuesta.precio();
        this.descripcion = propuesta.descripcion();
    }


    public String cliente() {
        return nombreCliente;
    }

    public Double importe() {
        return importe;
    }

    public String descripcion() {
        return descripcion;
    }
    

}
