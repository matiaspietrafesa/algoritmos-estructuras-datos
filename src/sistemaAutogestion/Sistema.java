package sistemaAutogestion;

import dominio.Calificacion;
import java.time.LocalDate;
import tads.ListaNodos;
import dominio.Evento;
import dominio.Cliente;
import dominio.Entrada;
import dominio.Sala;
import tads.Pila;

public class Sistema implements IObligatorio {

    private ListaNodos<Sala> listaSalas;
    private ListaNodos<Evento> listaEventos;
    private ListaNodos<Cliente> listaClientes;
    private Pila<Entrada> pilaEntradas;

    public Sistema() {
        listaSalas = new ListaNodos();
        listaEventos = new ListaNodos();
        listaClientes = new ListaNodos();
        pilaEntradas = new Pila();
    }

    @Override
    //Post Condición: Se inicializan las estructuras: listaSalas, listaEventos, listaClientes, el sistema queda listo con las listas vacías.
    public Retorno crearSistemaDeGestion() {
        listaSalas = new ListaNodos();
        listaEventos = new ListaNodos();
        listaClientes = new ListaNodos();
        pilaEntradas = new Pila();
        return Retorno.ok();
    }

    //Pre Condición: Sistema inicializado, 'nombre' no debe ser null y 'capacidad' debe ser entero positivo
    //Post Condición: Si 'capacidad <= 0' retorna error2(), si la sala es valida y no existe previamente la sala se agrega al principio y retorna Retorno.ok().
    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        if (capacidad <= 0) {
            return Retorno.error2();
        }

        Sala sala = new Sala(capacidad, nombre);
        if (listaSalas.existeElemento(sala)) {
            return Retorno.error1();
        }

        listaSalas.agregarInicio(sala);
        return Retorno.ok();
    }

    //Pre Condición: Sistema inicializado, 'nombre' no null ni vacio.
    //Post Condición: Si no existe sala con ese nombre retorna Retorno.error1(), y no modifica. Si existe la elimina y retorna Retorno.ok()
    @Override
    public Retorno eliminarSala(String nombre) {
        Sala sala = new Sala(nombre);
        if (!listaSalas.existeElemento(sala)) {
            return Retorno.error1();
        }

        // eliminar sala
        int i = 0;
        while (i < listaSalas.cantidadElementos() && listaSalas.obtenerElemento(i).getNombre().compareTo(nombre) != 0) {
            i++;
        }
        listaSalas.eliminarEnPos(i);
        return Retorno.ok();
    }

    //Pre Condición: Sistema inicializado,`codigo` no debe ser null y debe ser único, `descripcion` no debe ser null, `aforoNecesario` debe ser un entero positivo,
    //`fecha` debe ser una LocalDate válida y no null. Debe existir al menos una sala que Capacidad >= `aforoNecesario` y esté disponible en la `fecha` especificada.
    //Post Condición: Si el evento ya existe retorna Retorno.error1() y no modifica, si `aforoNecesario` <= 0 retorna Retorno.error2() y no modifica, 
    //si no se encuentra sala adecuada, retorna Retorno.error3() y no modifica. Si el registro es exitoso el evento se agrega a la listaEventos ordenado, la sala pasa a estar ocupada en 
    //la fecha y retorna Retorno.ok().
    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        Evento evento = new Evento(codigo, descripcion, aforoNecesario, fecha);

        if (listaEventos.existeElemento(evento)) {
            return Retorno.error1();
        }
        if (aforoNecesario <= 0) {
            return Retorno.error2();
        }

        boolean existeSala = false;
        int i = 0;
        while (!existeSala && i < listaSalas.cantidadElementos()) {
            Sala sala = listaSalas.obtenerElemento(i);
            if (sala.getCapacidad() >= aforoNecesario) {
                ListaNodos<LocalDate> fechasOcupada = sala.getFechasOcupada();
                if (!fechasOcupada.existeElemento(fecha)) {
                    existeSala = true;
                    evento.setSala(sala);
                    sala.getFechasOcupada().agregarOrdenado(fecha);
                }
            }
            i++;
        }

        if (!existeSala) {
            return Retorno.error3();
        }

        listaEventos.agregarOrdenado(evento);
        return Retorno.ok();
    }

    //Pre Condición: Sistema inicializado, 'cedula' string no null y no registrada en el sistema, 'nombre' string no null.
    //Post Condición: Si cedula no tiene 8 caracteres retorna Retorno.error1() y no modifica ,si la cédula ya existe en el sistema, retorna Retorno.error2() y no modifica,
    //si el registro es exitoso el cliente se agrega a la listaClientes ordenado y retorna Retrono.ok().
    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        if (cedula.length() != 8) {
            return Retorno.error1();
        }

        Cliente cliente = new Cliente(cedula, nombre);
        if (listaClientes.existeElemento(cliente)) {
            return Retorno.error2();
        }

        listaClientes.agregarOrdenado(cliente);
        return Retorno.ok();
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        Cliente c = new Cliente(cedula, "");
        if (!listaClientes.existeElemento(c)) {
            return Retorno.error1();
        }

        Evento e = new Evento(codigoEvento);
        if (!listaEventos.existeElemento(e)) {
            return Retorno.error2();
        }

        e = null;
        int i = 0;
        while (e == null) {
            Evento eventoAux = listaEventos.obtenerElemento(i);
            if (eventoAux.getCodigo().compareTo(codigoEvento) == 0) {
                e = eventoAux;
            }
            i++;
        }

        if (e.getEntradas().cantidadElementos() < e.getSala().getCapacidad()) {
            Cliente cliente = null;
            int j = 0;
            while (cliente == null) {
                Cliente aux = listaClientes.obtenerElemento(j);
                if (aux.getCedula().compareTo(cedula) == 0) {
                    cliente = aux;
                } else {
                    j++;
                }
            }
            Entrada entrada = new Entrada(cliente, e);
            e.getEntradas().agregarFinal(entrada);
            cliente.getCompras().agregarFinal(entrada);

            pilaEntradas.push(entrada);
        } else {
            e.getListaEspera().encolar(c);
        }

        return Retorno.ok();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        Evento e = new Evento(codigo);
        if (!listaEventos.existeElemento(e)) {
            return Retorno.error1();
        }

        e = null;
        int i = 0;
        while (e == null) {
            Evento eventoAux = listaEventos.obtenerElemento(i);
            if (eventoAux.getCodigo().compareTo(codigo) == 0) {
                e = eventoAux;
            } else {
                i++;
            }
        }

        if (e.getEntradas().cantidadElementos() > 0) {
            return Retorno.error2();
        }

        LocalDate fechaEvento = e.getFecha();
        Sala s = e.getSala();
        boolean fechaEliminada = false;
        for (int j = 0; j < s.getFechasOcupada().cantidadElementos() && !fechaEliminada; j++) {
            LocalDate fecha = s.getFechasOcupada().obtenerElemento(j);
            if (fecha.compareTo(fechaEvento) == 0) {
                s.getFechasOcupada().eliminarEnPos(j);
                fechaEliminada = true;
            }
            j++;
        }
        listaEventos.eliminarEnPos(i);

        return Retorno.ok();
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        Cliente c = new Cliente(cedula, "");
        if (!listaClientes.existeElemento(c)) {
            return Retorno.error1();
        }

        Evento e = new Evento(codigoEvento);
        if (!listaEventos.existeElemento(e)) {
            return Retorno.error2();
        }

        e = null;
        int i = 0;
        while (e == null) {
            Evento eventoAux = listaEventos.obtenerElemento(i);
            if (eventoAux.getCodigo().compareTo(codigoEvento) == 0) {
                e = eventoAux;
            }
            i++;
        }

        c = null;
        int g = 0;
        while (c == null) {
            Cliente aux = listaClientes.obtenerElemento(g);
            if (aux.getCedula().equals(cedula)) {
                c = aux;
            } else {
                g++;
            }
        }
        
        ListaNodos<Entrada> entradas = e.getEntradas();
        Entrada entrada = new Entrada(c, e);
        boolean devuelta = false;
        int j = 0;
        while (!devuelta && j < entradas.cantidadElementos()) {
            Entrada aux = entradas.obtenerElemento(j);
            if (entrada.compareTo(aux) == 0) {
                aux.setFueDevuelta(true);
                devuelta = true;
            } else {
                j++;
            }
        }
        entradas.eliminarEnPos(j);

        if (devuelta && e.getListaEspera().cantidad() > 0) {
            Entrada entradaNueva = new Entrada(e.getListaEspera().frente(), e);
            entradas.agregarFinal(entradaNueva);
            pilaEntradas.push(entradaNueva);
            e.getListaEspera().desencolar();
        }

        return Retorno.ok();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        Cliente c = new Cliente(cedula, "");
        if (!listaClientes.existeElemento(c)) {
            return Retorno.error1();
        }

        Evento e = new Evento(codigoEvento);
        if (!listaEventos.existeElemento(e)) {
            return Retorno.error2();
        }

        if (puntaje < 1 || puntaje > 10) {
            return Retorno.error3();
        }

        e = null;
        int i = 0;
        while (e == null) {
            Evento eventoAux = listaEventos.obtenerElemento(i);
            if (eventoAux.getCodigo().compareTo(codigoEvento) == 0) {
                e = eventoAux;
            }
            i++;
        }

        Calificacion rating = new Calificacion(cedula, codigoEvento, puntaje, comentario);

        if (e.getCalificaciones().existeElemento(rating)) {
            return Retorno.error4();
        }

        e.getCalificaciones().agregarFinal(rating);

        return Retorno.ok();
    }

    //Pre Condición: Sistema inicializado y la listaSalas no debe ser null. 
    //Post Condición: Si no hay salas retorna un string vacio, si es satisfactorio muestra el texto de todas las salas (según su método toString()) y las separa por '#'.
    //El orden del listado es en orden inverso al registro.
    @Override
    public Retorno listarSalas() {
        String mensajeMostrado = "";
        for (int i = 0; i < listaSalas.cantidadElementos(); i++) {
            Sala sala = listaSalas.obtenerElemento(i);
            mensajeMostrado += sala.toString();
            if (i < listaSalas.cantidadElementos() - 1) {
                mensajeMostrado += "#";
            }
        }
        return Retorno.ok(mensajeMostrado);
    }

    //Pre Condición: Sistema inicializado y la listaEventos no debe ser null. 
    //Post Condición: Si no hay eventos retorna un string vacio, si es satisfactorio muestra el texto de todos los eventos (según su método toString()) y los separa por '#'.
    //El orden del listado es alfabéticamente por el código del evento.
    @Override
    public Retorno listarEventos() {
        String mensajeMostrado = "";
        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento evento = listaEventos.obtenerElemento(i);
            mensajeMostrado += evento.toString();
            if (i < listaEventos.cantidadElementos() - 1) {
                mensajeMostrado += "#";
            }
        }
        return Retorno.ok(mensajeMostrado);
    }

    //Pre Condición: Sistema inicializado y la listaClientes no debe ser null. 
    //Post Condición: Si no hay clientes retorna un string vacio, si es satisfactorio muestra el texto de todos los clientes (según su método toString()) y los separa por '#'.
    //El orden del listado es por cédula de identidad.
    @Override
    public Retorno listarClientes() {
        String mensajeMostrado = "";
        for (int i = 0; i < listaClientes.cantidadElementos(); i++) {
            Cliente cliente = listaClientes.obtenerElemento(i);
            mensajeMostrado += cliente.toString();
            if (i < listaClientes.cantidadElementos() - 1) {
                mensajeMostrado += "#";
            }
        }
        return Retorno.ok(mensajeMostrado);
    }

    //Pre Condición: vistaSala debe ser una matriz no vacía de Strings. Cada elemento de la matriz debe ser "O" (ocupado) o "X" (libre) y los límites son "#".
    //Post Condición: Retorna Retorno.error1() si la matriz está vacía o tiene dimensiones inválidas. Si cumple con al menos dos columnas en donde la cantidad
    //de asientos ocupados consecutivos de dichas columnas superan a los asientos libres retorna Retorno.ok("Es óptimo") y si no retorna Retorno.ok("No es óptimo").
    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        int columnasOptimas = 0;

        // Si no hay columnas, retornar false inmediatamente
        if (vistaSala.length == 0 || vistaSala[0].length == 0) {
            return Retorno.error1();
        }

        for (int col = 0; col < vistaSala[0].length && columnasOptimas < 2; col++) {
            int asientosLibres = 0;
            int maxAsientosOcupadosSeguidas = 0;
            int asientosOcupadosSeguidas = 0;

            for (int fila = 0; fila < vistaSala.length; fila++) {
                String asiento = vistaSala[fila][col];

                if (asiento.equals("O")) {
                    asientosOcupadosSeguidas++;
                    if (asientosOcupadosSeguidas > maxAsientosOcupadosSeguidas) {
                        maxAsientosOcupadosSeguidas = asientosOcupadosSeguidas;
                    }
                } else if (asiento.equals("X")) {
                    asientosLibres++;
                    asientosOcupadosSeguidas = 0;
                } else {
                    asientosOcupadosSeguidas = 0;
                }
            }
            if (maxAsientosOcupadosSeguidas > asientosLibres) {
                columnasOptimas++;
            }
        }

        if (columnasOptimas >= 2) {
            return Retorno.ok("Es óptimo");
        } else {
            return Retorno.ok("No es óptimo");
        }

    }

    @Override
    public Retorno listarClientesDeEvento(String codigo, int n) {
        Evento e = new Evento(codigo);
        if (!listaEventos.existeElemento(e)) {
            return Retorno.error1();
        }

        if (n < 1) {
            return Retorno.error2();
        }

        e = null;
        int i = 0;
        while (e == null) {
            Evento eventoAux = listaEventos.obtenerElemento(i);
            if (eventoAux.getCodigo().compareTo(codigo) == 0) {
                e = eventoAux;
            }
            i++;
        }

        String mensajeMostrado = "";
        for (int j = 0; j < e.getEntradas().cantidadElementos() && j < n; j++) {
            Cliente clie = e.getEntradas().obtenerElemento(j).getCliente();
            mensajeMostrado += clie.toString();
            if (j < e.getEntradas().cantidadElementos() - 1) {
                mensajeMostrado += "#";
            }
        }

        return Retorno.ok(mensajeMostrado);
    }

    @Override
    public Retorno listarEsperaEvento() {
        String mensaje = new String();
        boolean primerEvento = true;

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);

            if (!e.getListaEspera().esVacia() && e.getListaEspera() != null) {
                ListaNodos<Cliente> clientesEnEspera = new ListaNodos<>();

                while (!e.getListaEspera().esVacia()) {

                    Cliente cliente = e.getListaEspera().frente();
                    clientesEnEspera.agregarFinal(cliente);
                    e.getListaEspera().desencolar();

                }

                // en este for se encolan los clientes ordenados
                // cambiando el orden de la cola
                for (int j = 0; j < clientesEnEspera.cantidadElementos(); j++) {
                    e.getListaEspera().encolar(clientesEnEspera.obtenerElemento(j));
                }

                ListaNodos listaOrdenada = new ListaNodos<Cliente>();

                for (int j = 0; j < clientesEnEspera.cantidadElementos(); j++) {
                    listaOrdenada.agregarOrdenado(clientesEnEspera.obtenerElemento(j));
                }

                if (!primerEvento && listaOrdenada.cantidadElementos() > 0) {
                    mensaje += "#";
                }
                for (int k = 0; k < listaOrdenada.cantidadElementos(); k++) {
                    Cliente cli = (Cliente) listaOrdenada.obtenerElemento(k);
                    mensaje += (e.getCodigo() + "-" + cli.getCedula());

                    if (k < listaOrdenada.cantidadElementos() - 1) {
                        mensaje += "#";
                    }
                }
                primerEvento = false;

            }
        }
        return Retorno.ok(mensaje);
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {

        Pila<Entrada> pilaAuxiliar = new Pila<>();
        ListaNodos<Entrada> entradasDeshechas = new ListaNodos<>();
        String mensaje = "";

        for (int i = 0; i < n; i++) {
            if (!pilaEntradas.esVacia()) {
                Entrada entrada = pilaEntradas.top();
                pilaAuxiliar.push(entrada);
                pilaEntradas.pop();
            }
        }

        while (!pilaAuxiliar.esVacia()) {
            Entrada entrada = pilaAuxiliar.top();
            entradasDeshechas.agregarOrdenado(entrada);
            pilaAuxiliar.pop();
        }

        for (int i = 0; i < entradasDeshechas.cantidadElementos(); i++) {
            procesarEntradaADeshacer(entradasDeshechas.obtenerElemento(i));
        }

        for (int k = 0; k < entradasDeshechas.cantidadElementos(); k++) {
            Entrada e = entradasDeshechas.obtenerElemento(k);
            mensaje += (e.getCodigoEvento() + "-" + e.getCedula());
            if (k < entradasDeshechas.cantidadElementos() - 1) {
                mensaje += "#";
            }
        }

        return Retorno.ok(mensaje);
    }

    private void procesarEntradaADeshacer(Entrada entrada) {

        Evento evento = entrada.getEvento();
        if (evento == null) {
            return;
        }

        Cliente cliente = entrada.getCliente();
        if (cliente == null) {
            return;
        }

        entrada.setFueDevuelta(true);

        boolean eliminadaDeEvento = false;
        for (int i = 0; i < evento.getEntradas().cantidadElementos() && !eliminadaDeEvento; i++) {
            if (evento.getEntradas().obtenerElemento(i).equals(entrada)) {
                evento.getEntradas().eliminarEnPos(i);
                eliminadaDeEvento = true;
            }
        }

        boolean eliminadaDeCliente = false;
        for (int i = 0; i < cliente.getCompras().cantidadElementos() && !eliminadaDeCliente; i++) {
            if (cliente.getCompras().obtenerElemento(i).equals(entrada)) {
                cliente.getCompras().eliminarEnPos(i);
                eliminadaDeCliente = true;
            }
        }

        //. Si hay lista de espera, asignar al primero
        if (!evento.getListaEspera().esVacia()) {
            asignarEntradaANuevoCliente(evento);
        }
    }

    private void asignarEntradaANuevoCliente(Evento evento) {
        if (evento.getListaEspera().esVacia()) {
            return;
        }

        Cliente cliente = evento.getListaEspera().frente();
        evento.getListaEspera().desencolar();

        Entrada nuevaEntrada = new Entrada(cliente, evento);

        cliente.getCompras().agregarOrdenado(nuevaEntrada);

        evento.getEntradas().agregarOrdenado(nuevaEntrada);

        pilaEntradas.push(nuevaEntrada);
    }

    @Override
    public Retorno eventoMejorPuntuado() {

        ListaNodos<Evento> eventosConMejorPuntaje = new ListaNodos();
        String mensaje = "";

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getPuntajePromedio() > 0) {
                if (eventosConMejorPuntaje.esVacia()) {
                    eventosConMejorPuntaje.agregarOrdenado(e);
                } else {
                    if (e.getPuntajePromedio() > eventosConMejorPuntaje.obtenerElemento(0).getPuntajePromedio()) {
                        eventosConMejorPuntaje.vaciar();
                        eventosConMejorPuntaje.agregarOrdenado(e);
                    } else if (e.getPuntajePromedio() == eventosConMejorPuntaje.obtenerElemento(0).getPuntajePromedio()) {
                        eventosConMejorPuntaje.agregarOrdenado(e);
                    }
                }
            }
        }

        for (int k = 0; k < eventosConMejorPuntaje.cantidadElementos(); k++) {
            Evento e = eventosConMejorPuntaje.obtenerElemento(k);
            mensaje += (e.getCodigo() + "-" + Math.round(e.getPuntajePromedio()));
            if (k < eventosConMejorPuntaje.cantidadElementos() - 1) {
                mensaje += "#";
            }
        }

        return Retorno.ok(mensaje);
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        Cliente c = new Cliente(cedula, "");
        if (!listaClientes.existeElemento(c)) {
            return Retorno.error1();
        }

        c = null;
        int i = 0;
        while (c == null) {
            Cliente aux = listaClientes.obtenerElemento(i);
            if (aux.getCedula().compareTo(cedula) == 0) {
                c = aux;
            } else {
                i++;
            }
        }

        ListaNodos<Entrada> entradas = c.getCompras();

        String mensaje = "";
        for (int j = 0; j < entradas.cantidadElementos(); j++) {
            mensaje += entradas.obtenerElemento(i).toString();
            if (j < entradas.cantidadElementos() - 1) {
                mensaje += "#";
            }
        }

        return Retorno.ok(mensaje);
    }

    @Override
    public Retorno comprasXDia(int mes) {
        if (mes < 1 || mes > 12) {
            return Retorno.error1();
        }

        int[] contador = new int[31];

        for (int i = 0; i < listaClientes.cantidadElementos(); i++) {
            Cliente cliente = listaClientes.obtenerElemento(i);
            ListaNodos<Entrada> compras = cliente.getCompras();

            for (int j = 0; j < compras.cantidadElementos(); j++) {
                Entrada entrada = compras.obtenerElemento(j);
                Evento evento = entrada.getEvento();

                if (evento != null) {
                    LocalDate fechaEvento = evento.getFecha();
                    if (fechaEvento.getMonthValue() == mes) {
                        int dia = fechaEvento.getDayOfMonth();

                        contador[dia - 1] += 1;
                    }
                }
            }
        }

        String mensaje = "";
        for (int g = 0; g < contador.length; g++) {
            if (contador[g] > 0) {
                mensaje += g + 1 + "-" + contador[g] + "#";
            }
        }
        if (!mensaje.equals("")) {
            mensaje = mensaje.substring(0, mensaje.length() - 1);
        }
        return Retorno.ok(mensaje);
    }
}
