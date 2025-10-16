
package dominio;

import java.time.LocalDate;
import tads.ListaNodos;

public class Sala implements Comparable {
    
    
    private int capacidad;
    private String nombre;
    private ListaNodos<LocalDate> fechasOcupada;
    
    public Sala(int Capacidad, String Nombre)
    {
        this.nombre = Nombre;
        this.capacidad = Capacidad;
        this.fechasOcupada = new ListaNodos<LocalDate>();
    }    
    
    public Sala(String nombre) {
        this.nombre = nombre;
    }
    
    
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return nombre + "-" + capacidad;
    }

    public ListaNodos<LocalDate> getFechasOcupada() {
        return fechasOcupada;
    }

    public void setFechasOcupada(ListaNodos<LocalDate> fechasOcupada) {
        this.fechasOcupada = fechasOcupada;
    }

    @Override
    public int compareTo(Object o) {
        Sala otroObjeto = (Sala)o;
         return nombre.compareTo(otroObjeto.getNombre());
    }
    

}
