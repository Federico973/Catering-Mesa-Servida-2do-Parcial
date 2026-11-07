package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.AdicionalesYContratos.Contrato;
import ar.edu.unahur.obj2.catering.Excepciones.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.GestionDeCarta.ActualizarPrecio;
import ar.edu.unahur.obj2.catering.GestionDeCarta.AgregarPropuesta;
import ar.edu.unahur.obj2.catering.GestionDeCarta.GestionDeCarta;
import ar.edu.unahur.obj2.catering.GestionDeCarta.Modificacion;
import ar.edu.unahur.obj2.catering.GestionDeCarta.QuitarPropuesta;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Catalogo;
import ar.edu.unahur.obj2.catering.ServicioYMenu.Servicio;

public class GestionCartaTest {

    private final Catalogo catalogo = new Catalogo();
    private final Servicio fotografo = new Servicio("Fotógrafo", 100000.0);
    private final GestionDeCarta gestor = new GestionDeCarta();

    private Modificacion agregarFotografo() { 
        return (Modificacion) new AgregarPropuesta(catalogo.menuParrillero, fotografo); 
    }
    private Modificacion actualizarParrillada(Double p) { return new ActualizarPrecio(catalogo.parrillada, p); }
    private Modificacion quitarMesaDulce() { return new QuitarPropuesta(catalogo.menuParrillero, catalogo.mesaDulce); }

    @Test
    void dadaUnaPropuestaAgregada_alRevertirla_laQuitaDelMenu() {
        gestor.aplicar(agregarFotografo());
        assertTrue(catalogo.menuParrillero.incluye("Fotógrafo"));
        gestor.revertirUltima();
        assertFalse(catalogo.menuParrillero.incluye("Fotógrafo"));
    }

    @Test
    void dadaUnaPropuestaQuitada_alRevertirla_vuelveAAgregarla() {
        gestor.aplicar(quitarMesaDulce());
        assertFalse(catalogo.menuParrillero.incluye("Mesa dulce"));
        gestor.revertirUltima();
        assertTrue(catalogo.menuParrillero.incluye("Mesa dulce"));
    }

    @Test
    void dadaUnaActualizacionDePrecio_alRevertirla_devuelveElPrecioAnterior() {
        gestor.aplicar(actualizarParrillada(260000.0));
        assertEquals(260000.0, catalogo.parrillada.precio());
        gestor.revertirUltima();
        assertEquals(240000.0, catalogo.parrillada.precio());
    }

    @Test
    void dadaUnaSecuenciaInmediata_alRevertirTodo_vuelveAlCatalogoInicial() {
        gestor.aplicar(agregarFotografo());
        assertEquals(432000.0, catalogo.menuParrillero.precio());
        gestor.aplicar(actualizarParrillada(260000.0));
        assertEquals(450000.0, catalogo.menuParrillero.precio());
        gestor.aplicar(quitarMesaDulce());
        assertEquals(396000.0, catalogo.menuParrillero.precio());
        gestor.revertirUltima();
        assertEquals(450000.0, catalogo.menuParrillero.precio());
        gestor.revertirUltima();
        assertEquals(432000.0, catalogo.menuParrillero.precio());
        gestor.revertirUltima();
        assertEquals(342000.0, catalogo.menuParrillero.precio());
        assertThrows(NadaParaRevertirException.class, gestor::revertirUltima);
    }

    @Test
    void dadasDosActualizacionesDelMismoServicio_alRevertirlas_recuperaCadaPrecioAnterior() {
        Modificacion primera = actualizarParrillada(260000.0);
        Modificacion segunda = actualizarParrillada(300000.0);
        gestor.aplicar(primera);
        gestor.aplicar(segunda);
        assertEquals(300000.0, catalogo.parrillada.precio());
        gestor.revertirUltima();
        assertEquals(260000.0, catalogo.parrillada.precio());
        gestor.revertirUltima();
        assertEquals(240000.0, catalogo.parrillada.precio());
    }

    @Test
    void dadoUnLote_alAplicarlo_cadaModificacionQuedaEnElHistorialEnOrden() {
        gestor.aplicarLote(List.of(agregarFotografo(), actualizarParrillada(260_000.0), quitarMesaDulce()));
        assertEquals(396000.0, catalogo.menuParrillero.precio());
        gestor.revertirUltima();
        assertEquals(450000.0, catalogo.menuParrillero.precio());
        gestor.revertirUltima();
        gestor.revertirUltima();
        assertEquals(342000.0, catalogo.menuParrillero.precio());
        assertThrows(NadaParaRevertirException.class, gestor::revertirUltima);
    }


    @Test
    void dadoUnaActualizacionConPrecioNegativo_alAplicarla_lanzaExcepcionYNoQuedaEnElHistorial() {
        assertThrows(PrecioNegativoException.class, () -> gestor.aplicar(actualizarParrillada(-5.0)));
        assertEquals(240000.0, catalogo.parrillada.precio());
        assertThrows(NadaParaRevertirException.class, gestor::revertirUltima);
    }


    @Test
    void dadoUnContratoCreado_alCambiarLaCarta_elMenuCambiaPeroElImporteNo() {
        Contrato contrato = new Contrato("Lucía", catalogo.menuParrillero);
        gestor.aplicar(actualizarParrillada(260000.0));
        assertEquals(360000.0, catalogo.menuParrillero.precio());
        assertEquals(342000.0, contrato.importe());
    }

}
