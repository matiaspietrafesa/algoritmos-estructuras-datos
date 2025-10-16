package dominio;
import java.time.LocalDate;
import tads.Cola;
import tads.ListaNodos;

public class Evento implements Comparable {
    private String codigo;
    private LocalDate fecha;
    private Sala sala;
    private ListaNodos<Entrada> entradas;
    private Cola<Cliente> listaEspera;
    private String descripcion;
    private int aforoNecesario;
    private ListaNodos<Calificacion> calificaciones;
    private int puntajePromedio;

    
    
    
    public Evento(String Codigo, LocalDate Fecha, Sala Sala, String Descripcion, int AforoNecesario,int PuntajePromedio ){
        entradas = new ListaNodos();
        listaEspera = new Cola();
        calificaciones = new ListaNodos();
        codigo = Codigo;
        fecha = Fecha;
        sala = Sala;
        descripcion = Descripcion;
        aforoNecesario = AforoNecesario;
        puntajePromedio = PuntajePromedio;
    }
    
    public Evento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.aforoNecesario = aforoNecesario;
        this.fecha = fecha;
        this.entradas = new ListaNodos();
        this.listaEspera = new Cola();
        this.calificaciones = new ListaNodos();
    }
    
    public Evento(String codigo){
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public ListaNodos<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(ListaNodos<Entrada> entradas) {
        this.entradas = entradas;
    }

    public Cola<Cliente> getListaEspera() {
        return listaEspera;
    }

    public void setListaEspera(Cola<Cliente> listaEspera) {
        this.listaEspera = listaEspera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAforoNecesario() {
        return aforoNecesario;
    }

    public void setAforoNecesario(int aforoNecesario) {
        this.aforoNecesario = aforoNecesario;
    }

    public ListaNodos<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ListaNodos<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
    public double getPuntajePromedio() {
        if (this.calificaciones.esVacia()) return 0;

        int suma = 0;
        for (int i = 0; i < this.calificaciones.cantidadElementos(); i++) {
            suma += this.calificaciones.obtenerElemento(i).getPuntaje();
        }
        double promedio = suma / this.calificaciones.cantidadElementos();
        return promedio;
    }

    @Override
    public String toString(){
        return codigo + "-" + descripcion + "-" + sala.getNombre()  + "-" + (sala.getCapacidad() - entradas.cantidadElementos())+ "-" + entradas.cantidadElementos();
    }
    
    @Override
    public int compareTo(Object o) {
        Evento otroObjeto = (Evento)o;
        return this.codigo.compareTo(otroObjeto.getCodigo());
    }
    
    
}
