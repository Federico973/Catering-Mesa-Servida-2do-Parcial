package ar.edu.unahur.obj2.catering.AdicionalesYContratos;

import ar.edu.unahur.obj2.catering.ServicioYMenu.Propuesta;

public class MozoAdicional extends Adicional{

    public MozoAdicional(Propuesta propuesta) { 
        super(propuesta); 
    }

    @Override protected String nombre() { 
        return "Mozos adicionales"; 
    }

    @Override protected Double aplicarAlPrecio(double precioAcumulado) { 
        return precioAcumulado + 18000.0; 
    }

}
