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
import java.util.Scanner;

public class AFDController {

    AutomataFinito automataFinito;
    int countf = 0;
    int contc = 0;
    List<String> lineasArchivo = new ArrayList<>();
    Scanner sn = new Scanner(System.in);

    public AFDController() {
        automataFinito = new AutomataFinito();
    }

    public AFDController(AutomataFinito automata) {
        automataFinito = automata;
    }

    //metodo para crear un AFDEntity HARCODED
    public void crearAutomata() {

        // inicializa
        String read = new String();
        int quantity;
        System.out.println("Ingrese el alfabeto");
        read = sn.next();
        generarSigma(read);

        System.out.println("Ingrese el estado inicial");
        read = sn.next();
        automataFinito.initialState = read;

        System.out.println("Ingrese la cantidad de estados de aceptación");
        quantity = sn.nextInt();
        for (int i = 0; i < quantity; i++) {
            int x = i + 1;
            System.out.println("Ingrese el estado de aceptación " + x);
            read = sn.next();
            automataFinito.acceptanceStates.add(read);
        }

        System.out.println("Ingrese la cantidad de estados");
        quantity = sn.nextInt();
        for (int i = 0; i < quantity; i++) {
            automataFinito.states.add("q" + i);
        }
        automataFinito.transition = new String[automataFinito.states.size()][automataFinito.sigma.size()][1];

        System.out.println("Ingrese la función de transición");
        for (int i = 0; i < automataFinito.states.size(); i++) {
            for (int j = 0; j < automataFinito.sigma.size(); j++) {
                System.out.println("Ingrese la transición del estado " + automataFinito.states.get(i) + " cuando lee " + automataFinito.sigma.get(j));
                read = sn.next();
                automataFinito.transition[i][j][0] = read;
            }
        }
        System.out.println("si me leeeeee");
    }

    // metodos para procesar cadenas
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

    public String procesarTransicion(String[][][] transicion, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < automataFinito.states.size(); i++) {               //un loop para la cantidad de estados
            if (buffer.equals(automataFinito.states.get(i))) {               //si el estado actual buffer es igual a un estado en el automata ocntinua

                for (int j = 0; j < automataFinito.sigma.size(); j++) {      //loop para el alfabeto
                    if (read == automataFinito.sigma.get(j)) {                 //si el caracter es igual a la posición de sigma entra, 
                        bufferResult = transicion[i][j][0];    //teniendo en cuenta que en el array de transicion la primera columna son los estados
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

    public String procesarTransicionConDetalle(String[][][] transicion, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < automataFinito.states.size(); i++) {               //un loop para la cantidad de estados
            if (buffer.equals(automataFinito.states.get(i))) {              //si el estado actual buffer es igual a un estado en el automata ocntinua
                for (int j = 0; j < automataFinito.sigma.size(); j++) {      //loop para el alfabeto
                    if (read == automataFinito.sigma.get(j)) {                 //si el caracter es igual a la posicioòn de sigma entra, 
                        bufferResult = transicion[i][j][0];    // aqui parece que se está desbordando en rl indice   teniendo en cuenta que en el array de transicion la primera columa son los estados
                        //y la segunda columna los posibles caracteres
                    }
                }
            }
        }
        System.out.println("En el estado " + buffer + " Lee " + read + " y pasa al estado " + bufferResult);
        return bufferResult;
    }

    public boolean procesarListaDeCadenas() {
        ArrayList<String> Lista = new ArrayList<>();
        System.out.println("Ingrese la cantidad de cadenas a evaluar");
        int cant = sn.nextInt();
        String cadena = new String();
        for (int i = 0; i < cant; i++) {
            int x = i + 1;
            System.out.println("Ingrese la cadena " + x);
            cadena = sn.next();
            Lista.add(cadena);
        }

        boolean result = false;

        for (int i = 0; i < Lista.size(); i++) {
            String buffer = automataFinito.initialState;
            String cadenabuffer = Lista.get(i);
            for (int j = 0; j < cadenabuffer.length(); j++) {  //un loop para leer la cadena de entrada
                char read = cadenabuffer.charAt(j);
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

    public void generarSigma(String sigma) {

        String type = new String();

        for (int i = 0; i < sigma.length(); i++) {
            if (sigma.charAt(i) == ',') {
                type = "lista";
            } else if (sigma.charAt(i) == '-') {
                type = "intervalo";
            }
        }
        if (type.equals("lista")) {
            automataFinito.sigma.add(sigma.charAt(0));     // LOS ARCHIVOS ACEPTADOS NO TIENEN LISTA DE
            System.out.println(automataFinito.sigma.get(0));   // CARACTERES EN UNA SOLA LINEA
            automataFinito.sigma.add(sigma.charAt(2));
            System.out.println(automataFinito.sigma.get(1));
        } else if (type.equals("intervalo")) {
            int a = (int) sigma.charAt(0);
            int b = (int) sigma.charAt(2);
            int i = 0;
            for (int x = a; x <= b; x++) {

                automataFinito.sigma.add((char) x);
                System.out.println(automataFinito.sigma.get(i));
                i++;
            }
        }
    }
// fin de Clase
}
