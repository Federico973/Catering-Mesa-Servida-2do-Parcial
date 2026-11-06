package ar.edu.unahur.obj2.catering.ServicioYMenu;

public interface Propuesta {

    String descripcion();
    Double precio();
    Integer cantidadDeServicios();
    boolean incluye(String nombre);

}
