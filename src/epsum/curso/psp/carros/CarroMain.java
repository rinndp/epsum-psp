package epsum.curso.psp.carros;
class Puente {
    boolean carroSurPasando;
    boolean carroNortePasando;

    public void pasarCarroNorte () throws InterruptedException {
        synchronized (this) {
            while (true) {
                if (carroSurPasando) {
                    System.out.println("Carro sur pasando. Norte ESPERANDO...");
                    wait();
                }

                this.carroNortePasando = true;
                System.out.println("Carro NORTE pasando...");
                Thread.sleep(4000);
                System.out.println("Carro NORTE ha pasado.");
                Thread.sleep(4000);
                this.carroNortePasando = false;
                notify();
                wait();
            }
        }
    }

    public void pasarCarroSur () throws InterruptedException {
        synchronized (this) {
            while (true) {
                if (carroNortePasando) {
                    System.out.println("Carro norte pasando. SUR ESPERANDO...");
                    wait();
                }

                this.carroSurPasando = true;
                System.out.println("Carro SUR pasando...");
                Thread.sleep(2000);
                System.out.println("Carro SUR ha pasado.");
                Thread.sleep(2000);
                this.carroSurPasando = false;
                notify();
                wait();
            }
        }
    }
}
public class CarroMain {
    public static void main(String[] args) {
        Puente puente = new Puente();

        Thread hiloCarroNorte1 = new Thread(() -> {
            try {
                puente.pasarCarroNorte();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread hiloCarroNorte2 = new Thread(() -> {
            try {
                puente.pasarCarroNorte();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        Thread hiloCarroSur1 = new Thread(() -> {
            try {
                puente.pasarCarroSur();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread hiloCarroSur2 = new Thread(() -> {
            try {
                puente.pasarCarroSur();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        hiloCarroNorte1.start();
        hiloCarroNorte2.start();
        hiloCarroSur1.start();
        hiloCarroSur2.start();
    }
}
