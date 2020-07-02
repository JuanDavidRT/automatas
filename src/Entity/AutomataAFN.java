/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Controller.Estado;
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
            Estados.add(new Estado(nombreEstado));
        }

    }

    public Estado GetEstadoByNombre(String nombreEstado) {

        Estado existeEstado = new Estado(nombreEstado);
        for (int i = 0; i < Estados.size(); i++) {
            if (Estados.get(i) == existeEstado) {
                return Estados.get(i);
            }

        }
        return null;
    }

    public Estado GetEstadoInicial() {
        return GetEstadoByNombre(EstadoInicial.nombre);
    }

}
