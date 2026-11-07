
package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.adicionales.ActualizarPrecio;
import ar.edu.unahur.obj2.catering.adicionales.AgregarPropuesta;
import ar.edu.unahur.obj2.catering.adicionales.MozosAdicional;
import ar.edu.unahur.obj2.catering.adicionales.ServicioNocturno;
import ar.edu.unahur.obj2.catering.adicionales.VajillaPremium;
import ar.edu.unahur.obj2.catering.excepcions.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.excepcions.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.excepcions.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.excepcions.PropuestaNoIncluidaException;

public class EtapaTresTest {
private static final double DELTA = 0.01;
    private final Catalogo catalogo = new Catalogo();
    private final Gestor gestor = new Gestor();
    private final Servicio fotografo = new Servicio("Fotógrafo", 100000.0);

    @Test
    public void dadaUnaActualizacionDePrecio_alRevertirla_devuelveElPrecioAnterior() {
        Servicio servicio = new Servicio("Servicio de prueba", 100.0);
        ActualizarPrecio actualizacion = new ActualizarPrecio(servicio, 200.0);

        actualizacion.modificar();
        assertEquals(200.0, servicio.precio(), DELTA);

        actualizacion.deshacer();
        assertEquals(100.0, servicio.precio(), DELTA);
    }
    @Test
    public void dadoUnLoteConUnaModificacionInvalida_alAplicarlo_dejaElCatalogoComoEstaba() throws Exception {
    Servicio servicio = new Servicio("Servicio de prueba", 100.0);
    List<Modificador> lote = List.of(
            new ActualizarPrecio(servicio, 200.0),
            new ActualizarPrecio(servicio, -50.0));

    assertThrows(PrecioNegativoException.class, () -> gestor.aplicarLote(lote));

    assertEquals(100.0, servicio.precio().doubleValue(), DELTA);     
    assertThrows(NadaParaRevertirException.class, gestor::deshacer);   
    }
    @Test
    void dadoUnLoteValido_alAplicarloYRevertir_cadaModificacionQuedaEnElHistorialEnOrden() throws Exception {
        List<Modificador> lote = List.of(
                new AgregarPropuesta(catalogo.menuParrillero, fotografo),
                new ActualizarPrecio(catalogo.parrilladaCompleta, 260000.0),
                new QuitarPropuesta(catalogo.menuParrillero, catalogo.mesaDulce));
        gestor.aplicarLote(lote);
        assertEquals(396000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);                                        // a
        gestor.deshacer();
        assertEquals(450000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);                                        // b
        gestor.deshacer();
        gestor.deshacer();
        assertEquals(342000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);                                        // c
        assertThrows(NadaParaRevertirException.class, gestor::deshacer);                        // d
    }
    
    @Test
    void dadoUnQuitado_alAplicarloYRevertirlo_vuelveAAgregarLoQuitado() throws Exception {
        gestor.modificar(new QuitarPropuesta(catalogo.menuParrillero, catalogo.mesaDulce));
        assertFalse(catalogo.menuParrillero.incluyePropuesta(catalogo.mesaDulce.nombre()));
        gestor.deshacer();
        assertTrue(catalogo.menuParrillero.incluyePropuesta(catalogo.mesaDulce.nombre()));
        assertEquals(342000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);
    }  
    @Test
    void dadoUnLoteConUnaModificacionInvalida_alAplicarlo_dejaElCatalogoComoEstabaYNoRegistraElLote() throws Exception {
        gestor.modificar(new AgregarPropuesta(catalogo.menuParrillero, fotografo));
        List<Modificador> lote = List.of(
                new ActualizarPrecio(catalogo.parrilladaCompleta, 260000.0),
                new QuitarPropuesta(catalogo.menuParrillero, catalogo.barraDeTragos));

        assertThrows(PropuestaNoIncluidaException.class, () -> gestor.aplicarLote(lote));       // a
        assertEquals(432000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);                                        // b
        assertEquals(240000.0, catalogo.parrilladaCompleta.precio().doubleValue(), DELTA);                                    // c
        gestor.deshacer();
        assertEquals(342000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);                                        // d
        assertThrows(NadaParaRevertirException.class, gestor::deshacer);                        // e
    }   
    @Test
    void dadoUnMenu_alQuitarUnaPropuestaNoIncluida_lanzaExcepcionYNoAlteraElMenuNiElHistorial() {
        assertThrows(PropuestaNoIncluidaException.class,
                () -> gestor.modificar(new QuitarPropuesta(catalogo.menuParrillero, catalogo.barraDeTragos)));
        assertEquals(342000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);
        assertEquals(3, catalogo.menuParrillero.cantidadDeServicios().intValue());
        assertThrows(NadaParaRevertirException.class, gestor::deshacer);
    } 
    @Test
    void dadoUnContratoCreado_alActualizarElPrecioDeUnServicio_elImporteContratadoNoCambia() throws Exception {
        Contrato contrato = new Contrato("Lucía", catalogo.menuParrillero);
        gestor.modificar(new ActualizarPrecio(catalogo.parrilladaCompleta, 260000.0));
        assertEquals(360000.0, catalogo.menuParrillero.precio().doubleValue(), DELTA);
        assertEquals(342000.0, contrato.getPrecioBase().doubleValue(), DELTA);
    }       
}
