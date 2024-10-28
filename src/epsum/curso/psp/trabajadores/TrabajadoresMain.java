package epsum.curso.psp.trabajadores;
class Fabrica {
    boolean material = false;
    public synchronized void esperarMaterial () throws InterruptedException {
        while (!material) {
            System.out.println(Thread.currentThread().getName() + " esperando material");
            wait();
        }
    }

    public synchronized void llegarMaterial () throws InterruptedException {
        Thread.sleep(4000);
        material = true;
        System.out.println("El material ha llegado");
        notifyAll();
    }
}

class Trabajador implements Runnable {
    Fabrica fabrica;

    public Trabajador (Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        try {
            fabrica.esperarMaterial();
            System.out.println("Trabajador "+Thread.currentThread().getName()+" ha empezado a trabajar");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class TrabajadoresMain {
    public static void main(String[] args) throws InterruptedException {
        Fabrica fabrica = new Fabrica();
        Thread hilo1 = new Thread(new Trabajador(fabrica), "Pedro");
        Thread hilo2 = new Thread(new Trabajador(fabrica), "Pablo");
        Thread hilo3 = new Thread(new Trabajador(fabrica), "Carlos");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        fabrica.llegarMaterial();
    }
}
