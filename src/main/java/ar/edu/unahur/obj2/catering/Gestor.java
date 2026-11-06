package ar.edu.unahur.obj2.catering;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


public class Gestor {
    private final Deque<Modificador> historial = new ArrayDeque<>();

    public void modificar(Modificador modificador) throws PropuestaNoIncluidaException {
        modificador.modificar();
        historial.push(modificador);
    }

    public void aplicarLote(List<Modificador> lote) throws PropuestaNoIncluidaException {
    Deque<Modificador> aplicadas = new ArrayDeque<>();
    try {
        for (Modificador carta : lote) {
            carta.modificar();
            aplicadas.push(carta);
        }
    } catch (PropuestaNoIncluidaException e) {
        while (!aplicadas.isEmpty()) aplicadas.pop().deshacer();
        throw new PropuestaNoIncluidaException("Error al aplicar el lote de modificaciones. Se deshicieron los cambios realizados.");
    }
    lote.forEach(historial::push);
    }
}
