package ar.edu.unahur.obj2.catering.GestionDeCarta;

import ar.edu.unahur.obj2.catering.ServicioYMenu.Menu;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Propuesta;

public class AgregarPropuesta implements Modificacion{

    private final Menu menu;
    private final Propuesta propuesta;

    public AgregarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override public void aplicar() { 
        menu.agregar(propuesta); 
    }

    @Override public void revertir() { 
        menu.quitar(propuesta); 
    }
}
