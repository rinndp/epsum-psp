package epsum.curso.psp.ejemplo.main;

import epsum.curso.psp.ejemplo.clases.MiHilo;

public class HiloMain {
    public static void main(String[] args) {
        MiHilo miHilo = new MiHilo();
        miHilo.start();
    }
}