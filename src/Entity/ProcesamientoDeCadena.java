/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;

/**
 *
 * @author andre
 */
public class ProcesamientoDeCadena {
    
    // Atributos de la clase padre
    public AutomataFinito automata;                // el automata que realiza el procedimiento
    public String cadena;                          // la cadena que se procesa
    public Boolean esAceptada;
    public List<String> listaDePasos;

    
    
    
  
    
    public void setAutomata(AutomataFinito automata) {
        this.automata = automata;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public void setEsAceptada(Boolean esAceptada) {
        this.esAceptada = esAceptada;
    }

    public void setListaDePasos(List<String> listaDePasos) {
        this.listaDePasos = listaDePasos;
    }
    
    

    public AutomataFinito getAutomata() {
        return automata;
    }

    public String getCadena() {
        return cadena;
    }

    public Boolean getEsAceptada() {
        return esAceptada;
    }

    public List<String> getListaDePasos() {
        return listaDePasos;
    }
    
    
    
    
}
