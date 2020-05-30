/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AFD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leons
 */
public class AFDController {
     AFD afd;
    
     
     
    public AFDController(){
    afd=new AFD();
    afd.Sigma=new char[10];
    afd.States=new String[3];
    afd.InitialState= new String();
    afd.AcceptanceState=new String[3];
    afd.Transition=new String[3][2];
    
    
    afd.Sigma[0]='a';
     afd.Sigma[1]='b';
    afd.InitialState="q0";
    afd.AcceptanceState[0]="q0";
    afd.AcceptanceState[1]="q2";
    for(int i=0; i<3;i++){
         afd.States[i]="q"+i;
    }
    
         afd.Transition[0][0]="q0";
         afd.Transition[0][1]="q1";
         afd.Transition[1][0]="q1";
         afd.Transition[1][1]="q2";
         afd.Transition[2][0]="q1";
         afd.Transition[2][1]="q1";
         
         
              
    
    }
    
    public boolean procesarCadena(){
        String cadena= "ababab";
        String buffer=afd.InitialState;
        boolean result = false;
        for(int i=0;i<cadena.length();i++){  //un loop para leer la cadena de entrada
            char read=cadena.charAt(i);
            buffer=procesarTransicion(afd.Transition, buffer, read);//procesa el caracter que está en la posicion de i del string
            result=false;           //reinicializo en false para cada iteración
            for(int j=0;j<afd.AcceptanceState.length;j++){
                if(buffer.equals(afd.AcceptanceState[j])){
                    result=true;                                // dice si es aceptado o no
                }
            }            
        }
                
        return result;
    }
    public boolean procesarCadenaConDetalle(){
        String cadena= "ababab";
        String buffer=afd.InitialState;
        boolean result = false;
        for(int i=0;i<cadena.length();i++){  //un loop para leer la cadena de entrada
            char read=cadena.charAt(i);
            buffer=procesarTransicionConDetalle(afd.Transition, buffer, read);//procesa el caracter que está en la posicion de i del string
            result=false;           //reinicializo en false para cada iteración
            for(int j=0;j<afd.AcceptanceState.length;j++){
                if(buffer.equals(afd.AcceptanceState[j])){
                    result=true;                                // dice si es aceptado o no
                }
            }            
        }
                
        return result;
    }
    
    public String procesarTransicion(String[][] transicion, String buffer, char read)
    {
        String bufferResult=new String();
        for(int i=0;i<afd.States.length;i++){               //un loop para la cantidad de estados
            if(buffer.equals(afd.States[i])){               //si el estado actual buffer es igual a un estado en el automata ocntinua
                
                for(int j=0; j<afd.Sigma.length; j++){      //loop para el alfabeto
                    if(read==afd.Sigma[j]){                 //si el caracter es igual a la posición de sigma entra, 
                        bufferResult =transicion[i][j];    //teniendo en cuenta que en el array de transicion la primera columna son los estados
                                                            //y la segunda columna el alfabeto
                    }
                }           
            }
        }       
        
        return bufferResult;
    }
    public String procesarTransicionConDetalle(String[][] transicion, String buffer, char read)
    {
        String bufferResult=new String();
        for(int i=0;i<afd.States.length;i++){               //un loop para la cantidad de estados
            if(buffer.equals(afd.States[i])){               //si el estado actual buffer es igual a un estado en el automata ocntinua
                
                for(int j=0; j<afd.Sigma.length; j++){      //loop para el alfabeto
                    if(read==afd.Sigma[j]){                 //si el caracter es igual a la posicioòn de sigma entra, 
                        bufferResult =transicion[i][j];    //teniendo en cuenta que en el array de transicion la primera columa son los estados
                                                            //y la segunda columna los posibles caracteres
                    }
                }           
            }
        }       
        System.out.println("En el estado " +buffer+" Lee "+ read + " y pasa al estado " + bufferResult);
        return bufferResult;
    }
    public void leerArchivo(){
                
        
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      
    
      List<String> lista = new ArrayList<>();
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("C:\\Universidad\\nose.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         
        
         // Lectura del fichero
         String linea;
         
         while((linea=br.readLine())!=null){
            System.out.println(linea);
            lista.add(linea); // añade archivo a la lista 
         }
            
           
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      
      
       for (int i = 0; i < lista.size(); i++) {
           
           if(i==0){
               if(lista.get(0).equals("#!dfa")){
                   System.out.println("-----AUTOMOATA FINITO DETERMINISTA-----");/// crea un objeto AFD
               }else if(lista.get(0).equals("#!nfa")){
                   System.out.println("-----AUTOAMTA FINITO NO DETERMINISTA-----"); /// crea un objeto AFN
               }else if (lista.get(0).equals("#!nfe")) {
                   System.out.println("-----AUTOMATA FINITO NO DETERMINISTA CON TRANSICIONES LAMBDA------");   // crea objeto AFN lambda 
               }else{
                   System.out.println("----NO HA ESCRITO EL TIPO DE AUTOMATA------- ");
                   
                 
                   
               }
           }
           if(lista.get(i).equals("#alphabet")){
               while(!lista.get(i+1).equals("#states")&& i<=lista.size()){
                   System.out.println("busco"); // agregar alfabeto e identicar intervalo de alfabeto
                   
                   i++;
               }
               
           }
           if(lista.get(i).equals("#states")){
               while(!lista.get(i+1).equals("#initial")&& i<=lista.size()){
                   System.out.println("busco estados "); // agregar alfabeto e identicar intervalo de alfabeto
                   
                   i++;
               }
           }
           if(lista.get(i).equals("#initial")){
               while(!lista.get(i+1).equals("#accepting")&& i<=lista.size()){
                   System.out.println("busco ini"); // agregar estado inicial del automata
                   
                   i++;
               }
           }
           if(lista.get(i).equals("#accepting")){
               while(!lista.get(i+1).equals("#transitions")&& i<=lista.size()){
                   System.out.println("leo acept"); // agregar estado inicial del automata
                   
                   i++;
               }
           

           } 
           if(lista.get(i).equals("#transitions")){
               while(!lista.get(i+1).equals("")&& i<=lista.size()){
                   System.out.println("leo trans "); // agregar estado inicial del automata
                   
                   i++;
               }
           

           } 
           
           
        }
       }
    }