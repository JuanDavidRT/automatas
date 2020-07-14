/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Controller.Estado;
import Controller.Transicion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crist
 */
public class AutomataAFN {

    public List<String> Alfabeto;
    public Estado EstadoInicial;
    public List<Estado> Estados;
    public List<Estado> EstadosAceptacion;

    public AutomataAFN(String estadoInicial) {
        EstadoInicial = new Estado(estadoInicial);

        Alfabeto = new ArrayList<>();
        Estados = new ArrayList<>();

        Estados.add(EstadoInicial);
        EstadosAceptacion = new ArrayList<>();
    }

    public AutomataAFN(List<String> alfabeto, Estado estado, List<Estado> estados, List<Estado> estadosAceptacion) {
        this.Alfabeto = alfabeto;
        this.EstadoInicial = estado;
        this.Estados = estados;
        this.EstadosAceptacion = estadosAceptacion;
    }

    public void AgregarEstado(String nombreEstado) {

        Estado existeEstado = new Estado(nombreEstado);
        if (!Estados.contains(existeEstado)) {
            Estados.add(existeEstado);
        }

    }

    public Estado GetEstadoByNombre(String nombreEstado) {

        Estado existeEstado = new Estado(nombreEstado);
        for (int i = 0; i < Estados.size(); i++) {
            if (Estados.get(i).nombre.equals(nombreEstado)) {
                return Estados.get(i);
            }

        }
        return null;

    }

    public Estado GetEstadoInicial() {
        return GetEstadoByNombre(EstadoInicial.nombre);
    }

    public void CrearAutomataAMano() {

        AgregarEstado("q0");
        AgregarEstado("q1");
        AgregarEstado("q2");

        Estado estadoQ0 = GetEstadoByNombre("q0");
        Estado estadoQ1 = GetEstadoByNombre("q1");
        Estado estadoQ2 = GetEstadoByNombre("q2");

        EstadosAceptacion.add(estadoQ1);

        estadoQ0.AgregarTransicion("a", estadoQ1);
        estadoQ1.AgregarTransicion("a", estadoQ1);
        estadoQ1.AgregarTransicion("a", estadoQ2);
        estadoQ2.AgregarTransicion("b", estadoQ1);
        estadoQ1.AgregarTransicion("a", estadoQ1);

    }

    public boolean procesarCadena(String Cadena, Estado estadoActual) {
        if (Cadena.length() == 0) {
            for (int i = 0; i < EstadosAceptacion.size(); i++) {
                if (EstadosAceptacion.get(i) == estadoActual) {
                    return true;
                }
            }
        } else {
            String caracterEvaluar = Cadena.substring(0, 1);
            String cadenaRestante = Cadena.substring(1, Cadena.length());

            //Si la cadena que se recibe por parmaetro es de longitud 1 al buscar la transicion se debe evaluar si el estado destino es valido, retorna true, sino false.
            List<Transicion> transicionEncontrada = estadoActual.BuscarTransicion(caracterEvaluar);
            if (transicionEncontrada == null || transicionEncontrada.isEmpty()) {
                return false;
            }

            for (int j = 0; j < transicionEncontrada.size(); j++) {
                boolean respuesta = procesarCadena(cadenaRestante, transicionEncontrada.get(j).EstadosDestino);
                if (respuesta) {
                    return true;
                }
            }

        }
        return false;

    }
    
}
