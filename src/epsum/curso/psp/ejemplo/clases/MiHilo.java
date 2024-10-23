package epsum.curso.psp.ejemplo.clases;

public class MiHilo extends  Thread{

    //run

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo: "+ i);

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.err.println("Error: "+e);
            }
        }
    }
}
