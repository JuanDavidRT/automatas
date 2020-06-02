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
    AutomataFinito afd;
     int countf=0;
    int contc=0;
    List<String> lineasArchivo = new ArrayList<>();
    public  ArchivoController() {
       
    }
     public AutomataFinito leerArchivo(String ruta) {

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        
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
       
        //Se lee las lineas del archivo y se extraen los datos
        for (int i = 0; i < lineasArchivo.size(); i++) {

            if (i == 0) {
                if (lineasArchivo.get(0).equals("#!dfa")) {
                    System.out.println("-----AUTOMOATA FINITO DETERMINISTA-----");/// crea un objeto AFD
                } else if (lineasArchivo.get(0).equals("#!nfa")) {
                    System.out.println("-----AUTOAMTA FINITO NO DETERMINISTA-----"); /// crea un objeto AFN
                } else if (lineasArchivo.get(0).equals("#!nfe")) {
                    System.out.println("-----AUTOMATA FINITO NO DETERMINISTA CON TRANSICIONES LAMBDA------");   // crea objeto AFN lambda 
                } else {
                    System.out.println("----NO HA ESCRITO EL TIPO DE AUTOMATA------- ");

                }
            }
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
                        afd.transition[(int)lineasArchivo.get(j).charAt(1)][afd.sigma.indexOf(lineasArchivo.get(j).charAt(3))]=lineasArchivo.get(j).substring(4, 6);
                    }
                }
               }

            }
            return afd;
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
            afd.sigma.add(alfabetoA.charAt(0));     // LOS ARCHIVOS ACEPTADOS NO TIENEN LISTA DE
            System.out.println(afd.sigma.get(0));   // CARACTERES EN UNA SOLA LINEA
            afd.sigma.add(alfabetoA.charAt(2)); 
            System.out.println(afd.sigma.get(1));
        }
        else if(type.equals("intervalo")){
            int a=(int)alfabetoA.charAt(0);
            int b=(int)alfabetoA.charAt(2);
            int i=0;
            for(int x=a;x<=b;x++){
                
                afd.sigma.add((char)x);
                System.out.println(afd.sigma.get(i));
                i++;
            }
            
        }
         
    }
       // return afd;    
    }
