package ar.edu.unahur.obj2.catering.GestionDeCarta;

import ar.edu.unahur.obj2.catering.ServicioYMenu.Servicio;

public class ActualizarPrecio implements Modificacion{

    private final Servicio servicio;
    private final Double nuevoPrecio;
    private Double precioAnterior;

    public ActualizarPrecio(Servicio servicio, Double nuevoPrecio) {
        this.servicio = servicio;
        this.nuevoPrecio = nuevoPrecio;
    }

    @Override
    public void aplicar() {
        Double anterior = servicio.precio();
        servicio.actualizarPrecio(nuevoPrecio);
        precioAnterior = anterior;
    }

    @Override
    public void revertir() {
        servicio.actualizarPrecio(precioAnterior);
    }
}
