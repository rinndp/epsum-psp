package epsum.curso.psp.repaso.ejercicio4;

import static java.lang.System.exit;

class Parking  {
    private final int CAPACIDAD = 3;
    private int contadorVehiculosParking = 0;
    boolean lleno = false;
    boolean cerrado = false;

    public synchronized  void aparcaVehiculo () throws InterruptedException {
        while (this.contadorVehiculosParking == CAPACIDAD) {
            lleno = true;
            wait();
        }

        System.out.println("El "+Thread.currentThread().getName()+" ha APARCADO al parking");
        contadorVehiculosParking++;
        notifyAll();
        wait();


        if (cerrado)
            wait();

    }

    public synchronized void saleVehiculo () throws InterruptedException {
        while (contadorVehiculosParking < CAPACIDAD) {
            lleno = false;
            wait();
        }

        System.out.println("El " +Thread.currentThread().getName()+ " ha SALIDO al parking");
        contadorVehiculosParking--;
        notifyAll();
        wait();

        if (cerrado)
            wait();
    }

    public synchronized void cierreParking () throws InterruptedException {
        System.out.println("EL PARKING HA CERRADO");
        cerrado = true;
        exit(0);
    }
}

class Vehiculos implements Runnable {
    Parking parking;
    int cooldown;

    public Vehiculos (Parking parking, int cooldown) {
        this.parking = parking;
        this.cooldown = cooldown;

    }

    @Override
    public void run() {
        try {
            Thread.sleep(cooldown);
            parking.aparcaVehiculo();
            Thread.sleep(cooldown);
            parking.saleVehiculo();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class EstacionamientoMain {
    public static void main(String[] args) {
        Parking parking = new Parking();

        Thread vehiculo1 = new Thread(new Vehiculos(parking, 6000), "Nissan");
        Thread vehiculo2 = new Thread(new Vehiculos(parking, 7000), "Toyota");
        Thread vehiculo3 = new Thread(new Vehiculos(parking, 10000), "Subaru");
        Thread vehiculo4 = new Thread(new Vehiculos(parking, 8000), "Seat Ibiza");
        Thread vehiculo5 = new Thread(new Vehiculos(parking, 7000), "Volvo");
        Thread vehiculo6 = new Thread(new Vehiculos(parking, 9000), "Mazda");
        Thread vehiculo7 = new Thread(new Vehiculos(parking, 11000), "Audi");

        vehiculo1.start();
        vehiculo2.start();
        vehiculo3.start();
        vehiculo4.start();
        vehiculo5.start();
        vehiculo6.start();
        vehiculo7.start();


        try {
            Thread.sleep(40000);
            parking.cierreParking();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
