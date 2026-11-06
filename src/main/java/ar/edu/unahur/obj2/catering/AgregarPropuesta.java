package ar.edu.unahur.obj2.catering;

public class AgregarPropuesta implements Modificador{
    private final Menu menu;
    private final Propuesta propuesta;

    public AgregarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override 
    public void modificar() throws PropuestaNoIncluidaException {
        if (menu.incluyePropuesta(propuesta.nombre())) {
            throw new PropuestaNoIncluidaException("La propuesta ya está incluida en el menú.");
        }
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
