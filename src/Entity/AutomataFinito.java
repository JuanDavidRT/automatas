/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author crist
 */
public class AutomataFinito {
    public char sigma[];
    public String states[];
    public String initialState;  
    public String acceptanceStates[];    
    public String transition[][];
    
    public AutomataFinito(char sigma[], String states[], String initialState, String acceptanceStates[], String transition[][] ){
        this.sigma = sigma;
        this.states=states;
        this.initialState= initialState;
        this.acceptanceStates = acceptanceStates;
        this.transition = transition;
        
    }
    
    public boolean  procesarCadena(String cadena){
        
        return false;
    }
}


