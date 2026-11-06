package ar.edu.unahur.obj2.catering;

public class MozosAdicional extends Adicional{
    public MozosAdicional(Propuesta propuesta) {
        super(propuesta, "Mozos Adicionales");
    }

    @Override
    public Double precio() {    
        return propuesta.precio() + 18000.0;
    }

    @Override
    public String nombre() {
        return "Mozos Adicionales";
    }

}
