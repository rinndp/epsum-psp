package epsum.curso.psp.ejemplo4;

class Contador {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
        System.out.println("Contador: " + contador);
    }
}
public class SyncHiloMain {
    public static void main(String[] args) {
        Contador contador1 = new Contador();

        Thread hilo1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                contador1.incrementar();
            }
        });

        Thread hilo2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                contador1.incrementar();
            }
        });

        hilo1.start();
        hilo2.start();

    }
}
