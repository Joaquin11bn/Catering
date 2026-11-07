package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.Propuesta;

public class VajillaPremium extends Adicional {
    public VajillaPremium(Propuesta propuesta) {
        super(propuesta, "Vajilla Premium");
    }

    @Override
    public Double precio() {
        return propuesta.precio() + 12000.0;
    }

    @Override
    public String nombre() {
        return "Vajilla Premium";
    }

}
