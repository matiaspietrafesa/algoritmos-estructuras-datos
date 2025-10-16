
package dominio;

public class Entrada implements Comparable {
    private String cedula;
    private Cliente cliente;
    private String codigoEvento;
    private Evento evento;
    private boolean fueDevuelta;

    public Entrada(Cliente cliente, Evento evento) {
        this.cliente = cliente;
        this.evento = evento;
        this.cedula = cliente.getCedula();
        this.codigoEvento = evento.getCodigo();
        this.fueDevuelta = false;
    }

    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    

    public Entrada(String Cedula, String CodigoEvento){
        cedula = Cedula;
        codigoEvento = CodigoEvento;
        fueDevuelta = false;
        
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

    public boolean isFueDevuelta() {
        return fueDevuelta;
    }

    public void setFueDevuelta(boolean fueDevuelta) {
        this.fueDevuelta = fueDevuelta;
    }

    @Override
    public int compareTo(Object otra) {
        
        Entrada otroObjeto = (Entrada)otra;
        int comparacionEvento = this.codigoEvento.compareTo(otroObjeto.codigoEvento);
        
        if (comparacionEvento == 0) {
            return this.cedula.compareTo(otroObjeto.cedula);
        }
        
        return comparacionEvento;
    }
    
    @Override
    public String toString(){
        return this.codigoEvento + "-" + (this.fueDevuelta ? "D" : "N");
    }
    
    
    
}
