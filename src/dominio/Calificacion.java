
package dominio;

public class Calificacion implements Comparable {
    private String cedula;
    private String codigoEvento;
    private int puntaje;
    private String comentario;
    
    public Calificacion (String Cedula, String CodigoEvento, int Puntaje, String Comentario){
        this.cedula = Cedula;
        this.codigoEvento = CodigoEvento;
        this.puntaje = Puntaje;
        this.comentario = Comentario;
    }
    

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String Comentario) {
        this.comentario = Comentario;
    }

    @Override
    public int compareTo(Object o) {
        Calificacion c = (Calificacion)o;
        return this.cedula.compareTo(c.getCedula());
    }
    
    
    
    
    
    
}
