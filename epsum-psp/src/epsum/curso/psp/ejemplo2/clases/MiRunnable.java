package epsum.curso.psp.ejemplo2.clases;

public class MiRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo: "+i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Error: "+e);
            }
        }
    }
}
