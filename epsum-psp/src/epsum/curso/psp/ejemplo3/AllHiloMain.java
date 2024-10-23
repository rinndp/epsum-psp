package epsum.curso.psp.ejemplo3;

public class AllHiloMain {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Hilo 1: " + i);

                try  {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error: " + e);
                }
            }
        });

        Thread hilo2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Hilo 2: " + i);

                try  {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println("Error: " + e);
                }
            }
        });


        hilo1.start();
        hilo2.start(); //Se ejecutan a la par

        System.out.println("-----------------------------------------");

        try {
            hilo1.join();
            hilo2.join(); //Se ejecutan en orden

        } catch (InterruptedException e) {
            System.err.println("Error: " + e);
        }
    }
}
