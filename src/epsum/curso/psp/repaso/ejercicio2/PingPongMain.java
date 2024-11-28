package epsum.curso.psp.repaso.ejercicio2;

class PingPongController {
    boolean ping = true;
    boolean pong = false;

    public synchronized void pingPrinter () throws InterruptedException {
        while (!ping) {
            wait();
        }

        System.out.println("PING");
        Thread.sleep(1000);
        pong = true;
        notify();
        ping = false;
    }

    public synchronized void pongPrinter () throws InterruptedException {
        while (!pong) {
            wait();
        }

        System.out.println("PONG");
        Thread.sleep(1000);
        ping = true;
        notify();
        pong = false;
    }
}

public class PingPongMain {
    public static void main(String[] args) {
        PingPongController pingPongController = new PingPongController();

        Thread pingPrinter = new Thread(() -> {
            while (true) {
                try {
                    pingPongController.pingPrinter();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread pongPrinter = new Thread(() -> {
            while (true) {
                try {
                    pingPongController.pongPrinter();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pingPrinter.start();
        pongPrinter.start();
    }
}
