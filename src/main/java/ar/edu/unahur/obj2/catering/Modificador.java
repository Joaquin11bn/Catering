package ar.edu.unahur.obj2.catering;

/**
 * Modificador
 */
public interface Modificador {
    void modificar() throws PropuestaNoIncluidaException;
    void deshacer() throws PropuestaNoIncluidaException;
}
