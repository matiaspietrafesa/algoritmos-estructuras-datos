
package dominio;

import tads.ListaNodos;

public class Cliente implements Comparable {

    private String cedula;
    private String nombre;
    private ListaNodos<Entrada> compras;
    
    
    public Cliente(String Cedula, String Nombre){
        cedula = Cedula;
        nombre = Nombre;
        compras = new ListaNodos();
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaNodos<Entrada> getCompras() {
        return compras;
    }

    public void setCompras(ListaNodos<Entrada> compras) {
        this.compras = compras;
    }

    @Override
    public String toString(){
        return cedula + "-" + nombre;
    }

    
    
    @Override
    public int compareTo(Object o) {
        Cliente c = (Cliente)o;
        return cedula.compareTo(c.getCedula());
    }
    
    
    
}
