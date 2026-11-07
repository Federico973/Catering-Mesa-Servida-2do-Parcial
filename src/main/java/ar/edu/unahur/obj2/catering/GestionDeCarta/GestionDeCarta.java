package ar.edu.unahur.obj2.catering.GestionDeCarta;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.catering.Excepciones.NadaParaRevertirException;

public class GestionDeCarta {
        private final List<Modificacion> historial = new ArrayList<>();

    public void aplicar(Modificacion modificacion) {
        modificacion.aplicar();
        historial.add(modificacion);
    }

    public void aplicarLote(List<Modificacion> lote) {
        List<Modificacion> aplicadas = new ArrayList<>();
        try {
            for (Modificacion m : lote) {
                m.aplicar();
                aplicadas.add(m);
            }
        } catch (RuntimeException falla) {
            for (int i = aplicadas.size() - 1; i >= 0; i--) {
                aplicadas.get(i).revertir();
            }
            throw falla;
        }
        historial.addAll(aplicadas);
    }


    public void revertirUltima() {
        if (historial.isEmpty()) {
            throw new NadaParaRevertirException("No se puede revertir");
        }
        historial.remove(historial.size() - 1).revertir();
    }
}
