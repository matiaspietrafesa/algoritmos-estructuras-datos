/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import dominio.Sala;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
    }

    @Test
    public void testCrearSistemaDeGestion() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testRegistrarSalaOk() {
        Retorno.Resultado r = miSistema.registrarSala("Sala Azul", 17).resultado;
        assertEquals(r, Retorno.Resultado.OK);
    }

    @Test
    public void testRegistrarSalaError1() {
        miSistema.registrarSala("Sala Azul", 30);
        Retorno.Resultado r = miSistema.registrarSala("Sala Azul", 20).resultado;
        assertEquals(r, Retorno.Resultado.ERROR_1);
    }

    @Test
    public void testRegistrarSalaError2() {
        Retorno.Resultado r = miSistema.registrarSala("Sala Azul", 0).resultado;
        assertEquals(r, Retorno.Resultado.ERROR_2);
    }

    @Test
    public void testRegistrarSala() {
        miSistema.registrarSala("Sala Verde", 30);
        miSistema.registrarSala("Sala Azul", 40);
        miSistema.registrarSala("Sala Marron", 20);

        Retorno r = miSistema.listarSalas();

        assertEquals("Sala Marron-20#Sala Azul-40#Sala Verde-30", r.valorString);
    }

    @Test
    public void testRegistrarSalaNull() {
        Retorno r = miSistema.listarSalas();

        assertEquals("", r.valorString);
    }

    @Test
    public void testEliminarSalaOk() {
        miSistema.registrarSala("Sala Gris", 10);
        Retorno.Resultado r = miSistema.eliminarSala("Sala Gris").resultado;
        assertEquals(r, Retorno.Resultado.OK);
    }

    @Test
    public void testEliminarSalaError1() {
        Retorno.Resultado r = miSistema.eliminarSala("Sala Violeta").resultado;
        assertEquals(r, Retorno.Resultado.ERROR_1);
    }

    @Test
    public void testEliminarSala() {
        miSistema.registrarSala("Sala Verde", 30);
        miSistema.registrarSala("Sala Azul", 40);
        miSistema.registrarSala("Sala Marron", 20);

        miSistema.eliminarSala("Sala Azul");

        Retorno r2 = miSistema.listarSalas();

        assertEquals("Sala Marron-20#Sala Verde-30", r2.valorString);
    }

    @Test
    public void testEliminarSalaConEvento() {
        miSistema.registrarSala("Sala azul", 20);
        miSistema.registrarEvento("COD1", "Obra de teatro", 20, LocalDate.now());
        miSistema.eliminarSala("Sala azul");
        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-Obra de teatro-Sala azul-20-0", r.valorString);
    }

    @Test
    public void testEliminarSala2() {
        miSistema.registrarSala("Sala 1", 20);
        miSistema.registrarSala("Sala 2", 15);
        miSistema.registrarSala("Sala 3", 30);
        miSistema.eliminarSala("Sala 1");
        Retorno r = miSistema.listarSalas();

        assertEquals("Sala 3-30#Sala 2-15", r.valorString);
    }

    @Test
    public void testEliminarSala3() {
        miSistema.registrarSala("Sala 1", 20);
        miSistema.registrarSala("Sala 2", 15);
        miSistema.registrarSala("Sala 3", 30);
        miSistema.eliminarSala("Sala 3");
        Retorno r = miSistema.listarSalas();

        assertEquals("Sala 2-15#Sala 1-20", r.valorString);
    }

    @Test
    public void testEliminarSala4() {
        miSistema.registrarSala("Sala 1", 20);
        miSistema.registrarSala("Sala 2", 15);
        miSistema.registrarSala("Sala 3", 30);
        miSistema.eliminarSala("Sala 2");
        Retorno r = miSistema.listarSalas();

        assertEquals("Sala 3-30#Sala 1-20", r.valorString);
    }

    @Test
    public void testRegistrarEventoOk() {
        LocalDate fecha = LocalDate.now();
        miSistema.registrarSala("Sala Amarilla", 10);
        Retorno.Resultado r = miSistema.registrarEvento("COD1", "Concierto", 10, fecha).resultado;
        assertEquals(r, Retorno.Resultado.OK);
    }

    @Test
    public void testRegistrarEventoError1() {
        LocalDate fecha = LocalDate.now();
        miSistema.registrarSala("Sala Amarilla", 10);
        miSistema.registrarEvento("COD1", "Concierto", 10, fecha);
        Retorno.Resultado r = miSistema.registrarEvento("COD1", "Concierto", 10, fecha).resultado;
        assertEquals(r, Retorno.Resultado.ERROR_1);
    }

    @Test
    public void testRegistrarEventoError2() {
        LocalDate fecha = LocalDate.now();
        Retorno.Resultado r = miSistema.registrarEvento("COD1", "Concierto", 0, fecha).resultado;
        assertEquals(r, Retorno.Resultado.ERROR_2);
    }

    @Test
    public void testRegistrarEventoError3() {
        LocalDate fecha = LocalDate.now();
        miSistema.registrarEvento("COD1", "Concierto", 10, fecha);
        Retorno.Resultado r = miSistema.registrarEvento("COD1", "Concierto", 10, fecha).resultado;
        assertEquals(r, Retorno.Resultado.ERROR_3);
    }

    @Test
    public void testRegistrarEvento() {
        LocalDate fecha = LocalDate.now();

        miSistema.registrarSala("Sala Azul", 320);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);

        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-Evento1-Sala Azul-320-0", r.valorString);
    }

    @Test
    public void testRegistrarEvento2() {
        LocalDate fecha = LocalDate.now();

        miSistema.registrarSala("Sala Azul", 320);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);
        miSistema.registrarEvento("COD2", "Evento2", 150, fecha);

        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-Evento1-Sala Azul-320-0", r.valorString);
    }

    @Test
    public void testRegistrarEvento3() {
        LocalDate fecha = LocalDate.now();
        LocalDate fecha2 = LocalDate.now().plusDays(1);

        miSistema.registrarSala("Sala Azul", 320);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);
        miSistema.registrarEvento("COD2", "Evento2", 150, fecha2);

        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-Evento1-Sala Azul-320-0#COD2-Evento2-Sala Azul-320-0", r.valorString);
    }

    @Test
    public void testRegistrarEvento4() {
        LocalDate fecha = LocalDate.now();

        miSistema.registrarSala("Sala Azul", 320);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);
        miSistema.eliminarEvento("COD1");
        miSistema.registrarEvento("COD2", "Evento2", 150, fecha);

        Retorno r = miSistema.listarEventos();

        assertEquals("COD2-Evento2-Sala Azul-320-0", r.valorString);
    }

    @Test
    public void testRegistrarClienteOk() {
        Retorno.Resultado r = miSistema.registrarCliente("12345678", "Felipe").resultado;
        assertEquals(r, Retorno.Resultado.OK);
    }

    @Test
    public void testRegistrarClienteError1() {
        Retorno.Resultado r = miSistema.registrarCliente("1234567", "Juan").resultado;
        assertEquals(r, Retorno.Resultado.ERROR_1);
    }

    @Test
    public void testRegistrarClienteError2() {
        miSistema.registrarCliente("12345678", "Ernesto");
        Retorno.Resultado r = miSistema.registrarCliente("12345678", "Felipe").resultado;
        assertEquals(r, Retorno.Resultado.ERROR_2);
    }

    @Test
    public void testRegistrarCliente() {
        miSistema.registrarCliente("87654321", "Carlos");
        miSistema.registrarCliente("12345678", "Pablo");

        Retorno r = miSistema.listarClientes();

        assertEquals("12345678-Pablo#87654321-Carlos", r.valorString);
    }

    @Test
    public void testRegistrarCliente2() {
        miSistema.registrarCliente("12345678", "Carlos");
        miSistema.registrarCliente("", "Gardel");
        miSistema.registrarCliente("12345678", "Julio");
        miSistema.registrarCliente("22222222", "Carlos");
        Retorno r = miSistema.listarClientes();

        assertEquals("12345678-Carlos#22222222-Carlos", r.valorString);
    }

    @Test
    public void testListarSalas() {
        Retorno r = miSistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testListarSalas_FormatoCorrecto() {

        miSistema.registrarSala("Sala1", 300);
        miSistema.registrarSala("Sala2", 50);

        Retorno r = miSistema.listarSalas();

        assertEquals("Sala2-50#Sala1-300", r.valorString);

    }

    @Test
    public void testListarSalas_Vacio() {
        Retorno r = miSistema.listarSalas();

        assertEquals("", r.valorString);
    }

    @Test
    public void testListarEventos() {
        Retorno r = miSistema.listarEventos();
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testListarEventos_FormatoCorrecto() {

        LocalDate fecha = LocalDate.now();
        LocalDate fecha2 = LocalDate.of(2025, 5, 20);

        miSistema.registrarSala("Sala azul", 340);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);
        miSistema.registrarEvento("COD2", "Evento2", 330, fecha);
        miSistema.registrarEvento("COD3", "Evento3", 350, fecha2);
        miSistema.registrarEvento("COD4", "Evento4", 200, fecha2);

        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-Evento1-Sala azul-340-0#COD4-Evento4-Sala azul-340-0", r.valorString);
    }

    @Test
    public void testListarEventosConDelete() {

        LocalDate fecha = LocalDate.now();
        LocalDate fecha2 = LocalDate.now().plusDays(1);
        LocalDate fecha3 = LocalDate.now().plusDays(2);

        miSistema.registrarSala("Sala azul", 340);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);
        miSistema.registrarEvento("COD2", "Evento2", 200, fecha2);
        miSistema.registrarEvento("COD3", "Evento3", 100, fecha3);

        miSistema.eliminarEvento("COD2");

        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-Evento1-Sala azul-340-0#COD3-Evento3-Sala azul-340-0", r.valorString);
    }

    @Test
    public void testListarEventosConDelete2() {

        LocalDate fecha = LocalDate.now();
        LocalDate fecha2 = LocalDate.now().plusDays(1);
        LocalDate fecha3 = LocalDate.now().plusDays(2);

        miSistema.registrarSala("Sala azul", 340);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);
        miSistema.registrarEvento("COD2", "Evento2", 200, fecha2);
        miSistema.registrarEvento("COD3", "Evento3", 100, fecha3);

        miSistema.eliminarEvento("COD1");

        Retorno r = miSistema.listarEventos();

        assertEquals("COD2-Evento2-Sala azul-340-0#COD3-Evento3-Sala azul-340-0", r.valorString);
    }

    @Test
    public void testListarEventosConDelete3() {

        LocalDate fecha = LocalDate.now();
        LocalDate fecha2 = LocalDate.now().plusDays(1);
        LocalDate fecha3 = LocalDate.now().plusDays(2);

        miSistema.registrarSala("Sala azul", 340);
        miSistema.registrarEvento("COD1", "Evento1", 300, fecha);
        miSistema.registrarEvento("COD2", "Evento2", 200, fecha2);
        miSistema.registrarEvento("COD3", "Evento3", 100, fecha3);

        miSistema.eliminarEvento("COD3");

        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-Evento1-Sala azul-340-0#COD2-Evento2-Sala azul-340-0", r.valorString);
    }

    @Test
    public void testListarEventos_Vacio() {
        Retorno r = miSistema.listarEventos();

        assertEquals("", r.valorString);
    }

    @Test
    public void testListarClientes() {
        Retorno r = miSistema.listarClientes();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testListarClientes_FormatoCorrecto() {

        miSistema.registrarCliente("33333333", "Pepe");
        miSistema.registrarCliente("11111111", "Grillo");
        miSistema.registrarCliente("22222222", "Juan");

        Retorno r = miSistema.listarClientes();

        assertEquals("11111111-Grillo#22222222-Juan#33333333-Pepe", r.valorString);

    }

    @Test
    public void testListarClientes_Vacio() {
        Retorno r = miSistema.listarClientes();

        assertEquals("", r.valorString);
    }

    @Test
    public void testEsSalaOptima_Optima() {

        //no  no  no  si  si  no  no
        String[][] sala = {{"#", "#", "#", "#", "#", "#", "#"},
        {"#", "#", "X", "X", "X", "X", "#"},
        {"#", "O", "O", "X", "X", "X", "#"},
        {"#", "O", "O", "O", "O", "X", "#"},
        {"#", "O", "O", "X", "O", "O", "#"},
        {"#", "O", "O", "O", "O", "O", "#"},
        {"#", "X", "X", "O", "O", "O", "O"},
        {"#", "X", "X", "O", "O", "O", "X"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "#", "#", "O", "#", "#", "#"},
        {"#", "#", "#", "O", "#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);

        assertEquals("Es óptimo", r.valorString);
    }

    @Test
    public void testEsSalaOptima_NoOptima() {

        //no  no  no  si  no  no  no
        String[][] sala = {{"#", "#", "#", "#", "#", "#", "#"},
        {"#", "#", "X", "X", "X", "X", "#"},
        {"#", "O", "O", "X", "X", "X", "#"},
        {"#", "O", "O", "O", "X", "X", "#"},
        {"#", "O", "O", "X", "X", "O", "#"},
        {"#", "O", "O", "O", "O", "O", "#"},
        {"#", "X", "X", "O", "O", "O", "O"},
        {"#", "X", "X", "O", "O", "O", "X"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "#", "#", "O", "#", "#", "#"},
        {"#", "#", "#", "O", "#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);

        assertEquals("No es óptimo", r.valorString);
    }

    @Test
    public void testEsSalaOptima_Vacia() {

        String[][] sala = {{}, {}};

        Retorno r = miSistema.esSalaOptima(sala);

        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testComprarEntradaError1() {
        Retorno r = miSistema.comprarEntrada("12345678", "");

        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testComprarEntradaError2() {
        miSistema.registrarCliente("12345678", "Papo");
        Retorno r = miSistema.comprarEntrada("12345678", "COD1");

        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testComprarEntradaOk() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        Retorno r = miSistema.comprarEntrada("12345678", "COD1");

        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testComprarEntrada1() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        miSistema.comprarEntrada("12345678", "COD1");

        Retorno r = miSistema.comprasDeCliente("12345678");

        assertEquals("COD1-N", r.valorString);
    }

    @Test
    public void testComprarEntrada2() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarCliente("22222222", "Andres");
        miSistema.registrarSala("Sala azul", 1);
        miSistema.registrarEvento("COD1", "fiesta", 1, LocalDate.now());
        miSistema.comprarEntrada("12345678", "COD1");
        miSistema.comprarEntrada("22222222", "COD1");

        Retorno r = miSistema.listarEventos();

        assertEquals("COD1-fiesta-Sala azul-0-1", r.valorString);
    }

    @Test
    public void testEliminarEventoError1() {
        Retorno r = miSistema.eliminarEvento("COD1");

        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testEliminarEventoError2() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        miSistema.comprarEntrada("12345678", "COD1");
        Retorno r = miSistema.eliminarEvento("COD1");

        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testEliminarEventoOk() {
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        Retorno r = miSistema.eliminarEvento("COD1");

        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testCalificarEventoError1() {
        Retorno r = miSistema.calificarEvento("12345678", "", 1, "");

        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testCalificarEventoError2() {
        miSistema.registrarCliente("12345678", "Luca Prodan");
        Retorno r = miSistema.calificarEvento("12345678", "COD1", 1, "");

        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testCalificarEventoError3() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        Retorno r = miSistema.calificarEvento("12345678", "COD1", 11, "");

        assertEquals(Retorno.Resultado.ERROR_3, r.resultado);
    }

    @Test
    public void testCalificarEventoError4() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        miSistema.calificarEvento("12345678", "COD1", 1, "Malisima");
        Retorno r = miSistema.calificarEvento("12345678", "COD1", 10, "Buenisima");

        assertEquals(Retorno.Resultado.ERROR_4, r.resultado);
    }

    @Test
    public void testCalificarEventoOk() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        Retorno r = miSistema.calificarEvento("12345678", "COD1", 10, "Buenisima");

        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testComprasDeClienteOk() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        miSistema.comprarEntrada("12345678", "COD1");

        Retorno r = miSistema.comprasDeCliente("12345678");

        assertEquals("COD1-N", r.valorString);
    }

    @Test
    public void testListarClientesDeEventoError1() {
        Retorno r = miSistema.listarClientesDeEvento("COD1", 2);

        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testListarClientesDeEventoError2() {
        miSistema.registrarSala("Sala azul", 12);
        miSistema.registrarEvento("COD1", "fiesta", 5, LocalDate.now());
        Retorno r = miSistema.listarClientesDeEvento("COD1", 0);

        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testListarClientesDeEventoOk() {
        miSistema.registrarCliente("12345678", "Papo");
        miSistema.registrarCliente("87654321", "Pepo");
        miSistema.registrarCliente("11111111", "Pipo");
        miSistema.registrarSala("Sala azul", 2);
        miSistema.registrarEvento("COD1", "fiesta", 2, LocalDate.now());
        miSistema.comprarEntrada("12345678", "COD1");
        miSistema.comprarEntrada("87654321", "COD1");
        miSistema.comprarEntrada("11111111", "COD1");

        String r = miSistema.listarClientesDeEvento("COD1", 3).valorString;

        assertEquals("12345678-Papo#87654321-Pepo", r);
    }

    @Test
    public void testListaEsperaEvento_vacia() {

        Retorno ret = miSistema.listarEsperaEvento();

        assertEquals("", ret.valorString);

    }

    @Test
    public void testListaEsperaEvento_ok() {

        miSistema.registrarSala("Sala principal", 3);

        miSistema.registrarEvento("EV1", "Evento 1", 3, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");
        miSistema.registrarCliente("34567891", "Nicolas");
        miSistema.registrarCliente("34567892", "Florencia");
        miSistema.registrarCliente("34567894", "Amelia");

        miSistema.comprarEntrada("23456789", "EV1");
        miSistema.comprarEntrada("34567890", "EV1");
        miSistema.comprarEntrada("34567891", "EV1");
        miSistema.comprarEntrada("34567892", "EV1");
        miSistema.comprarEntrada("34567894", "EV1");

        Retorno ret = miSistema.listarEsperaEvento();

        assertEquals("EV1-34567892#EV1-34567894", ret.valorString);

    }

    @Test
    public void testOrdenColaEsperaLuegoDeListar() {
        miSistema.registrarSala("Sala principal", 1);

        miSistema.registrarEvento("EV1", "Evento 1", 1, LocalDate.now());

        miSistema.registrarCliente("11111111", "Gaston");
        miSistema.registrarCliente("22222222", "Michelle");
        miSistema.registrarCliente("33333333", "Nicolas");
        miSistema.registrarCliente("44444444", "Nicolas");

        miSistema.comprarEntrada("11111111", "EV1");
        miSistema.comprarEntrada("44444444", "EV1");
        miSistema.comprarEntrada("33333333", "EV1");
        miSistema.comprarEntrada("22222222", "EV1");
        // el frente de la cola es el 44444444, por lo que deberia ser el siguiente
        // en recibir una entrada si se devuelve una vendida

        miSistema.listarEsperaEvento();
        // el listar espera evento esta cambiando el orden de la cola de espera
        // la deja en orden en lugar de dejarla como estaba

        miSistema.devolverEntrada("11111111", "EV1");
        // cuando se devuelve la entrada se le asigna al cliente 22222222
        // en lugar de al 44444444 como deberia

        Retorno r = miSistema.listarEsperaEvento();

        assertEquals("EV1-22222222#EV1-33333333", r.valorString);
    }

    @Test
    public void testOrdenColaMultipleEsperaLuegoDeListar() {
        miSistema.registrarSala("Sala principal", 1);
        miSistema.registrarSala("Sala secundaria", 1);

        miSistema.registrarEvento("EV1", "Evento 1", 1, LocalDate.now());
        miSistema.registrarEvento("EV2", "Evento 2", 1, LocalDate.now());

        miSistema.registrarCliente("11111111", "Gaston");
        miSistema.registrarCliente("22222222", "Michelle");
        miSistema.registrarCliente("33333333", "Nicolas");
        miSistema.registrarCliente("44444444", "Nicolas");

        miSistema.registrarCliente("55555555", "Juan");
        miSistema.registrarCliente("66666666", "Maicol");
        miSistema.registrarCliente("77777777", "Pepe");

        miSistema.comprarEntrada("11111111", "EV1");
        miSistema.comprarEntrada("44444444", "EV1");
        miSistema.comprarEntrada("33333333", "EV1");
        miSistema.comprarEntrada("22222222", "EV1");

        miSistema.comprarEntrada("55555555", "EV2");
        miSistema.comprarEntrada("66666666", "EV2");
        miSistema.comprarEntrada("77777777", "EV2");

        miSistema.listarEsperaEvento();

        miSistema.devolverEntrada("11111111", "EV1");

        Retorno r = miSistema.listarEsperaEvento();

        assertEquals("EV1-22222222#EV1-33333333#EV2-66666666#EV2-77777777", r.valorString);
    }

    @Test
    public void testDeshacerUltimasCompras_ningunaCompra() {
        Retorno ret = miSistema.deshacerUtimasCompras(0);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("", ret.valorString);
    }

    @Test
    public void testDeshacerUltimasCompras_unaCompra() {

        miSistema.registrarSala("Sala principal", 3);
        miSistema.registrarEvento("EV1", "Evento 1", 3, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");

        miSistema.comprarEntrada("23456789", "EV1");
        miSistema.comprarEntrada("34567890", "EV1");

        Retorno r = miSistema.deshacerUtimasCompras(1);

        assertEquals("EV1-34567890", r.valorString);
    }

    @Test
    public void testDeshacerUltimasCompras_multiplesCompras() {

        miSistema.registrarSala("Sala principal", 4);
        miSistema.registrarEvento("EV1", "Evento 1", 4, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");
        miSistema.registrarCliente("34567895", "Nicolas");
        miSistema.registrarCliente("34567896", "Antonella");

        miSistema.comprarEntrada("23456789", "EV1");
        miSistema.comprarEntrada("34567890", "EV1");
        miSistema.comprarEntrada("34567895", "EV1");
        miSistema.comprarEntrada("34567896", "EV1");

        Retorno r = miSistema.deshacerUtimasCompras(2);

        assertEquals("EV1-34567895#EV1-34567896", r.valorString);
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testDeshacerUltimasComprasFueronDevueltasAEventos() {

        miSistema.registrarSala("Sala principal", 4);
        miSistema.registrarEvento("EV1", "Evento 1", 4, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");
        miSistema.registrarCliente("34567895", "Nicolas");
        miSistema.registrarCliente("34567896", "Antonella");

        miSistema.comprarEntrada("23456789", "EV1");
        miSistema.comprarEntrada("34567890", "EV1");
        miSistema.comprarEntrada("34567895", "EV1");
        miSistema.comprarEntrada("34567896", "EV1");

        miSistema.deshacerUtimasCompras(2);

        Retorno r = miSistema.listarEventos();

        assertEquals("EV1-Evento 1-Sala principal-2-2", r.valorString);
    }

    @Test
    public void testAsignarEntradaAListaEsperaAlDeshacer() {
        miSistema.registrarSala("Sala principal", 2);
        miSistema.registrarEvento("EV1", "Evento 1", 2, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");
        miSistema.registrarCliente("34567895", "Nicolas");
        miSistema.registrarCliente("34567896", "Antonella");

        miSistema.comprarEntrada("23456789", "EV1");
        miSistema.comprarEntrada("34567890", "EV1");
        miSistema.comprarEntrada("34567895", "EV1");
        miSistema.comprarEntrada("34567896", "EV1");

        miSistema.deshacerUtimasCompras(1);

        Retorno r = miSistema.listarEsperaEvento();

        assertEquals("EV1-34567896", r.valorString);
    }

    @Test
    public void testAsignarEntradaAListaEsperaAlDeshacer_dejarListaEsperaVacia() {
        miSistema.registrarSala("Sala principal", 2);
        miSistema.registrarEvento("EV1", "Evento 1", 2, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");
        miSistema.registrarCliente("34567895", "Nicolas");
        miSistema.registrarCliente("34567896", "Antonella");

        miSistema.comprarEntrada("23456789", "EV1");
        miSistema.comprarEntrada("34567890", "EV1");
        miSistema.comprarEntrada("34567895", "EV1");
        miSistema.comprarEntrada("34567896", "EV1");

        miSistema.deshacerUtimasCompras(2);

        Retorno r = miSistema.listarEsperaEvento();

        assertEquals("", r.valorString);
    }

    @Test
    public void testEventoMejorPuntuado_SinPuntajes() {
        miSistema.registrarSala("Sala principal", 4);
        miSistema.registrarEvento("EV1", "Evento 1", 4, LocalDate.now());

        Retorno ret = miSistema.eventoMejorPuntuado();
        assertEquals("", ret.valorString);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

    }

    @Test
    public void testEventoMejorPuntuado_unEvento() {
        miSistema.registrarSala("Sala principal", 4);
        miSistema.registrarEvento("EV1", "Evento 1", 4, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");

        miSistema.calificarEvento("23456789", "EV1", 9, "Genial");
        miSistema.calificarEvento("34567890", "EV1", 8, "Muy bueno");

        Retorno ret = miSistema.eventoMejorPuntuado();

        assertEquals("EV1-8", ret.valorString);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testEventoMejorPuntuado_multiplesEventos() {
        miSistema.registrarSala("Sala principal", 4);
        miSistema.registrarEvento("EV1", "Evento 1", 4, LocalDate.now());

        miSistema.registrarSala("Sala grande", 8);
        miSistema.registrarEvento("EV2", "Evento 2", 8, LocalDate.now());

        miSistema.registrarSala("Sala mediana", 20);
        miSistema.registrarEvento("EV3", "Evento 3", 20, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");

        miSistema.registrarCliente("34567891", "Juan");
        miSistema.registrarCliente("34567892", "Antonella");

        miSistema.registrarCliente("14567891", "Lucas");
        miSistema.registrarCliente("14567892", "Romeo");

        miSistema.calificarEvento("23456789", "EV1", 9, "Genial");
        miSistema.calificarEvento("34567890", "EV1", 8, "Muy bueno");

        miSistema.calificarEvento("34567891", "EV2", 2, "Desastroso");
        miSistema.calificarEvento("34567892", "EV2", 3, "Preocupante");

        miSistema.calificarEvento("14567891", "EV3", 9, "Super");
        miSistema.calificarEvento("14567892", "EV3", 9, "El mejor");

        Retorno ret = miSistema.eventoMejorPuntuado();

        assertEquals("EV3-9", ret.valorString);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testEventoMejorPuntuado_multiplesEventosConMismaPuntuacion() {
        miSistema.registrarSala("Sala principal", 4);
        miSistema.registrarEvento("EV1", "Evento 1", 4, LocalDate.now());

        miSistema.registrarSala("Sala grande", 8);
        miSistema.registrarEvento("EV2", "Evento 2", 8, LocalDate.now());

        miSistema.registrarSala("Sala mediana", 20);
        miSistema.registrarEvento("EV3", "Evento 3", 20, LocalDate.now());

        miSistema.registrarCliente("23456789", "Gaston");
        miSistema.registrarCliente("34567890", "Michelle");

        miSistema.registrarCliente("34567891", "Juan");
        miSistema.registrarCliente("34567892", "Antonella");

        miSistema.registrarCliente("14567891", "Lucas");
        miSistema.registrarCliente("14567892", "Romeo");

        miSistema.calificarEvento("23456789", "EV1", 9, "Genial");
        miSistema.calificarEvento("34567890", "EV1", 9, "Muy bueno");

        miSistema.calificarEvento("34567891", "EV2", 2, "Desastroso");
        miSistema.calificarEvento("34567892", "EV2", 3, "Preocupante");

        miSistema.calificarEvento("14567891", "EV3", 9, "Super");
        miSistema.calificarEvento("14567892", "EV3", 9, "El mejor");

        Retorno ret = miSistema.eventoMejorPuntuado();

        assertEquals("EV1-9#EV3-9", ret.valorString);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testComprasXDia_sinCompras() {
        Retorno ret = miSistema.comprasXDia(6);
        assertEquals("", ret.valorString);
    }

    @Test
    public void testComprasXDia_valorMesInferior() {
        Retorno ret = miSistema.comprasXDia(0);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testComprasXDia_valorMesSuperior() {
        Retorno ret = miSistema.comprasXDia(13);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testComprasXDia_mesInvalido() {
        Retorno ret = miSistema.comprasXDia(6);
        assertEquals("", ret.valorString);
    }

    @Test
    public void testComprasXDia_unaFecha_ok() {
        miSistema.registrarCliente("12345678", "Cliente 1");
        miSistema.registrarSala("Sala 1", 100);

        miSistema.registrarEvento("EV2", "Evento 2", 50, LocalDate.of(2023, 6, 10));

        miSistema.comprarEntrada("12345678", "EV1");
        miSistema.comprarEntrada("12345678", "EV2");
        miSistema.comprarEntrada("12345678", "EV2");

        Retorno ret = miSistema.comprasXDia(6);
        assertEquals("10-2", ret.valorString);
    }

    @Test
    public void testComprasXDia_eventosConMismaFecha_ok() {
        miSistema.registrarCliente("12345678", "Pedro");
        miSistema.registrarCliente("11111111", "Juan");
        miSistema.registrarCliente("22222222", "Analia");
        miSistema.registrarCliente("33333333", "Michelle");

        miSistema.registrarSala("Sala 1", 100);
        miSistema.registrarSala("Sala 2", 50);

        miSistema.registrarEvento("EV1", "Evento 1", 50, LocalDate.of(2026, 6, 10));

        miSistema.registrarEvento("EV2", "Evento 2", 50, LocalDate.of(2026, 6, 10));

        miSistema.comprarEntrada("12345678", "EV2");
        miSistema.comprarEntrada("12345678", "EV2");
        miSistema.comprarEntrada("12345678", "EV2");

        miSistema.comprarEntrada("11111111", "EV1");
        miSistema.comprarEntrada("22222222", "EV1");
        miSistema.comprarEntrada("33333333", "EV1");

        Retorno ret = miSistema.comprasXDia(6);
        assertEquals("10-6", ret.valorString);
    }

    @Test
    public void testComprasXDia_eventosConDiferenteFecha_ok() {
        miSistema.registrarCliente("12345678", "Pedro");
        miSistema.registrarCliente("11111111", "Juan");
        miSistema.registrarCliente("22222222", "Analia");
        miSistema.registrarCliente("33333333", "Michelle");

        miSistema.registrarSala("Sala 1", 100);
        miSistema.registrarSala("Sala 2", 50);
        miSistema.registrarSala("Sala 3", 50);

        miSistema.registrarEvento("EV1", "Evento 1", 50, LocalDate.of(2026, 6, 10));

        miSistema.registrarEvento("EV2", "Evento 2", 50, LocalDate.of(2026, 6, 22));

        miSistema.registrarEvento("EV3", "Evento 3", 50, LocalDate.of(2026, 6, 24));

        miSistema.comprarEntrada("12345678", "EV2");
        miSistema.comprarEntrada("12345678", "EV2");

        miSistema.comprarEntrada("11111111", "EV1");
        miSistema.comprarEntrada("22222222", "EV1");
        miSistema.comprarEntrada("33333333", "EV1");

        miSistema.comprarEntrada("22222222", "EV3");

        Retorno ret = miSistema.comprasXDia(6);
        assertEquals("10-3#22-2#24-1", ret.valorString);
    }

    @Test
    public void testDevolverEntradaError1NoExisteCliente() {
        miSistema.registrarSala("Sala azul", 10);
        miSistema.registrarEvento("COD1", "Fiesta", 5, LocalDate.now());

        Retorno r = miSistema.devolverEntrada("11111111", "COD1");

        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testDevolverEntradaError2NoExisteEvento() {
        miSistema.registrarCliente("11111111", "Carly");

        Retorno r = miSistema.devolverEntrada("11111111", "COD1");

        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testDevolverEntradaOk() {
        miSistema.registrarSala("Sala azul", 10);
        miSistema.registrarEvento("COD1", "Fiesta", 5, LocalDate.now());
        miSistema.registrarCliente("11111111", "Carly");
        miSistema.comprarEntrada("11111111", "COD1");

        Retorno r = miSistema.devolverEntrada("11111111", "COD1");

        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

    @Test
    public void testDeshacerMasQueDisponibles() {
        miSistema.registrarSala("Sala azul", 10);
        miSistema.registrarEvento("COD1", "Fiesta", 5, LocalDate.now());
        miSistema.registrarCliente("11111111", "Carly");
        miSistema.registrarCliente("22222222", "Bling bling");
        miSistema.comprarEntrada("11111111", "COD1");
        miSistema.comprarEntrada("22222222", "COD1");
        
        Retorno r = miSistema.deshacerUtimasCompras(5);
        
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }
}
