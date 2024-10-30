package epsum.curso.psp.barrera;

class Camino {
    int contadorHilos = 0;
    boolean continuar = true;
    final int TOTAL_HILOS = 3;

    public Camino() {}

    public synchronized void esperarHilos() throws InterruptedException {
        contadorHilos++;
        if (contadorHilos < TOTAL_HILOS) {
            continuar = false;
            wait();
        } else {
            Thread.sleep(1000);
            System.out.println("Todos los HILOS han LLEGADO. COTNINUANDO...");
            Thread.sleep(1000);
            contadorHilos = 0;
            continuar = true;
            notifyAll();
        }
    }
}

class Hilo implements Runnable {
    Camino camino;
    int velocidad;
    int id;

    public Hilo(int velocidad, int id, Camino camino) {
        this.velocidad = velocidad;
        this.id = id;
        this.camino = camino;
    }

    @Override
    public void run() {
        while (camino.continuar) {
            System.out.println("El hilo '" + id + "' ha empezado");
            try {
                Thread.sleep(velocidad);
                System.out.println("HILO '" + id + "' EN BARRERA");
                camino.esperarHilos();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class BarreraMain {
    public static void main(String[] args) {
        Camino camino = new Camino();
        Thread hilo1 = new Thread(new Hilo(2000, 1, camino));
        Thread hilo2 = new Thread(new Hilo(5000, 2, camino));
        Thread hilo3 = new Thread(new Hilo(3000, 3, camino));

        hilo1.start();
        hilo2.start();
        hilo3.start();

    }
}
