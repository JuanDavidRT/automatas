package Controller;

import java.util.*;
import java.io.*;
import Entity.AutomataFinito;

public class AFNLController extends AutomataFinito {

    Scanner sn = new Scanner(System.in);

    public AFNLController() {

    }

    public AFNLController(AutomataFinito automata) {
        sigma = automata.sigma;
        acceptanceStates = automata.acceptanceStates;
        states = automata.states;
        initialState = automata.initialState;
        transition = automata.transition;
        tran2 = automata.tran2;
    }

    public void crearAutomata() {

        String read = new String();
        int quantity;
        String arr[];
        System.out.println("Ingrese el alfabeto");
        read = sn.next();
        generarSigma(read);

        System.out.println("Ingrese el estado inicial");
        read = sn.next();
        initialState = read;
        states.add(read);
        System.out.println("El estado inicial es un estado de aceptación?");
        System.out.println("1. Si");
        System.out.println("2. No");
        int option = sn.nextInt();
        switch (option) {
            case 1:
                acceptanceStates.add(read);
                break;

            case 2:

                break;
            default:
                System.out.println("Solo números hay dos opciones válidas, 1 o 2");
        }

        System.out.println("Ingrese la cantidad de estados de aceptación (SIN incluir el estado inicial si este es de aceptación)");
        quantity = sn.nextInt();
        for (int i = 0; i < quantity; i++) {
            //int x = i + 1;
            System.out.println("Ingrese el estado de aceptación " + (i + 1));
            read = sn.next();
            acceptanceStates.add(read);
            states.add(read);
        }

        System.out.println("Ingrese la cantidad de estados SIN incluir el estado inicial y los estados de aceptación");
        quantity = sn.nextInt();
        for (int i = 0; i < quantity; i++) {
            System.out.println("Ingrese el estado " + (i + 1));
            read = sn.next();
            states.add(read);
        }

        System.out.println("¿Cuántas transiciones espontáneas en total tiene el autómata?");
        quantity = sn.nextInt();
        System.out.println("  A continuación podrá ingresar  las transiciones espontáneas");
        for (int i = 0; i < quantity; i++) {
            System.out.println("   Transición espontánea " + (i + 1));
            System.out.println("Estado inicial (del cual parte la transición espontánea): ");
            String ei = sn.next();
            System.out.println("Estado final: ");
            String ef = sn.next();
            arr = new String[3];
            arr[0] = ei;
            arr[1] = "$";
            arr[2] = ef;
            tran2.add(arr);
        }
        transition = new String[(states.size() + quantity)][(sigma.size() * 4)][1];

        System.out.println("   A continuación podrá ingresar las funciones de transición (SIN las transiciones espontáneas)");
        for (int i = 0; i < states.size(); i++) {
            for (int j = 0; j < sigma.size(); j++) {
                System.out.println("¿Cuántas transiciones tiene " + states.get(i) + " cuando lee " + sigma.get(j) + " ?");
                System.out.println("Si no tiene ninguna transición presione 0");
                quantity = sn.nextInt();
                if (quantity != 0) {
                    if (quantity > 1) {
                        for (int k = 0; k < quantity; k++) {
                            System.out.println("Ingrese la transición " + (k + 1) + " cuando " + states.get(i) + " lee " + sigma.get(j));
                            read = sn.next();
                            arr = new String[3];
                            arr[0] = states.get(i);
                            arr[1] = String.valueOf(sigma.get(j));
                            arr[2] = read;
                            tran2.add(arr);
                        }
                    } else {
                        System.out.println("Ingrese el estado al que pasa " + states.get(i) + " cuando lee " + sigma.get(j));
                        read = sn.next();
                        arr = new String[3];
                        arr[0] = states.get(i);
                        arr[1] = String.valueOf(sigma.get(j));
                        arr[2] = read;
                        tran2.add(arr);
                    }
                } else {
                    // NADA
                }
            }
        }
        for (int i = 0; i < tran2.size(); i++) {
            System.out.println(Arrays.toString(tran2.get(i)));
        }

    }

    public boolean procesarCadena(String cadena) {
        System.out.println("-------- AFN-L Entity procesar cadena --------");
        String buffer = initialState;
        boolean result = false;
        for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
            char read = cadena.charAt(i);
            buffer = procesarTransicion(tran2, buffer, read);//procesa el caracter que está en la posicion de i del stringresult = false;
        }
        for (int j = 0; j < acceptanceStates.size(); j++) {
            if (buffer.equals(acceptanceStates.get(j))) {
                result = true;                                // dice si es aceptado o no
            }
        }
        return result;
    }

    public String procesarTransicion(ArrayList<String[]> tran2, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < tran2.size(); i++) {
            if (buffer.equals(tran2.get(i)[0]) && String.valueOf(read).equals(tran2.get(i)[1])) {
                bufferResult = tran2.get(i)[2];
                break;
            }
        }
        return bufferResult;
    }

    public boolean procesarCadenaConDetalle(String cadena) {

        String buffer = initialState;
        boolean result = false;
        for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
            char read = cadena.charAt(i);
            buffer = procesarTransicionConDetalle(tran2, buffer, read);//procesa el caracter que está en la posicion de i del string
            result = false;           //reinicializo en false para cada iteración
            for (int j = 0; j < acceptanceStates.size(); j++) {
                if (buffer.equals(acceptanceStates.get(j))) {
                    result = true;                                // dice si es aceptado o no
                }
            }
        }
        return result;
    }

    public String procesarTransicionConDetalle(ArrayList<String[]> tran2, String buffer, char read) {
        String bufferResult = new String();
        for (int i = 0; i < tran2.size(); i++) {
            if (buffer.equals(tran2.get(i)[0]) && String.valueOf(read).equals(tran2.get(i)[1])) {
                bufferResult = tran2.get(i)[2];
                break;
            }
        }
        System.out.println("En el estado " + buffer + " Lee " + read + " y pasa al estado " + bufferResult);
        return bufferResult;
    }

    public void generarSigma(String Sigma) {

        String type = new String();

        for (int i = 0; i < Sigma.length(); i++) {
            if (Sigma.charAt(i) == ',') {
                type = "lista";
            } else if (Sigma.charAt(i) == '-') {
                type = "intervalo";
            }
        }
        if (type.equals("lista")) {
            sigma.add(Sigma.charAt(0));     // LOS ARCHIVOS ACEPTADOS NO TIENEN LISTA DE
            System.out.println(sigma.get(0));   // CARACTERES EN UNA SOLA LINEA
            sigma.add(Sigma.charAt(2));
            System.out.println(sigma.get(1));
        } else if (type.equals("intervalo")) {
            int a = (int) Sigma.charAt(0);
            int b = (int) Sigma.charAt(2);
            int i = 0;
            for (int x = a; x <= b; x++) {
                sigma.add((char) x);
                System.out.println(sigma.get(i));
                i++;
            }
        }
    }
    /*   
4
0-1
q0
1
0
2
q1
q2
0
1
q1
0
0
2
q0
q2
1
q0
0
     */
}
