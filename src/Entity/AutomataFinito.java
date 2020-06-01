
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
    
    
    // metodos (que se encontraban antes en AFDController)
    public boolean procesarCadena() {
        
        boolean result = false;
        
        return result;
    }
    
    public String procesarTransicion(String[][] transicion, String buffer, char read) {
        
        String bufferResult = new String();
      
        return bufferResult;
    }

    public boolean procesarCadenaConDetalle() {
        
        boolean result = false;

        return result;
    }
  
    public String procesarTransicionConDetalle(String[][] transicion, String buffer, char read) {
        
        String bufferResult = new String();
        
        return bufferResult;
    }
    
    public boolean procesarListaDeCadenas() {
       
        boolean result = false;

        return result;
    }
    
    


// fin de la clase
}