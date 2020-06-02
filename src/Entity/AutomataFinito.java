
package Entity;

import java.util.List;
import Controller.ArchivoController;
import java.util.ArrayList;

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
    
   
  
    public AutomataFinito(){
    
        sigma= new ArrayList<>();
        states= new ArrayList<>();
        initialState= new String();  
        acceptanceStates= new ArrayList<>();    
        transition= new String[3][2];
    }
    
    
   

// fin de la clase
}