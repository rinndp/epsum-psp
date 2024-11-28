package epsum.curso.psp.repaso.ejercicio1;

class Recursos {
    private int numeroGenerado;
    private boolean haLlegadoElNumero = false;

    public synchronized void generarNumero () throws InterruptedException {
        while (haLlegadoElNumero) {
            wait();
        }

        System.out.println("Se está generando un número...");
        numeroGenerado = (int) (Math.random() * 1000);
        Thread.sleep(1500);
        System.out.println("Se ha generado un número");
        this.haLlegadoElNumero = true;
        notify();

    }

    public synchronized void imprimirNumero () throws InterruptedException {
        while (!haLlegadoElNumero) {
            wait();
        }
        Thread.sleep(1000);
        System.out.println("El número generado es: " + numeroGenerado);
        Thread.sleep(1000);
        this.haLlegadoElNumero = false;
        notify();
    }
}

public class NumeroAleatorioMain {
    public static void main(String[] args) {
        Recursos r = new Recursos();

        Thread productor = new Thread(() -> {
            while (true) {
                try {
                    r.generarNumero();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumidor = new Thread(() -> {
            while (true) {
                try {
                    r.imprimirNumero();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        productor.start();
        consumidor.start();
    }
}
