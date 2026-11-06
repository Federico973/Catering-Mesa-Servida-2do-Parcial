package ar.edu.unahur.obj2.catering.AdicionalesYContratos;

import ar.edu.unahur.obj2.catering.ServicioYMenu.Propuesta;

public class Servicionocturno extends Adicional{

    public Servicionocturno(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected String nombre() {
       return "Servicio nocturno";
    }

    @Override
    protected Double aplicarAlPrecio(double precioAcumulado) {
        return precioAcumulado * 1.2;
    }

}
