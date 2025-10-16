package tads;


public class Pila<T extends Comparable> implements IPila<T> {

    private Nodo<T> inicio;
    private int cantElementos;

    public Pila() {
        this.inicio = null;
        cantElementos = 0;
    }

    @Override
    public void push(T x) {

        Nodo<T> nuevo = new Nodo(x);

        if (esVacia()) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }

        cantElementos++;
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        /*
        int cant = 0;
        Nodo aux = inicio;

        while (aux != null) {
            cant++;
            aux = aux.getSiguiente();
        }
        return cant;*/
        return cantElementos;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void vaciar() {
        inicio = null;
        cantElementos = 0;
    }

    /*
    @Override
    public boolean existeElemento(T x) {
        boolean existe = false;
        Nodo<T> aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(x)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }
     */

    @Override
    public void pop() {
        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                Nodo aBorrar = inicio;
                inicio = inicio.getSiguiente();
                aBorrar.setSiguiente(null);
                cantElementos--;
            }

        }
    }
    
    @Override
    public T top(){
        return inicio.getDato();
    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }
}
