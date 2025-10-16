package tads;

public interface IListaSimple<T> {

    public void agregarInicio(T x);
    
    public String mostrar();
    
    public int cantidadElementos ();
    
    public boolean esVacia();
    
    public void vaciar();
    
    public boolean existeElemento (T x);
    
    public T obtenerElemento(int indice);
    
    public void agregarFinal (T x);
    
    public void eliminarInicio();
    
    public void eliminarFinal();

    //pre: pos >= 0 y pos <= cant. elementos
    public void insertarEnPos(int pos, T dato);
    
    //pre: pos >= 0 y pos <= cant. elementos
    public void eliminarEnPos(int pos);
}
