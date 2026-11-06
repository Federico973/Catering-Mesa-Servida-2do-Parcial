package ar.edu.unahur.obj2.catering.AdicionalesYContratos;

import ar.edu.unahur.obj2.catering.ServicioYMenu.Propuesta;

public abstract class Adicional implements Propuesta{

    private final Propuesta propuesta;

    public Adicional(Propuesta propuesta) {
        this.propuesta = propuesta;
    }
    
    protected abstract String nombre();
    protected abstract Double aplicarAlPrecio(double precioAcumulado);

    @Override
    public Double precio() {
        return aplicarAlPrecio(propuesta.precio());
    }

    @Override
    public String descripcion() {
        return propuesta.descripcion() + " + " + nombre();
    }

    @Override
    public Integer cantidadDeServicios() {
        return propuesta.cantidadDeServicios();
    }

    @Override
    public boolean incluye(String nombreServicio) {
        return propuesta.incluye(nombreServicio);
    }
}
