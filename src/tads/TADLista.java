package tads;

import dominio.Sala;

public class TADLista {

    public static void main(String[] args) {
//        ListaV1 lista = new ListaV1(5);
//        lista.agregarFinal(2);
//        lista.agregarFinal(3);
//        lista.agregarFinal(4);
//        lista.mostrar();

          ListaNodos<Sala> salas = new ListaNodos();
          Sala sala1 = new Sala(10, "Sala Azul");
          Sala sala2 = new Sala(20, "Sala Verde");
          Sala sala3 = new Sala(30, "Sala Marron");
          
          Sala sala4 = new Sala("Sala Azul");
          
          salas.agregarInicio(sala2);
          salas.agregarInicio(sala1);
          salas.agregarInicio(sala3);
          
          salas.mostrar();
          System.out.println(salas.existeElemento(sala4));
          System.out.println(salas.obtenerElemento(1));
          salas.eliminarEnPos(1);
          salas.mostrar();
    }

}
