package ar.edu.unahur.obj2.catering;

public class Contrato {
    private final String cliente;
    private final Double precioBase;
    private final String Descripcion;

    public Contrato(String cliente, Propuesta propuesta) {
        this.cliente = cliente;
        this.precioBase = propuesta.precio();
        this.Descripcion = propuesta.nombre();
    }

    public String getCliente() {
        return cliente;
    }
    public Double getPrecioBase() {
        return precioBase;
    }
    public String getDescripcion() {
        return Descripcion;
    }
}
