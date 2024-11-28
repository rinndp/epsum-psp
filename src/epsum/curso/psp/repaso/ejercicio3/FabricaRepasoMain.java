package epsum.curso.psp.repaso.ejercicio3;

class Fabrica {
    boolean empezarTrabajo = false;

    public synchronized void esperarTrabajo() throws InterruptedException {
        while(!empezarTrabajo) {
            System.out.println("Esperando trabajo...");
            Thread.sleep(2000);
            System.out.println("EMPEZANDO TRABAJO");
            Thread.sleep(1000);
            empezarTrabajo = true;
        }
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

        trabajador1.start();
        trabajador2.start();
    }
}
