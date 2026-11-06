package ar.edu.unahur.obj2.catering.ServicioYMenu;

import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;

public class Servicio implements Propuesta{

    private final String nombre;
    private Double precio;


    public Servicio(String nombre, Double precio) {
        validar(precio);
        this.nombre = nombre;
        this.precio = precio;

    }
    @Override
    public String descripcion() {
       return nombre;
    }

    @Override
    public Double precio() {
        return precio;
    }
    @Override
    public Integer cantidadDeServicios() {
        return 1;
    }
    @Override
    public boolean incluye(String unNombre) {
        return nombre.equals(unNombre);
    }

    private void validar(Double precio) {
        if (precio < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo");
        }
    }

    public void actualizarPrecio(Double nuevoPrecio) {
        validar(nuevoPrecio);
        this.precio = nuevoPrecio;
    }

}
