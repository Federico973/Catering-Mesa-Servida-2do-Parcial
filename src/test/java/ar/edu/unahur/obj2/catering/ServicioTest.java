package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Catalogo;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Menu;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Servicio;

public class ServicioTest {
    private final Catalogo catalogo1 = new Catalogo();

    @Test
    void dadoUnServicioConPrecioNegativo_alCrearlo_lanzaPrecioNegativoException() {
        assertThrows(PrecioNegativoException.class, () -> new Servicio("Servicio1", -2.0));
    }

    @Test
    void dadoUnMenuConDescuentoMayorACien_alCrearlo_lanzaDescuentoInvalidoException() {
        assertThrows(DescuentoInvalidoException.class, () -> new Menu("Menu1", 101.0));
    }

    @Test
    void dadoUnMenuConDescuentoNegativo_alCrearlo_lanzaDescuentoInvalidoException() {
        assertThrows(DescuentoInvalidoException.class, () -> new Menu("Menu2", -1.0));
    }

    @Test
    void dadoUnMenuConServicios_alCalcularPrecio_aplicaElDescuento() {
        assertEquals(342000.0, catalogo1.menuParrillero.precio());
        assertEquals(256500.0, catalogo1.packFiesta.precio());
    }

    @Test
    void dadoUnServicioSuelto_alConsultarSusDatos_cuentaUnoYSeDescribePorNombre() {
        assertEquals(1, catalogo1.dj.cantidadDeServicios());
        assertEquals("DJ y sonido", catalogo1.dj.descripcion());
        assertEquals(120000.0, catalogo1.dj.precio());
    }

    @Test
    void dadoUnMenu_alContarServicios_devuelveLaCantidad() {
        assertEquals(3, catalogo1.menuParrillero.cantidadDeServicios());
        assertEquals("Menú Parrillero", catalogo1.menuParrillero.descripcion());
    }

    @Test
    void dadoUnMenu_alConsultarInclusion_respondeSegunElNombre() {
        assertTrue(catalogo1.menuParrillero.incluye("Parrillada completa"));
    }

    @Test
    void dadoUnMenuQueContieneOtrosMenus_alCalcularPrecio_usaElPrecioYaDescontadoDeLosInternos() {
        assertEquals(518800.0, catalogo1.eventoCompleto.precio());
    }

    @Test
    void dadoUnMenuQueContieneOtrosMenus_alContarServicios_sumaLosDeTodosLosNiveles() {
        assertEquals(6, catalogo1.eventoCompleto.cantidadDeServicios());
    }

    @Test
    void dadoUnMenuQueContieneOtrosMenus_alConsultarInclusion_buscaEnTodosLosNiveles() {
        assertTrue(catalogo1.eventoCompleto.incluye("DJ y sonido"));
        assertFalse(catalogo1.eventoCompleto.incluye("Fotógrafo"));
    }

}
