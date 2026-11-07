package ar.edu.unahur.obj2.catering;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.catering.excepcions.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.excepcions.PropuestaNoIncluidaException;

public class Menu implements Propuesta {
    private final String nombre;
    private final Integer descuento;
    private final List<Propuesta> contienePropuestas = new ArrayList<>();


    public Menu(String nombre, Integer descuento, Propuesta... contienePropuestas) {
        if (descuento < 0 || descuento > 100) {
            throw new DescuentoInvalidoException((double) descuento);
        }
        this.nombre = nombre;
        this.descuento = descuento;
        this.contienePropuestas.addAll(List.of(contienePropuestas));
    }


    public void agregarPropuesta(Propuesta propuesta) {
        contienePropuestas.add(propuesta);
    }

    public void quitarPropuesta(Propuesta propuesta) throws PropuestaNoIncluidaException {
        if(!contienePropuestas.remove(propuesta)){
            throw new PropuestaNoIncluidaException(propuesta.nombre());
        }
        contienePropuestas.remove(propuesta);
    }
    @Override 
    public Double precio() {
        Double precioTotal = contienePropuestas.stream().mapToDouble(Propuesta::precio).sum();
        return precioTotal - (precioTotal * descuento / 100);
    }

    @Override
    public String nombre() {
        return nombre;
    }



    @Override
    public Integer cantidadDeServicios() {
        return contienePropuestas.stream().mapToInt(Propuesta::cantidadDeServicios).sum();
    }


    @Override
    public Boolean incluyePropuesta(String nombre) {
        return contienePropuestas.stream().anyMatch(propuesta -> propuesta.incluyePropuesta(nombre));
    }
}

