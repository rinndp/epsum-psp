package epsum.curso.psp.ejemplo5.clases;

import java.util.LinkedList;

public class Almacen {
    private LinkedList<Integer> lista = new LinkedList();
    private final int CAPACIDAD = 5;

    public void setLista(LinkedList<Integer> lista) {
        this.lista = lista;
    }

    public void producir(int valor) throws InterruptedException {
        synchronized (this) {
            while (lista.size() == CAPACIDAD) {
                wait();
            }
            lista.add(valor);
            System.out.println("Producido: " + valor);
            notify();
        }
    }

    public void consumir() throws InterruptedException {
        synchronized (this) {
            while (lista.size() == 0) {
                wait();
            }

            int valor = lista.removeFirst();
            lista.removeFirst();
            System.out.println("Producto consumido: " + valor);
            notify();
        }
    }
}
