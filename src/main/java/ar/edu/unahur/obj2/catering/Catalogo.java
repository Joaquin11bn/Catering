package ar.edu.unahur.obj2.catering;

public class Catalogo {
    public final Servicio mesaDeEntradas = new Servicio("Mesa de Entradas", 80000.0);
    public final Servicio parrilladaCompleta = new Servicio("Parrillada Completa", 240000.0);
    public final Servicio mesaDulce = new Servicio("Mesa Dulce", 60000.0);
    public final Servicio barraDeTragos = new Servicio("Barra de Tragos", 150000.0);
    public final Servicio dJySonido = new Servicio("DJ", 120000.0);
    public final Servicio brindisDeBienvenida = new Servicio("Brindis de Bienvenida", 50000.0);
    
    public final Menu packFiesta = new Menu("Pack Fiesta",barraDeTragos.precio() + dJySonido.precio(), 5);
    public final Menu menuParrillero = new Menu("Menu Parrillero", mesaDeEntradas.precio() + parrilladaCompleta.precio() + mesaDulce.precio(), 10);
    public final Menu eventoCompleto = new Menu("Evento Completo", menuParrillero.precio() + brindisDeBienvenida.precio() + packFiesta.precio(), 20);
}
