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
 *
 * @author leons
 */
public class ArchivoController {
    
    AutomataFinito afd = new AutomataFinito();
    int countf=0;
    int contc=0;
    List<String> lineasArchivo = new ArrayList<>();
    
 
    
    
    public  ArchivoController() {
       
    }
    
    public AutomataFinito leerArchivo(String ruta) {

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
                System.out.println(linea);
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

            // de determina que tido de Auotomata contiene el archivocon la primera linea
            if (i == 0) {
                if (lineasArchivo.get(0).equals("#!dfa")) {
                    System.out.println("-----AUTOMOATA FINITO DETERMINISTA-----");
                } else if (lineasArchivo.get(0).equals("#!nfa")) {
                    System.out.println("-----AUTOAMTA FINITO NO DETERMINISTA-----"); 
                } else if (lineasArchivo.get(0).equals("#!nfe")) {
                    System.out.println("-----AUTOMATA FINITO NO DETERMINISTA CON TRANSICIONES LAMBDA------");
                } else {
                    System.out.println("----NO HA ESCRITO EL TIPO DE AUTOMATA    ¡Archivo no aceptado!  ------- ");

                }
            }
            
            
            //seccion que genera los alfabetos
            if (lineasArchivo.get(i).equals("#alphabet")) {
                while (!lineasArchivo.get(i + 1).equals("#states") && i <= lineasArchivo.size()) {
                    //System.out.println("busco"); // agregar alfabeto e identicar intervalo de alfabeto
                    generarSigma(lineasArchivo.get(i+1));
                    i++;
                }

            }
            
            
            if (lineasArchivo.get(i).equals("#states")) {
                while (!lineasArchivo.get(i + 1).equals("#initial") && i <= lineasArchivo.size()) {
                   // System.out.println("busco estados "); // agregar alfabeto e identicar intervalo de alfabeto
                    afd.states.add(lineasArchivo.get(i+1));
                    
                    
                    i++;
                }
            }
            if (lineasArchivo.get(i).equals("#initial")) {
                while (!lineasArchivo.get(i + 1).equals("#accepting") && i <= lineasArchivo.size()) {
                    System.out.println("busco ini"); // agregar estado inicial del automata
                    
                    afd.initialState= lineasArchivo.get(i+1);
                    i++;
                }
            }
            if (lineasArchivo.get(i).equals("#accepting")) {
                while (!lineasArchivo.get(i + 1).equals("#transitions") && i <= lineasArchivo.size()) {
                    System.out.println("leo acept"); // agregar estado inicial del automata
                    afd.acceptanceStates.add(lineasArchivo.get(i+1));

                    i++;
                }

            }
            if (lineasArchivo.get(i).equals("#transitions")) {
                for (int j = i+1; j < lineasArchivo.size(); j++) {
                    for (int k = 0; k < lineasArchivo.get(j).length(); k++) {
                        afd.transition[(int)lineasArchivo.get(j).charAt(1)][afd.sigma.indexOf(lineasArchivo.get(j).charAt(3))] = lineasArchivo.get(j).substring(4, 6);
                    }
                }
               }

            }
        
        
            return afd;
            
        }
    
    
    //interpreta y añade aol alfabeto liena por linea
    public void generarSigma(String sigma){
        
        String alfabetoA = sigma;
        String type = new String();
        
        for(int i=0; i<alfabetoA.length();i++){
            if(alfabetoA.charAt(i)==','){       // estas comas no están el los formatos admitidos
                type="lista";
            }else if(alfabetoA.charAt(i)=='-'){
                type="intervalo";
            }      
        }
        if(type.equals("lista")){
            afd.sigma.add(alfabetoA.charAt(0));     // LOS ARCHIVOS ACEPTADOS NO TIENEN LISTA DE
            System.out.println(afd.sigma.get(0));   // CARACTERES EN UNA SOLA LINEA
            afd.sigma.add(alfabetoA.charAt(2)); 
            System.out.println(afd.sigma.get(1));
        }
        else if(type.equals("intervalo")){
            
            int a=(int)alfabetoA.charAt(0);  // toma valores ASCII
            int b=(int)alfabetoA.charAt(2);
            int i=0;
            for(int x=a;x<=b;x++){
                System.out.println(" el valor de x: " + (char)x);
                afd.sigma.add( (char)x );
                System.out.println("valor guardado en el AF" + afd.sigma.get(i));
                i++;
            }
            
        }
         
    }
         
        
       // return afd;
    }

