package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.Propuesta;

public class ServicioNocturno extends Adicional {
    public ServicioNocturno(Propuesta propuesta) {
        super(propuesta, "Servicio Nocturno");
    }

    @Override
    public Double precio() {
        return propuesta.precio() * 1.2;
    }

    @Override  
    public String nombre() {
        return "Servicio Nocturno";
    }

}
