package ar.edu.unahur.obj2.catering.excepcions;

public class PropuestaNoIncluidaException extends Exception {
    public PropuestaNoIncluidaException(String nombre) {
        super("La propuesta " + nombre + " no está incluida en el menú.");
    }

}
