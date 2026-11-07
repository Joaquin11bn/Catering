package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.adicionales.MozosAdicional;
import ar.edu.unahur.obj2.catering.adicionales.ServicioNocturno;
import ar.edu.unahur.obj2.catering.adicionales.VajillaPremium;
import ar.edu.unahur.obj2.catering.excepcions.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.excepcions.PrecioNegativoException;

public class EpataDosTest {
    private static final String DELTA = null;
    private final Catalogo catalogo = new Catalogo();

    @Test 
    public void dadoUnServicioNocturnoAplicadoAntesQueMozos_alCalcularPrecio_elOrdenAlteraElPrecioFinal() {
        ServicioNocturno servicioNocturno = new ServicioNocturno(catalogo.mesaDeEntradas);
        MozosAdicional mozosAdicionales = new MozosAdicional(catalogo.mesaDeEntradas);

        assertNotEquals(mozosAdicionales.precio() + servicioNocturno.precio(), 
                        new ServicioNocturno(mozosAdicionales).precio());
    }

    @Test
    void dadoMozosYLuegoNocturno_alCalcularPrecio_elRecargoIncluyeLosMozos() {
        Propuesta p = new ServicioNocturno(new MozosAdicional(catalogo.menuParrillero));
        assertEquals(432000.0, p.precio());
        assertEquals(catalogo.menuParrillero.nombre() + " + Mozos Adicionales + Servicio Nocturno", p.nombre());
    }

    @Test void dadoMozosAdicionales_alCalcularPrecio_suma18000() {
        assertEquals(98_000, new MozosAdicional(catalogo.mesaDeEntradas).precio());
    }

    @Test
    void dadoVajillaPremium_alCalcularPrecio_suma12000() {
        Propuesta p = new VajillaPremium(catalogo.mesaDeEntradas);
        assertEquals(92000.0, p.precio());
        assertEquals(catalogo.mesaDeEntradas.nombre() + " + Vajilla Premium", p.nombre());
    }

    @Test
    void dadoServicioNocturno_alCalcularPrecio_suma20PorCientoDelAcumulado() {
        Propuesta p = new ServicioNocturno(catalogo.mesaDulce);
        assertEquals(72000.0, p.precio());
        assertEquals(catalogo.mesaDulce.nombre() + " + Servicio Nocturno", p.nombre());
    }

    @Test 
    void dadoUnAdicionalRepetido_alCalcularPrecio_seAplicaCadaVez() {
        Propuesta vajilla = new VajillaPremium(new VajillaPremium(catalogo.brindisDeBienvenida));
        assertEquals(74_000, vajilla.precio());
        assertEquals("Brindis de Bienvenida + Vajilla Premium + Vajilla Premium", vajilla.nombre());
        assertEquals(86_400, new ServicioNocturno(new ServicioNocturno(catalogo.mesaDulce)).precio());
    }

    @Test 
    public void dadoUnAdicionalSobreEventoCompleto_alCalcularPrecio_sumaSobreElMenu() {
        Propuesta p = new VajillaPremium(catalogo.eventoCompleto);
        assertEquals(518_800 + 12_000, p.precio());
        assertEquals(catalogo.eventoCompleto.nombre() + " + Vajilla Premium", p.nombre());
    } 

    @Test 
    public void dadoUnAdicionalSobreUnMenu_alContarServiciosEConsultarInclusion_semantieneLoDelMenu() {
        Propuesta p = new VajillaPremium(catalogo.menuParrillero);
        assertEquals(3, p.cantidadDeServicios());
        assertTrue(p.incluyePropuesta("Mesa de Entradas"));
        assertFalse(p.incluyePropuesta("DJ Y SONIDO"));
    }

    @Test
    void dadoUnAdicionalContratado_alConsultarLaPropuestaOriginal_noCambiaPrecioNiNombre() {
        new MozosAdicional(catalogo.menuParrillero);
        assertEquals(342000.0, catalogo.menuParrillero.precio());
        assertEquals("Menu Parrillero", catalogo.menuParrillero.nombre());
    }

    @Test
    void dadoUnContratoCreado_alActualizarElPrecioDeUnServicio_elImporteContratadoNoCambia() {
    Contrato contrato = new Contrato("Lucía", catalogo.menuParrillero);

    catalogo.mesaDeEntradas.setPrecio(100_000.0);

    assertEquals(360_000.0, catalogo.menuParrillero.precio().doubleValue(), 0.01); // la carta sí cambió
    assertEquals(342_000.0, contrato.getPrecioBase().doubleValue(), 0.01);         // el contrato no
}

    @Test
    void dadoUnContratoDeLuciaConMozos_alConsultarSuImporte_devuelve360000() {
        Contrato contrato = new Contrato("Lucía", new MozosAdicional(catalogo.menuParrillero));
        assertEquals(360000.0, contrato.getPrecioBase().doubleValue(), DELTA);
    }

}
