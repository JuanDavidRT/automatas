/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;




public class AFDEntity extends AutomataFinito {  

    
    // constructores
    public AFDEntity(List<Character> sigma, List<String> states, String initialState, List<String> acceptanceStates, String[][] transition) {
        super(sigma, states, initialState, acceptanceStates, transition);
    }

    public AFDEntity(String nombreDeArchivo) {
        super(nombreDeArchivo);
    }

    // metodos que se encontraba antes en AFDController
    public boolean procesarCadena() {
        System.out.println("-------- AFDEntity procesar cadena --------");
        String cadena = "ababab";
        String buffer = this.initialState;
        boolean result = false;
        for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
            char read = cadena.charAt(i);
            buffer = procesarTransicion(this.transition, buffer, read);//procesa el caracter que está en la posicion de i del string
            result = false;           //reinicializo en false para cada iteración
            for (int j = 0; j < this.acceptanceStates.size(); j++) {
                if (buffer.equals(this.acceptanceStates.get(j))) {
                    result = true;                                // dice si es aceptado o no
                }
            }
        }

        return result;
    }
    
    public String procesarTransicion(String[][] transicion, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < this.states.size(); i++) {               //un loop para la cantidad de estados
            if (buffer.equals(this.states.get(i))) {               //si el estado actual buffer es igual a un estado en el automata ocntinua

                for (int j = 0; j < this.sigma.size(); j++) {      //loop para el alfabeto
                    if (read == this.sigma.get(j)) {                 //si el caracter es igual a la posición de sigma entra, 
                        bufferResult = transicion[i][j];    //teniendo en cuenta que en el array de transicion la primera columna son los estados
                        //y la segunda columna el alfabeto
                    }
                }
            }
        }

        return bufferResult;
    }

    public boolean procesarCadenaConDetalle() {
        String cadena = "ababab";
        String buffer = this.initialState;
        boolean result = false;
        for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
            char read = cadena.charAt(i);
            buffer = procesarTransicionConDetalle(this.transition, buffer, read);//procesa el caracter que está en la posicion de i del string
            result = false;           //reinicializo en false para cada iteración
            for (int j = 0; j < this.acceptanceStates.size(); j++) {
                if (buffer.equals(this.acceptanceStates.get(j))) {
                    result = true;                                // dice si es aceptado o no
                }
            }
        }

        return result;
    }
  
    public String procesarTransicionConDetalle(String[][] transicion, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < this.states.size(); i++) {               //un loop para la cantidad de estados
            if (buffer.equals(this.states.get(i))) {              //si el estado actual buffer es igual a un estado en el automata ocntinua
                for (int j = 0; j < this.sigma.size(); j++) {      //loop para el alfabeto
                    if (read == this.sigma.get(j)) {                 //si el caracter es igual a la posicioòn de sigma entra, 
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
            String buffer = this.initialState;
            String cadena = Lista[i];
            for (int j = 0; j < cadena.length(); j++) {  //un loop para leer la cadena de entrada
                char read = cadena.charAt(j);
                buffer = procesarTransicionConDetalle(this.transition, buffer, read);//procesa el caracter que está en la posicion de i del string
                result = false;           //reinicializo en false para cada iteración
                for (int k = 0; k < this.acceptanceStates.size(); k++) {
                    if (buffer.equals(this.acceptanceStates.get(k))) {
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
    
}
