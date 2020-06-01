
package Entity;

import java.util.List;


public class AutomataFinito {
    // atributos de la clase padre principal
    public List<Character> sigma;
    public List<String> states;
    public String initialState;  
    public List<String>  acceptanceStates;    
    public String[][] transition;
    
    
    // metodos
    // constructor básico
    public AutomataFinito(List<Character> sigma, List<String> states, String initialState, List<String> acceptanceStates, String transition[][] ){
        this.sigma = sigma;
        this.states=states;
        this.initialState= initialState;
        this.acceptanceStates = acceptanceStates;
        this.transition = transition;
        
    }
    
    // constructor desde archivo
    public AutomataFinito(String nombreDeArchivo){
    
        
        
    }
    public AutomataFinito(){
    
        
        
    }
    
    
   

// fin de la clase
}