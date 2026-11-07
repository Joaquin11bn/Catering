package ar.edu.unahur.obj2.catering;

public class Catalogo {
    public final Servicio mesaDeEntradas = new Servicio("Mesa de Entradas", 80000.0);
    public final Servicio parrilladaCompleta = new Servicio("Parrillada Completa", 240000.0);
    public final Servicio mesaDulce = new Servicio("Mesa Dulce", 60000.0);
    public final Servicio barraDeTragos = new Servicio("Barra de Tragos", 150000.0);
    public final Servicio dJySonido = new Servicio("DJ Y SONIDO", 120000.0);
    public final Servicio brindisDeBienvenida = new Servicio("Brindis de Bienvenida", 50000.0);
    
    public final Menu packFiesta = new Menu("Pack Fiesta", 5, barraDeTragos, dJySonido);
    public final Menu menuParrillero = new Menu("Menu Parrillero", 10, mesaDeEntradas, parrilladaCompleta, mesaDulce);
    public final Menu eventoCompleto = new Menu("Evento Completo", 20, menuParrillero, brindisDeBienvenida, packFiesta);
}
