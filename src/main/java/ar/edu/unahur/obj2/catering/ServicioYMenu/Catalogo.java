package ar.edu.unahur.obj2.catering.ServicioYMenu;


public class Catalogo {
    final Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
    final Servicio parrillada = new Servicio("Parrillada completa", 240000.0);
    final Servicio mesaDulce = new Servicio("Mesa dulce", 60000.0);
    final Servicio barraDeTragos = new Servicio("Barra de tragos", 150000.0);
    public final Servicio dj = new Servicio("DJ y sonido", 120000.0);
    final Servicio brindis = new Servicio("Brindis de bienvenida", 50000.0);

    public final Menu menuParrillero = new Menu("Menú Parrillero", 10.0);
    public final Menu packFiesta = new Menu("Pack Fiesta", 5.0);
    public final Menu eventoCompleto = new Menu("Evento Completo", 20.0);

    public Catalogo() {
        menuParrillero.agregar(mesaDeEntradas);
        menuParrillero.agregar(parrillada);
        menuParrillero.agregar(mesaDulce);
        packFiesta.agregar(barraDeTragos);
        packFiesta.agregar(dj);
        eventoCompleto.agregar(menuParrillero);
        eventoCompleto.agregar(packFiesta);
        eventoCompleto.agregar(brindis);
    }
}
