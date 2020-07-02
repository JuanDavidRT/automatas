/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crist
 */
public class Estado {

    public String nombre;
    public List<Transicion> Transiciones;

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public void AgregarTransicion(String caracter, Estado estadoDestino) {
        Transicion añadir = new Transicion(caracter,estadoDestino);
       
        Transiciones.add(añadir);

    }

    public List<Transicion> BuscarTransicion(String caracter) {
        List<Transicion> DevuelveTransiciones = new ArrayList<>();
        for (int i = 0; i < Transiciones.size(); i++) {
            if (Transiciones.get(i).Sigma.equals(caracter)) {
                DevuelveTransiciones.add(Transiciones.get(i));
            }
        }
        return DevuelveTransiciones;
    }

}
