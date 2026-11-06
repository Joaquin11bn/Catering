package ar.edu.unahur.obj2.catering;

public interface Propuesta {
    String nombre();
    Double precio();
    Integer cantidadDeServicios();
    Boolean incluyePropuesta(String nombre) 
    ;
}
