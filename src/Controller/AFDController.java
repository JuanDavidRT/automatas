/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AutomataFinito;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Dentro del modelo MVC las clases de este paquete se 
 * encargan de manejar las interacciones entre
 *      View (que se encaga de manejar las interacciones con el usuario)
 *                              y
 *      Entity (Model en MVC, que maneja las entidades, objetos, atributos y metodos)
 */

public class AFDController {

    AutomataFinito automataFinito;
    int countf=0;
    int contc=0;
    List<String> lineasArchivo = new ArrayList<>();

    public AFDController(){
    }
    
     public AFDController(AutomataFinito automata){
    automataFinito=automata;
    }
    
    //metodo para crear un AFDEntity HARCODED
    public void crearAutomata(List<Character> sigma, List<String> states, String initialState, List<String> acceptanceStates, String transition[][] ){
    
        
        //
       transition = new String[3][2];

        // inicializa
        sigma.add('a');
        sigma.add('b');
        initialState = "q0";
        acceptanceStates.add("q0");
        acceptanceStates.add("q2");
        
        for (int i = 0; i < 3; i++) {
            states.add ( "q" + i);
        }

        transition[0][0] = "q0";
        transition[0][1] = "q1";
        transition[1][0] = "q1";
        transition[1][1] = "q2";
        transition[2][0] = "q1";
        transition[2][1] = "q1";

        automataFinito = new AutomataFinito(sigma, states, initialState, acceptanceStates, transition);
        
    }
    
    // metodos que se encontraba antes en AFDController
    public boolean procesarCadena(String cadena) {
        System.out.println("-------- AFDEntity procesar cadena --------");
         
        String buffer = automataFinito.initialState;
        boolean result = false;
        for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
            char read = cadena.charAt(i);
            buffer = procesarTransicion(automataFinito.transition, buffer, read);//procesa el caracter que está en la posicion de i del string
            result = false;           //reinicializo en false para cada iteración
            for (int j = 0; j < automataFinito.acceptanceStates.size(); j++) {
                if (buffer.equals(automataFinito.acceptanceStates.get(j))) {
                    result = true;                                // dice si es aceptado o no
                }
            }
        }

        return result;
    }
    
    public String procesarTransicion(String[][] transicion, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < automataFinito.states.size(); i++) {               //un loop para la cantidad de estados
            if (buffer.equals(automataFinito.states.get(i))) {               //si el estado actual buffer es igual a un estado en el automata ocntinua

                for (int j = 0; j < automataFinito.sigma.size(); j++) {      //loop para el alfabeto
                    if (read == automataFinito.sigma.get(j)) {                 //si el caracter es igual a la posición de sigma entra, 
                        bufferResult = transicion[i][j];    //teniendo en cuenta que en el array de transicion la primera columna son los estados
                        //y la segunda columna el alfabeto
                    }
                }
            }
        }

        return bufferResult;
    }

    public boolean procesarCadenaConDetalle(String cadena) {
        
        String buffer = automataFinito.initialState;
        boolean result = false;
        for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
            char read = cadena.charAt(i);
            buffer = procesarTransicionConDetalle(automataFinito.transition, buffer, read);//procesa el caracter que está en la posicion de i del string
            result = false;           //reinicializo en false para cada iteración
            for (int j = 0; j < automataFinito.acceptanceStates.size(); j++) {
                if (buffer.equals(automataFinito.acceptanceStates.get(j))) {
                    result = true;                                // dice si es aceptado o no
                }
            }
        }

        return result;
    }
  
    public String procesarTransicionConDetalle(String[][] transicion, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < automataFinito.states.size(); i++) {               //un loop para la cantidad de estados
            if (buffer.equals(automataFinito.states.get(i))) {              //si el estado actual buffer es igual a un estado en el automata ocntinua
                for (int j = 0; j < automataFinito.sigma.size(); j++) {      //loop para el alfabeto
                    if (read == automataFinito.sigma.get(j)) {                 //si el caracter es igual a la posicioòn de sigma entra, 
                        bufferResult = transicion[i][j];    // aqui parece que se está desbordando en rl indice   teniendo en cuenta que en el array de transicion la primera columa son los estados
                        //y la segunda columna los posibles caracteres
                    }
                }
            }
        }
        System.out.println("En el estado " + buffer + " Lee " + read + " y pasa al estado " + bufferResult);
        return bufferResult;
    }
    
    public boolean procesarListaDeCadenas() {
        String Lista[] = new String[5];
        Lista[0] = "ababab";
        Lista[1] = "babab";
        Lista[2] = "aabab";
        Lista[3] = "abbab";
        Lista[4] = "ababb";
        
        boolean result = false;

        for (int i = 0; i < Lista.length; i++) {
            String buffer = automataFinito.initialState;
            String cadena = Lista[i];
            for (int j = 0; j < cadena.length(); j++) {  //un loop para leer la cadena de entrada
                char read = cadena.charAt(j);
                buffer = procesarTransicionConDetalle(automataFinito.transition, buffer, read);//procesa el caracter que está en la posicion de i del string
                result = false;           //reinicializo en false para cada iteración
                for (int k = 0; k < automataFinito.acceptanceStates.size(); k++) {
                    if (buffer.equals(automataFinito.acceptanceStates.get(k))) {
                        result = true;                                // dice si es aceptado o no
                    }
                }
            }
            int ot = i + 1;
        if (result) {
            
            System.out.println("La cadena " + ot + " es aceptada");
            System.out.println();
        } else {
            System.out.println("La cadena " + ot + " NO es aceptada");
            System.out.println();
        }// dice si es aceptado o no
        
        }

        return result;
    }  
    
    
    
   
    
    public void generarSigma(String sigma){
        String alfabetoA=sigma;
        String alfabetoB="0-9";
        String alfabetoC="a-m";
        String type=new String();
        
        for(int i=0; i<alfabetoA.length();i++){
            if(alfabetoA.charAt(i)==','){
                type="lista";
            }else if(alfabetoA.charAt(i)=='-'){
                type="intervalo";
            }      
        }
        if(type.equals("lista")){
            automataFinito.sigma.add(alfabetoA.charAt(0));     // LOS ARCHIVOS ACEPTADOS NO TIENEN LISTA DE
            System.out.println(automataFinito.sigma.get(0));   // CARACTERES EN UNA SOLA LINEA
            automataFinito.sigma.add(alfabetoA.charAt(2)); 
            System.out.println(automataFinito.sigma.get(1));
        }
        else if(type.equals("intervalo")){
            int a=(int)alfabetoA.charAt(0);
            int b=(int)alfabetoA.charAt(2);
            int i=0;
            for(int x=a;x<=b;x++){
                
                automataFinito.sigma.add((char)x);
                System.out.println(automataFinito.sigma.get(i));
                i++;
            }
            
        }
         
    }
    


// fin de Clase
}
