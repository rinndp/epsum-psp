package epsum.curso.psp.vehiculos;

import java.util.LinkedList;

class Parking {
    LinkedList <Integer> listaCoches = new LinkedList<>();
    private final int CAPACIDAD = 5;
    private int contadorVehiculos = 0;

    public void setListaCoches(LinkedList<Integer> listaCoches) {
        this.listaCoches = listaCoches;
    }

    public void entraCoche () throws InterruptedException {
        synchronized (this) {
            if (contadorVehiculos == CAPACIDAD) {
                wait();
            }
            while (true) {
                listaCoches.add(contadorVehiculos);
                System.out.println("Un coche entra al PARKING: " + contadorVehiculos);
                contadorVehiculos++;

                Thread.sleep(1000);
                notify();
            }
        }
    }


    public void saleCoche () throws InterruptedException {
        synchronized (this) {
            if (contadorVehiculos == 0) {
                wait();
            }

            while (true) {
                listaCoches.removeFirst();
                System.out.println("Un coche sale al PARKING: " + contadorVehiculos);
                contadorVehiculos--;

                Thread.sleep(1000);
                notify();
            }

        }

    }
}

public class VehiculosMain {
    public static void main(String[] args) {
        Parking parking = new Parking();


        Thread hiloEntrar = new Thread(() -> {
            try {
                parking.entraCoche();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread hiloSalir = new Thread(() -> {
            try {
                parking.saleCoche();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        hiloEntrar.start();
        hiloSalir.start();
    }
}
