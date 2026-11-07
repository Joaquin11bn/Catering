package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.excepcions.PropuestaNoIncluidaException;

/**
 * Modificador
 */
public interface Modificador {
    void modificar() throws PropuestaNoIncluidaException;
    void deshacer() throws PropuestaNoIncluidaException;
}
