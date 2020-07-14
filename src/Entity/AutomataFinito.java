package Entity;

import java.util.List;
import java.util.ArrayList;

public class AutomataFinito {

    // atributos de la clase padre principal
    public List<Character> sigma;
    public List<String> states;
    public String initialState;
    public List<String> acceptanceStates;
    public String[][][] transition;
    public ArrayList<String[]> tran2;

    // metodos
    // constructor básico
    public AutomataFinito(List<Character> sigma, List<String> states, String initialState, List<String> acceptanceStates, String transition[][][], ArrayList<String[]> tran2) {
        this.sigma = sigma;
        this.states = states;
        this.initialState = initialState;
        this.acceptanceStates = acceptanceStates;
        this.transition = transition;
        this.tran2 = tran2;
//        
    }

    public AutomataFinito() {

        sigma = new ArrayList<>();
        states = new ArrayList<>();
        initialState = new String();
        acceptanceStates = new ArrayList<>();
        transition = new String[states.size()][sigma.size()][1];
        tran2 = new ArrayList<String[]>();
    }
}
