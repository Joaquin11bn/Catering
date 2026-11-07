package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.excepcions.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.excepcions.PrecioNegativoException;

public class EtapaUnoTest {
    private final Catalogo catalogo = new Catalogo();


    @Test 
    public void dadoUnServicioConPrecioNegativo_alCrearlo_lanzaPrecioNegativoException() {
        assertThrows(PrecioNegativoException.class, () -> new Servicio("Servicio con precio negativo", -100.0));
    }

    @Test
    public void dadoUnMenuConDescuentoMayorACien_alCrearlo_lanzaDescuentoInvalidoException() {
        assertThrows(DescuentoInvalidoException.class, () -> new Menu("Menu con descuento invalido", 150, catalogo.mesaDeEntradas));
    }
    
    @Test void dadoUnServicioSuelto_alContarServicios_devuelveUno() {
        assertEquals(1, catalogo.mesaDulce.cantidadDeServicios());
    }
    @Test void dadoUnMenu_alConsultarInclusion_respondePorNombre() {
        assertTrue(catalogo.menuParrillero.incluyePropuesta("Parrillada Completa"));
        assertFalse(catalogo.menuParrillero.incluyePropuesta("DJ Y SONIDO"));
    }    

    @Test 
    public void dadoUnMenuQueContieneOtrosMenus_alContarServicios_sumaLosDeTodosLosNiveles() {
        assertEquals(6, catalogo.eventoCompleto.cantidadDeServicios());
    }

    @Test void dadoUnMenuQueContieneOtrosMenus_alCalcularPrecio_usaElPrecioYaDescontadoDeLosInternos() {
        assertEquals(518_800, catalogo.eventoCompleto.precio());
    }   
    @Test void dadoUnMenuAnidado_alConsultarInclusion_buscaEnTodosLosNiveles() {
        assertTrue(catalogo.eventoCompleto.incluyePropuesta("DJ Y SONIDO"));
        assertFalse(catalogo.eventoCompleto.incluyePropuesta("Fotógrafo"));
    }    
}
