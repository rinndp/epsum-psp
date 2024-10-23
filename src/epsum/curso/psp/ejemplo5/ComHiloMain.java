package epsum.curso.psp.ejemplo5;

import epsum.curso.psp.ejemplo5.clases.Almacen;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ComHiloMain {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        LinkedList <Integer> lista = new LinkedList<>();
        lista.add(100);
        lista.add(200);
        lista.add(300);
        lista.add(400);
        lista.add(500);
        almacen.setLista(lista);

        Thread productor = new Thread(() -> {
            try {
                int valor = 1;

                while (true) {
                    almacen.producir(valor);
                    valor++;
                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });

        Thread consumidor = new Thread(() -> {
            try {
                while (true) {
                    almacen.consumir();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });

        productor.start();
        consumidor.start();

    }
}
