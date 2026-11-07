package ar.edu.unahur.obj2.catering;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import ar.edu.unahur.obj2.catering.excepcions.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.excepcions.PropuestaNoIncluidaException;


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
    } catch (PropuestaNoIncluidaException | RuntimeException errorFatal) {
        while (!aplicadas.isEmpty()) aplicadas.pop().deshacer();
        throw errorFatal;
    }
    lote.forEach(historial::push);
    }

    public void deshacer() throws NadaParaRevertirException, PropuestaNoIncluidaException {
        if (historial.isEmpty()) {
            throw new NadaParaRevertirException();
        }
        historial.pop().deshacer();
    }
}
