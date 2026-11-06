package ar.edu.unahur.obj2.catering;
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
        return cantidadDeServicios();
    }

    @Override
    public Boolean incluyePropuesta(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incluyePropuesta'");
    }

}
