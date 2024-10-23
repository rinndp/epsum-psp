package epsum.curso.psp.contador;

class Contador {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
        System.out.println("Contador: " + contador);
        if (contador == 5) {
            System.out.println("TERMINADO HILO 1");
        }
    }

    public synchronized void decrementar() {
        contador--;
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
                contador1.decrementar();
            }
        });

        hilo1.start();
        hilo2.start();

    }
}
