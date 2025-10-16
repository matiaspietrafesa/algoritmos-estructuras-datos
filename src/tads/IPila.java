package tads;


public interface IPila<T> {

    public void push(T x);

    public void mostrar();

    public int cantidadElementos();

    public boolean esVacia();

    public void vaciar();

    //public boolean existeElemento (T x);
    public void pop();

    public T top();
}
