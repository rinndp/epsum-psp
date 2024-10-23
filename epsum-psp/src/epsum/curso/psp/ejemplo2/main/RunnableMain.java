package epsum.curso.psp.ejemplo2.main;

import epsum.curso.psp.ejemplo2.clases.MiRunnable;

public class RunnableMain {
    public static void main(String[] args) {
        MiRunnable miRunnable = new MiRunnable();
        Thread hilo = new Thread(miRunnable);
        hilo.start();
    }
}
