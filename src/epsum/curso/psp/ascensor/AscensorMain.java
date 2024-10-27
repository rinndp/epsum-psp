package epsum.curso.psp.ascensor;

import java.util.LinkedList;

class Ascensor {
    private int contadorPersonas = 0;
    private final int CAPACIDAD = 5;

    public Ascensor() {}

    public synchronized void añadirPersona () throws InterruptedException {
            while (true) {
                while (contadorPersonas == CAPACIDAD) {
                    System.out.println("ASCENSOR LLENO");
                    wait();
                }
                contadorPersonas++;
                System.out.println("Se ha subido UNA persona: " + contadorPersonas);
                notify();
                Thread.sleep(1000);

            }
    }

    public synchronized void bajarPersona () throws InterruptedException {
            while (true) {
                while (contadorPersonas == 0) {
                    System.out.println("ASCENSOR VACIO");
                    wait();
                }
                contadorPersonas--;
                System.out.println("Se ha bajado UNA persona: " + contadorPersonas);
                notify();
                Thread.sleep(2500);

            }

    }

}

public class AscensorMain {
    public static void main(String[] args) {
        Ascensor ascensor = new Ascensor();
        Thread hiloSubir = new Thread(() -> {
            try {
                ascensor.añadirPersona();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread hiloBajar = new Thread(() -> {
            try {
                ascensor.bajarPersona();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        hiloSubir.start();
        hiloBajar.start();

    }
}
