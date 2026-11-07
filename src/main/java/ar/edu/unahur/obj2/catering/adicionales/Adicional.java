package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.Propuesta;

public abstract class Adicional implements Propuesta {
    protected final Propuesta propuesta;
    protected Adicional(Propuesta propuesta, String nombre) {
        this.propuesta = propuesta;
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
