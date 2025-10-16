/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author pietr
 * @param <T>
 */
public class Cola<T extends Comparable> implements ICola<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantidad;

    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }

    @Override
    public void mostrar() {
        Nodo<T> curr = inicio;
        while (inicio != null) {
            System.out.print(curr.getDato() + " - ");
            curr = curr.getSiguiente();
        }
    }

    @Override
    public int cantidad() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public void vaciar() {
        inicio = fin = null;
        cantidad = 0;
    }

    @Override
    public void encolar(T item) {
        Nodo<T> nodo = new Nodo(item);
        if (fin != null) {
            fin.setSiguiente(nodo);
            fin = nodo;
        } else {
            inicio = fin = nodo;
        }
        cantidad++;
    }

    @Override
    public void desencolar() {
        if (inicio != null) {
            if (inicio == fin) {
                vaciar();
            } else {
                inicio = inicio.getSiguiente();
                cantidad--;
            }
        }
    }

    @Override
    public T frente() {
        if (!esVacia()){
            return inicio.getDato();
        }
        return null;
    }
    
    public boolean existeElemento(T x){
        boolean existe = false;
        Nodo<T> aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().compareTo(x) == 0) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

}
