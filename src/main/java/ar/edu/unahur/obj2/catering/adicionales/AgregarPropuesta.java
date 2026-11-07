package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.Menu;
import ar.edu.unahur.obj2.catering.Modificador;
import ar.edu.unahur.obj2.catering.Propuesta;
import ar.edu.unahur.obj2.catering.excepcions.PropuestaNoIncluidaException;

public class AgregarPropuesta implements Modificador{
    private final Menu menu;
    private final Propuesta propuesta;

    public AgregarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override 
    public void modificar() throws PropuestaNoIncluidaException {
        /*if (menu.incluyePropuesta(propuesta.nombre())) {
            throw new PropuestaNoIncluidaException("La propuesta ya está incluida en el menú.");
        }*/
        menu.agregarPropuesta(propuesta);
    }
    @Override 
    public void deshacer() throws PropuestaNoIncluidaException {
        if (!menu.incluyePropuesta(propuesta.nombre())) {
            throw new PropuestaNoIncluidaException("La propuesta no está incluida en el menú.");
        }
        menu.quitarPropuesta(propuesta);
    }
}
