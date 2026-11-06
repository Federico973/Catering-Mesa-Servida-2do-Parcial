package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.AdicionalesYContratos.Contrato;
import ar.edu.unahur.obj2.catering.AdicionalesYContratos.MozoAdicional;
import ar.edu.unahur.obj2.catering.AdicionalesYContratos.Servicionocturno;
import ar.edu.unahur.obj2.catering.AdicionalesYContratos.VajillaPremium;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Catalogo;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Propuesta;

public class AdicionalYContratoTest {

    private final Catalogo catalogo1 = new Catalogo();

    @Test
    void dadoUnServicioConMozos_alCalcularPrecio_suma18000() {
        Propuesta propuesta1 = new MozoAdicional(catalogo1.mesaDulce);
        assertEquals(78000.0, propuesta1.precio());
        assertEquals("Mesa dulce + Mozos adicionales", propuesta1.descripcion());
    }

    @Test
    void dadoUnServicioConVajilla_alCalcularPrecio_suma12000() {
        Propuesta propuesta2 = new VajillaPremium(catalogo1.mesaDulce);
        assertEquals(72000.0, propuesta2.precio());
        assertEquals("Mesa dulce + Vajilla premium", propuesta2.descripcion());
    }

    @Test
    void dadoUnServicioConServicioNocturno_alCalcularPrecio_suma20PorCiento() {
        Propuesta propuesta3 = new Servicionocturno(catalogo1.mesaDulce);
        assertEquals(72000.0, propuesta3.precio());
        assertEquals("Mesa dulce + Servicio nocturno", propuesta3.descripcion());
    }

    @Test
    void dadoUnServicioNocturnoAplicadoDespuesDeMozos_alCalcularPrecio_incluyeLosMozosEnElRecargo() {
        Propuesta propuesta4 = new Servicionocturno(new MozoAdicional(catalogo1.menuParrillero));
        assertEquals(432000.0, propuesta4.precio());
        assertEquals("Menú Parrillero + Mozos adicionales + Servicio nocturno", propuesta4.descripcion());
    }

    @Test
    void dadoUnServicioNocturnoAplicadoAntesQueMozos_alCalcularPrecio_elOrdenAlteraElPrecioFinal() {
        Propuesta propuesta5 = new MozoAdicional(new Servicionocturno(catalogo1.menuParrillero));
        assertEquals(428400.0, propuesta5.precio());
        assertEquals("Menú Parrillero + Servicio nocturno + Mozos adicionales", propuesta5.descripcion());
    }

    @Test
    void dadoUnAdicionalRepetido_alCalcularPrecio_seAplicaCadaVez() {
        Propuesta vajilla = new VajillaPremium(new VajillaPremium(catalogo1.brindis));
        assertEquals(74000.0, vajilla.precio());
        assertEquals("Brindis de bienvenida + Vajilla premium + Vajilla premium", vajilla.descripcion());

        Propuesta noche = new Servicionocturno(new Servicionocturno(catalogo1.mesaDulce));
        assertEquals(86400.0, noche.precio());
        assertEquals("Mesa dulce + Servicio nocturno + Servicio nocturno", noche.descripcion());
    }

    @Test
    void dadoUnAdicionalSobreUnMenu_alCalcularPrecio_sumaSobreElPrecioDelMenu() {
        Propuesta p = new VajillaPremium(catalogo1.eventoCompleto);
        assertEquals(530800.0, p.precio());
        assertEquals("Evento Completo + Vajilla premium", p.descripcion());
    }

    @Test
    void dadoUnAdicional_alConsultarServiciosEInclusion_seMantienenLosDeLaPropuesta() {
        Propuesta p = new Servicionocturno(new MozoAdicional(catalogo1.eventoCompleto));
        assertEquals(6, p.cantidadDeServicios());
        assertTrue(p.incluye("DJ y sonido"));
        assertFalse(p.incluye("Fotógrafo"));
    }

    @Test
    void dadoUnAdicionalContratado_alConsultarLaPropuestaOriginal_noCambia() {
        new Servicionocturno(new MozoAdicional(catalogo1.menuParrillero));
        assertEquals(342000.0, catalogo1.menuParrillero.precio());
        assertEquals("Menú Parrillero", catalogo1.menuParrillero.descripcion());
    }

    @Test
    void dadoUnContratoConAdicionales_alConsultarlo_devuelveImporteYDescripcionFijados() {
        Contrato contrato = new Contrato("Lucía", new MozoAdicional(catalogo1.menuParrillero));
        assertEquals("Lucía", contrato.cliente());
        assertEquals(360000.0, contrato.importe());
        assertEquals("Menú Parrillero + Mozos adicionales", contrato.descripcion());
    }

    @Test
    void dadoUnContratoCreado_alActualizarElPrecioDeUnServicio_elImporteContratadoNoCambia() {
        Contrato contrato = new Contrato("Lucía", catalogo1.menuParrillero);
        catalogo1.parrillada.actualizarPrecio(260000.0);
        assertEquals(360000.0, catalogo1.menuParrillero.precio());
        assertEquals(342000.0, contrato.importe());
    }
}
