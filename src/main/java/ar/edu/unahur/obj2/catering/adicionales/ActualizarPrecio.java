package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.Modificador;
import ar.edu.unahur.obj2.catering.Servicio;

public class ActualizarPrecio implements Modificador {
    private final Servicio servicio;
    private final Double nuevoPrecio;
    private Double precioAnterior;

    public ActualizarPrecio(Servicio servicio, Double nuevoPrecio) {
        this.servicio = servicio;
        this.nuevoPrecio = nuevoPrecio;
    }

    @Override
    public void modificar() {
        Double anterior = servicio.precio();
        servicio.setPrecio(nuevoPrecio);
        precioAnterior = anterior;
    }

    @Override
    public void deshacer() {
        servicio.setPrecio(precioAnterior);
    }

}
