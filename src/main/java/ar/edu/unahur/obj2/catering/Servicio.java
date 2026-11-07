package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.excepcions.PrecioNegativoException;

public class Servicio implements Propuesta {
    private String nombre;
    private Double precio;
    

    private void validarPrecio(Double precio) {
        if (precio < 0) {
            throw new PrecioNegativoException(precio);
        }
    }

    public Servicio(String nombre, Double precio) {
        validarPrecio(precio);
        this.nombre = nombre;
        this.precio = precio;
       
    }

    public void setPrecio(Double precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public Double precio() {
        return precio;
    }

    @Override
    public Integer cantidadDeServicios() {
        return 1;
    }

    @Override
    public Boolean incluyePropuesta(String nombre) {
        return this.nombre.equals(nombre);
    }

}
