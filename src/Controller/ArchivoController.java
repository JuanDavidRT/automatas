/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Automata.Automatas.automataFinito;
import Entity.AutomataFinito;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author leons
 */
public class ArchivoController {

    
    int countf = 0;
    int contc = 0;
    List<String> lineasArchivo = new ArrayList<>();

    public ArchivoController() {

    }

    
    public String leerTipo(String ruta ) throws IOException{
        String tipo;        
        File archivo = null;
        archivo = new File(ruta);
        
        BufferedReader br = new BufferedReader(new FileReader(archivo));        
        tipo = br.readLine();
        
        
        
    return tipo;
    }
    
    //leer arvho 
    public AutomataFinito leerArchivo(String ruta) {
        
        AutomataFinito automatafinito = new AutomataFinito();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        

        //Se lee las lineas del archivo y se extraen los datos
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;

            while ((linea = br.readLine()) != null) {
                //System.out.println(linea);
                lineasArchivo.add(linea); // añade archivo a la lista 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        
        
        //se leen las string en la lista
        for (int i = 0; i < lineasArchivo.size(); i++) {

            //lectura de la primera linea del archivo
            if (i == 0) {
                if (lineasArchivo.get(0).equals("#!dfa")) {
                    System.out.println("-----  AFD  -----");
                    
                } else if (lineasArchivo.get(0).equals("#!nfa")) {
                    System.out.println("-----  AFN  -----");
                   
                } else if (lineasArchivo.get(0).equals("#!nfe")) {
                    System.out.println("-----  AFNL  ------");
                   
                } else {
                    System.out.println("----  ¡Archivo no aceptado!  ------- ");

                }
            }

            
            
            
            
            //seccion que genera los alfabetos
            if (lineasArchivo.get(i).equals("#alphabet")) {
                System.out.println("Generando alfabeto ..."); 
                while (!lineasArchivo.get(i + 1).equals("#states") && i <= lineasArchivo.size()) {
                    generarSigma(lineasArchivo.get(i + 1));
                    i++;
                }

            }

            if (lineasArchivo.get(i).equals("#states")) {
                System.out.println("Capturando Estados Q ...");
                
                int index = 0;
                while (!lineasArchivo.get(i + 1).equals("#initial") && i <= lineasArchivo.size()) {
                    automataFinito.states.add(lineasArchivo.get(i + 1));                    
                    System.out.println("-- " + lineasArchivo.get(i + 1) + " --");

                    i++;
                }
            }
            if (lineasArchivo.get(i).equals("#initial")) {
                System.out.println("Capturando estado inicial q0 ...");
                while (!lineasArchivo.get(i + 1).equals("#accepting") && i <= lineasArchivo.size()) {
                    System.out.println("-- " + lineasArchivo.get(i + 1) + " --");
                    automataFinito.initialState = lineasArchivo.get(i + 1);
                    i++;
                }
            }
            if (lineasArchivo.get(i).equals("#accepting")) {
                System.out.println("Capturando estados de aceptacion F ...");
                while (!lineasArchivo.get(i + 1).equals("#transitions") && i <= lineasArchivo.size()) {
                    System.out.println("-- " + lineasArchivo.get(i + 1) + " --");
                    automataFinito.acceptanceStates.add(lineasArchivo.get(i + 1));
                    i++;
                }

            }
            
            //necesita nueva funcion de lectura
            if (lineasArchivo.get(i).equals("#transitions")) {
                System.out.println("generar transitons...");
                System.out.println("Capturando funcion de tracicion Delta ...");                
                for ( int j = i + 1 ; j < lineasArchivo.size(); j++) {
                    generarTransitions(lineasArchivo.get(j));
                }
            }
        }
        return automataFinito;
    }

    
    //interpreta y añade al alfabeto liena por linea 
    public void generarSigma(String lineaSigma) {
        
        String regex = "^([^\\n\\t\\$\\r])(-[^\\$\\ \\n\\t\\r])*";
        Pattern sigmaPattern = Pattern.compile(regex);                  //genera un patron desde un regex
        Matcher m = sigmaPattern.matcher(lineaSigma);                   //objeto Matcher
 
        boolean coincidencia = m.find();                                //procesa las cadenas del archivo y determina si son aceptadas
        //System.out.println("-- liena aceptada??? " + coincidencia); 
        
        if(coincidencia){
            if(lineaSigma.length() > 1){
                int a = (int) lineaSigma.charAt(0);                     // toma valores ASCII de los esctremos del intervalo
                int b = (int) lineaSigma.charAt(2);  
                int i = 0;
                for (int x = a; x <= b; x++) {                          //ingresa caracteres al alfebeto
                System.out.println("--  " + (char) x + " --");
                automataFinito.sigma.add((char) x);                
                i++;                
                }        
            }else{
                char simbol = lineaSigma.charAt(0);
                automataFinito.sigma.add(simbol);
            }
        }else{
            System.out.println("LINEA NO ACEPTADA!!!  " + lineaSigma);
        }
    }
    
    
    // Genera la funcion de transicion liena por linea
    public void generarTransitions(String lineaTransitions){
        
        System.out.println("-- " + lineaTransitions + " --");               
        
        
        String regex = "^([^\\t\\n\\ \\:\\;\\>]*):([^\\n\\t\\ \\:\\>])>([^\\n\\t\\ \\:\\;>]+;)*([^\\n\\t\\ \\:\\;\\>]+)$";
        Pattern trantitionPattern = Pattern.compile(regex);          //genera un patron desde un regex
        Matcher m = trantitionPattern.matcher(lineaTransitions);       //objeto Matcher
 
        boolean coincidencia = m.find();                                //procesa las cadenas del archivo y determina si son aceptadas
              
        
        
        if (coincidencia){                                                          
            
            
            String estateSimbol_TrantitionsSplit [] = lineaTransitions.split(">");      //separa la linea para guardar la info          
            String stateSimbol [] = estateSimbol_TrantitionsSplit[0].split(":");    // estateSimbol tiene el estado de la unidad de cotrol y simbolo                         
            String transitions [] = estateSimbol_TrantitionsSplit[1].split(";");               //transiciones individiales
            
            int fila = 0;
            int columna = 0;
            boolean estadoPertenece = false;
            boolean simboloPertenece = false;
            
            for (int i = 0 ; i < automataFinito.sigma.size(); i++){
                
                //System.out.println(stateSimbol[1].charAt(0) + " " + automataFinito.sigma.get(i));
                
                
                if(stateSimbol[1].charAt(0) == automataFinito.sigma.get(i)){
                    fila = automataFinito.sigma.indexOf(i);
                    simboloPertenece = true;
                }                
            }
            
            for (int i = 0; i < automataFinito.states.size(); i++){
                if( stateSimbol[0].equals(automataFinito.states.get(i)) ){
                    columna = automataFinito.states.indexOf(i);
                    estadoPertenece = true;
                    }
                }
            
            
            if(simboloPertenece && estadoPertenece){
                System.out.println("[" + fila + "," + columna + "]");
            }else if(!simboloPertenece){
                System.out.println("SIMBOLO NO PERTENECE");
            }else if(!estadoPertenece){
                System.out.println("ESTADO NO PERTENECE");
            }
            
            
                                
        }else{
            System.out.println("linea / archivo no aceptado");
        }
        
        
    }
    

    public void generarArchivo(AutomataFinito automata) {
        final Formatter x;
        Scanner sn = new Scanner(System.in);
        System.out.println("Ingresa el nombre que deseas para el arhcivo del automata");
        String fileName = sn.next();

        try {
            File archivo = new File(fileName + ".txt");
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            PrintWriter pw = new PrintWriter(archivo);
            System.out.println("Ingresa el tipo de automata");
            System.out.println("1.AFD");
            System.out.println("2.AFN");
            System.out.println("3.AFN-lambda");
            int option = sn.nextInt();
            switch (option) {
                case 1:
                    pw.println("#!dfa");
                    break;
                case 2:
                    pw.println("#!nfa");
                    break;
                case 3:
                    pw.println("#!nfe");
                    break;
            }
            pw.println("#alphabet");
            for (int i = 0; i < automata.sigma.size(); i++) {
                System.out.println(automata.initialState);
                pw.println(automata.sigma.get(i));
            }
            pw.println("#states");
            for (int i = 0; i < automata.states.size(); i++) {
                pw.println(automata.states.get(i));
            }
            pw.println("#initial");
            pw.println(automata.initialState);
            pw.println("#accepting");
            for (int i = 0; i < automata.acceptanceStates.size(); i++) {
                pw.println(automata.acceptanceStates.get(i));
            }
            pw.println("#transitions");
            
            pw.close();
            System.out.println("Archivo creado exitosamente");
        } catch (IOException ex) {
            System.out.println("El archivo no se ha podido guardar");
        }
    }
  
    
//classEnd    
}
