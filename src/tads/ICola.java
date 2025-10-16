/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author pietr
 * @param <T>
 */
public interface ICola<T> {
    public void mostrar();
    public int cantidad();
    public boolean esVacia();
    public void vaciar();
    public void encolar(T item);
    public void desencolar();
    public T frente();
}
