package ar.edu.unahur.obj2.catering.excepcions;

public class PrecioNegativoException extends RuntimeException {
    public PrecioNegativoException(Double precio) {
        super("El precio no puede ser negativo: " + precio);
    }

}
