package ar.edu.unahur.obj2.catering;

public class DescuentoInvalidoException extends RuntimeException {
    public DescuentoInvalidoException(Double descuento) {
        super("El descuento no puede ser negativo, debe estar entre 0 y 100: " + descuento);
    }

}
