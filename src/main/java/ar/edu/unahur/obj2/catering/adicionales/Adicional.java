package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.Propuesta;

public abstract class Adicional implements Propuesta {
    protected final Propuesta propuesta;
    private final String nombreAdicional;

    protected Adicional(Propuesta propuesta, String nombreAdicional) {
        this.propuesta = propuesta;
        this.nombreAdicional = nombreAdicional;
    }

    @Override
    public String nombre() {
        return propuesta.nombre() + " + " + nombreAdicional;
    }

    @Override
    public Integer cantidadDeServicios() {
        return propuesta.cantidadDeServicios();
    }
    @Override 
    public Boolean incluyePropuesta(String nombre) {
        return propuesta.incluyePropuesta(nombre);
    }
}
