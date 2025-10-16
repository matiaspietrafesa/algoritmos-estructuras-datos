package tads;

public class ListaNodos<T extends Comparable> implements IListaSimple<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;

    public ListaNodos() {
        this.inicio = null;
        this.fin = null;
        cantElementos = 0;
    }

    @Override
    public void agregarInicio(T x) {

        Nodo<T> nuevo = new Nodo(x);

        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        cantElementos++;

    }

    @Override
    public String mostrar() {
        String mensaje = "";
        Nodo<T> aux = inicio;
        while (aux != null) {
            mensaje += aux.getDato() + " - ";
            aux = aux.getSiguiente();
        }
        return mensaje;
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
        fin = null;
        cantElementos = 0;
    }

    @Override
    public boolean existeElemento(T x) {
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

    @Override
    public T obtenerElemento(int indice) {
        if (indice < 0 || indice >= cantElementos) {
            throw new IndexOutOfBoundsException("Indice no valido.");
        }

        int i = 0;
        Nodo<T> curr = inicio;

        while (curr != null && i < indice) {
            i++;
            curr = curr.getSiguiente();
        }

        return curr.getDato();
    }

    @Override
    public void agregarFinal(T x) {
        if (esVacia()) {
            agregarInicio(x);
        } else {
            Nodo<T> nuevo = new Nodo(x);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            cantElementos++;
        }
    }

    @Override
    public void eliminarInicio() {
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
    public void eliminarFinal() {
        if (!esVacia()) {
            if (cantElementos == 1) { //Tengo solo 1 elemento
                vaciar();
            } else { //Tengo mas de 1 elemento
                Nodo<T> aux = inicio;
                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
                fin = aux;
                cantElementos--;
            }
        }
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

    public Nodo<T> getFin() {
        return fin;
    }

    @Override
    public void insertarEnPos(int pos, T dato) {

        if (pos == 0) {
            agregarInicio(dato);
        } else {
            if (pos == cantElementos) {
                agregarFinal(dato);
            } else {

                int indiceActual = 1;
                Nodo ant = inicio;
                Nodo actual = inicio.getSiguiente();
                while (pos != indiceActual) {
                    ant = actual;
                    actual = actual.getSiguiente();
                    indiceActual++;
                }
                Nodo nuevo = new Nodo(dato);
                nuevo.setSiguiente(actual);
                ant.setSiguiente(nuevo);
                cantElementos++;

            }
        }

    }

    @Override
    public void eliminarEnPos(int pos) {
        if (pos < 0 || pos >= cantElementos) {
            throw new IndexOutOfBoundsException("Indice no valido.");
        }

        if (pos == 0) {
            eliminarInicio();
        } else if (pos == cantElementos - 1) {
            eliminarFinal();
        } else {
            Nodo<T> aux = inicio;
            int i = 0;

            while (aux != null && i < pos - 1) {
                aux = aux.getSiguiente();
                i++;
            }

            Nodo<T> aEliminar = aux.getSiguiente();
            aux.setSiguiente(aEliminar.getSiguiente());
            cantElementos--;
        }
    }

    public void agregarOrdenado(T elemento) {
        if (esVacia() || elemento.compareTo(inicio.getDato()) < 0) {
            agregarInicio(elemento);
        } else if (elemento.compareTo(fin.getDato()) >= 0) {  // Mismo error aquí
            agregarFinal(elemento);
        } else {
            Nodo<T> act = inicio;
            while (act.getSiguiente() != null && elemento.compareTo(act.getSiguiente().getDato()) > 0) {
                act = act.getSiguiente();
            }
            if (fin == act) {
                agregarFinal(elemento);
            } else {
                Nodo<T> nuevo = new Nodo<>(elemento);
                nuevo.setSiguiente(act.getSiguiente());
                act.setSiguiente(nuevo);
                cantElementos++;
            }
        }
    }

}
