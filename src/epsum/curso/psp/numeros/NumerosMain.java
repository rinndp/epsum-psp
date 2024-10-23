package epsum.curso.psp.numeros;

class ContadorGeneral {
    int contadorImpar = 1;
    int contadorPar = 2;
    int contadorTotal = 0;

    public void imprimirNumeroPar() throws InterruptedException {
        synchronized (this) {
            while (true) {
                System.out.println("Contador par: "+contadorPar);
                contadorPar += 2;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error:"+ e);
                }
                notify();
                wait();
            }
        }
    }

    public void imprimirNumeroImpar() throws InterruptedException {
        synchronized (this) {
            while (true) {
                System.out.println("Contador impar: "+contadorImpar);
                contadorImpar += 2;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error:"+ e);
                }

                notify();
                wait();
            }
        }
    }
}
public class NumerosMain {
    public static void main(String[] args) {
        ContadorGeneral incrementar = new ContadorGeneral();

        Thread hiloImpar = new Thread(() -> {
            try {
                incrementar.imprimirNumeroImpar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread hiloPar = new Thread(() -> {
            try {
                incrementar.imprimirNumeroPar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        hiloImpar.start();
        hiloPar.start();


    }
}
