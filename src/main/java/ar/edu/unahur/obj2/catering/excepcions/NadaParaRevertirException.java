package ar.edu.unahur.obj2.catering.excepcions;

public class NadaParaRevertirException extends Exception {
    public NadaParaRevertirException() {
        super("No hay modificaciones para revertir.");
    }

}
