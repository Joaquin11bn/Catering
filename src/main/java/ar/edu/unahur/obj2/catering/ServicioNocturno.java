package ar.edu.unahur.obj2.catering;

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
