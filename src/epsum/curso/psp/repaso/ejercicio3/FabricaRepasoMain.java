package epsum.curso.psp.repaso.ejercicio3;

class Fabrica {
    boolean empezarTrabajo = false;

    public synchronized void esperarTrabajo() throws InterruptedException {
        while (!empezarTrabajo) {
            System.out.println("Esperando trabajo...");
            wait();
        }
    }

    public synchronized void comienzaTrabajo() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("EMPEZANDO TRABAJO");
        Thread.sleep(1000);
        empezarTrabajo = true;
        notifyAll();
    }
}

class Trabajador implements Runnable {
    Fabrica fabrica;

    public Trabajador(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        try {
            fabrica.esperarTrabajo();
            System.out.println(Thread.currentThread().getName() + " está trabajando");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class FabricaRepasoMain {
    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();
        Thread trabajador1 = new Thread(new Trabajador(fabrica), "Pablo");
        Thread trabajador2 = new Thread(new Trabajador(fabrica), "Pepe");

        Thread empezarTrabajoHilo = new Thread(() -> {
            try {
                fabrica.comienzaTrabajo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        trabajador1.start();
        trabajador2.start();
        empezarTrabajoHilo.start();


    }
}
