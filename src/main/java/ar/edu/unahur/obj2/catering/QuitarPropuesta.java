package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.excepcions.PropuestaNoIncluidaException;

public class QuitarPropuesta implements Modificador {
    private final Menu menu;
    private final Propuesta propuesta;

    public QuitarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override
    public void modificar() throws PropuestaNoIncluidaException {
        menu.quitarPropuesta(propuesta);
    }

    @Override
    public void deshacer() {
        menu.agregarPropuesta(propuesta);
    }
}