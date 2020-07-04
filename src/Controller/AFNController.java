/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AutomataAFN;
import Entity.AutomataFinito;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Dentro del modelo MVC las clases de este paquete se encargan de manejar las
 * interacciones entre View (que se encaga de manejar las interacciones con el
 * usuario) y Entity (Model en MVC, que maneja las entidades, objetos, atributos
 * y metodos)
 */
public class AFNController {

    String inicial;
    AutomataAFN automata = new AutomataAFN(inicial);

    public AFNController(String inicial) {
        this.inicial = inicial;

    }

    public void CrearAutomataAMano() {

        automata.AgregarEstado("q0");
        automata.AgregarEstado("q1");
        automata.AgregarEstado("q2");

        Estado estadoQ0 = automata.GetEstadoByNombre("q0");
        Estado estadoQ1 = automata.GetEstadoByNombre("q1");
        Estado estadoQ2 = automata.GetEstadoByNombre("q2");
        
       
        automata.EstadosAceptacion.add(estadoQ0);
        automata.EstadosAceptacion.add(estadoQ0);
        automata.EstadosAceptacion.add(estadoQ0);

        /*estadoQ0.AgregarTransicion("a", estadoQ1);
        estadoQ1.AgregarTransicion("a", estadoQ1);
        estadoQ1.AgregarTransicion("a", estadoQ2);
        estadoQ2.AgregarTransicion("b", estadoQ1);
        estadoQ1.AgregarTransicion("a", estadoQ1);*/
        
        for (int i = 0; i < automata.EstadosAceptacion.size(); i++) {
            System.out.println(automata.Estados.get(i).nombre);
        }
        
        
        
    }

    public boolean procesarCadena(String Cadena, Estado estadoActual) {

        //aabb
        String caracterEvaluar = Cadena.substring(0, 1);
        String cadenaRestante = Cadena.substring(1, Cadena.length());
        if(Cadena.length()==0){
            for (int i = 0; i < automata.EstadosAceptacion.size(); i++) {
               if( automata.EstadosAceptacion.get(i)== estadoActual){
                   return true;
               }
            }
        }

            //Si la cadena que se recibe por parmaetro es de longitud 1 al buscar la transicion se debe evaluar si el estado destino es valido, retorna true, sino false.
        List<Transicion> transicionEncontrada = estadoActual.BuscarTransicion(caracterEvaluar);
        if (transicionEncontrada == null || transicionEncontrada.isEmpty()) {
            return false;
        }

        for (int j = 0; j < transicionEncontrada.size(); j++) {
            boolean respuesta = procesarCadena(cadenaRestante,transicionEncontrada.get(j).EstadosDestino);
            if(respuesta){
                return true;
            }
        }

        return false;
    }
    public Estado estadoInicial(){
        return automata.EstadoInicial;
    }

    //metodo para crear un AFDEntity HARCODED
    /*public void crearAutomata() {

     // inicializa
     String read;
     int quantity;
     System.out.println("Ingrese el alfabeto");
     read = sn.next();
     generarSigma(read);

     System.out.println("Ingrese el estado inicial");
     read = sn.next();
     initialState = read;

     System.out.println("Ingrese la cantidad de estados de aceptación");
     quantity = sn.nextInt();
     for (int i = 0; i < quantity; i++) {
     int x = i + 1;
     System.out.println("Ingrese el estado de aceptación " + x);
     read = sn.next();
     acceptanceStates.add(read);
     }

     System.out.println("Ingrese la cantidad de estados");
     quantity = sn.nextInt();
     for (int i = 0; i < quantity; i++) {
     states.add("q" + i);
     }
     transition = new String[states.size()][sigma.size()][states.size()];

     System.out.println("Ingrese la función de transición");
     for (int i = 0; i < states.size(); i++) {
     for (int j = 0; j < sigma.size(); j++) {
     System.out.println("Ingrese la transición del estado " + states.get(i) + " cuando lee " + sigma.get(j) + ", Si ya completo todas las transiciones de ese elemento ingrese el 0");
     int k = 0;
     while (1 > 0) {
     read = sn.next();
     if (read.equals("0")) {
     break;
     }
     transition[i][j][k] = read;
     k++;
     }
     }
     }
     for (int i = 0; i < states.size(); i++) {
     for (int j = 0; j < sigma.size(); j++) {
     for (int k = 0; k < states.size(); k++) {
     System.out.println(i + "|" + j + "|" + k + "|" + " = " + transition[i][j][k]);
     }
     }
     }
     }

     public void generarAutomata() {

     initialState = "q0";
     generarSigma("a,b");
     acceptanceStates.add("q1");
     int quantity = 3;
     for (int i = 0; i < quantity; i++) {
     states.add("q" + i);
     }
     transition = new String[states.size()][sigma.size()][states.size()];
     transition[0][0][0] = "q1";
     transition[1][0][0] = "q1";
     transition[1][0][1] = "q2";
     transition[2][1][0] = "q1";
     /*transition[1][0][1] = "q2";
     transition[2][1][0] = "q1";
     transition[2][1][1] = "q2";
     transition[3][1][1] = "q1";
     for (int i = 0; i < states.size(); i++) {
     for (int j = 0; j < sigma.size(); j++) {
     if(transition[i][j][0]!= null &&transition[i][j][1]!= null){
     multi.add(states.get(i));
     }
     }
     }

     }

     // metodos que se encontraba antes en AFDController
    


    
     public boolean procesarCadenaasd(String cadena,String buffer) {
     System.out.println("-------- AFN Entity procesar cadena --------");

     // buffer = initialState;}
     boolean result = false;
     boolean result2 = false;
     for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
     char read = cadena.charAt(i);
     for (int j = 0; j < multi.size(); j++) {
     if(buffer.equals(multi.get(j))){
     for (int k = 0; k < states.size(); k++) {
     buffer= procesarTransicionafn(transition, buffer, read,k); 
                        
     result = false;           //reinicializo en false para cada iteración
     for (int p = 0; p < acceptanceStates.size(); p++) {
     if (buffer.equals(acceptanceStates.get(p))) {
     result = true;                                // dice si es aceptado o no
     }
     }
     String resto = cadena.substring(i, cadena.length());
     procesarCadenaasd(resto,buffer);
     }
                   
     } else {
     buffer = procesarTransicion(transition, buffer, read);//procesa el caracter que está en la posicion de i del string
     result = false;           //reinicializo en false para cada iteración
     for (int p = 0; p < acceptanceStates.size(); p++) {
     if (buffer.equals(acceptanceStates.get(p))) {
     result = true;                                // dice si es aceptado o no
     }
     }
     }

     }
 
            
     }
     return result;
        
     }

     public String procesarTransicionafn(String[][][] transicion, String buffer, char read, int k) {
     String bufferResult = new String();
     for (int i = 0; i < states.size(); i++) {               //un loop para la cantidad de estados
     if (buffer.equals(states.get(i))) {               //si el estado actual buffer es igual a un estado en el automata ocntinua
                
     for (int j = 0; j < sigma.size(); j++) {      //loop para el alfabeto
     char readSigma=sigma.get(j);
     if (read == readSigma && transition[i][j][k]!=null) {                 //si el caracter es igual a la posición de sigma entra, 
     bufferResult = transicion[i][j][k];    //teniendo en cuenta que en el array de transicion la primera columna son los estados
     //y la segunda columna el alfabeto
     }
     }
     }
     }
     return bufferResult;
     }

     public String procesarTransicion(String[][][] transicion, String buffer, char read) {
     String bufferResult = new String();
     for (int i = 0; i < states.size(); i++) {               //un loop para la cantidad de estados
     if (buffer.equals(states.get(i))) {               //si el estado actual buffer es igual a un estado en el automata ocntinua

     for (int j = 0; j < sigma.size(); j++) {      //loop para el alfabeto
     if (read == sigma.get(j)) {                 //si el caracter es igual a la posición de sigma entra, 
     bufferResult = transicion[i][j][0];    //teniendo en cuenta que en el array de transicion la primera columna son los estados
     //y la segunda columna el alfabeto
     }
     }
     }
     }
     return bufferResult;
     }
    
     public boolean procesarCadenaConDetalle(String cadena) {

     String buffer = initialState;
     boolean result = false;
     for (int i = 0; i < cadena.length(); i++) {  //un loop para leer la cadena de entrada
     char read = cadena.charAt(i);
     buffer = procesarTransicionConDetalle(transition, buffer, read);//procesa el caracter que está en la posicion de i del string
     result = false;           //reinicializo en false para cada iteración
     for (int j = 0; j < acceptanceStates.size(); j++) {
     if (buffer.equals(acceptanceStates.get(j))) {
     result = true;                                // dice si es aceptado o no
     }
     }
     }
     return result;
     }

     public String procesarTransicionConDetalle(String[][][] transicion, String buffer, char read) {
     String bufferResult = new String();
     for (int i = 0; i < states.size(); i++) {               //un loop para la cantidad de estados
     if (buffer.equals(states.get(i))) {              //si el estado actual buffer es igual a un estado en el automata ocntinua
     for (int j = 0; j < sigma.size(); j++) {      //loop para el alfabeto
     if (read == sigma.get(j)) {                 //si el caracter es igual a la posicioòn de sigma entra, 
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
     String buffer = initialState;
     String cadenabuffer = Lista.get(i);
     for (int j = 0; j < cadenabuffer.length(); j++) {  //un loop para leer la cadena de entrada
     char read = cadenabuffer.charAt(j);
     buffer = procesarTransicionConDetalle(transition, buffer, read);//procesa el caracter que está en la posicion de i del string
     result = false;           //reinicializo en false para cada iteración
     for (int k = 0; k < acceptanceStates.size(); k++) {
     if (buffer.equals(acceptanceStates.get(k))) {
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
     public void metodop(){
     for (int i = 0; i < 10; i++) {
     System.out.println(i);
     }
     }*/
// fin de Clase
}
