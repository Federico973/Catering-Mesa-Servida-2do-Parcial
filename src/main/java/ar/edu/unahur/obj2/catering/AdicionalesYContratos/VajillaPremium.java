package ar.edu.unahur.obj2.catering.AdicionalesYContratos;

import ar.edu.unahur.obj2.catering.ServicioYMenu.Propuesta;

public class VajillaPremium extends Adicional{

    public VajillaPremium(Propuesta propuesta) { 
        super(propuesta); 
    }

    @Override protected String nombre() { 
        return "Vajilla premium"; 
    }

    @Override protected Double aplicarAlPrecio(double precioAcumulado) { 
        return precioAcumulado + 12000.0; 
    }

}
