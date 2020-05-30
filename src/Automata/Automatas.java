/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

import Controller.AFDController;
import Entity.AFD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Darkluiggi
 */
public class Automatas {

    public static AFDController afdController;

    /**
     * @param args the command line arguments
     */
    public Automatas() {

    }

    public static void main(String[] args) {
        // TODO code application logic here
        
        
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
               while(!lista.get(i+1).equals("#states")){
                   System.out.println("busco"); // agregar alfabeto e identicar intervalo de alfabeto
                   
                   i++;
               }
               
           }
           if(lista.get(i).equals("states")){
               while(!lista.get(i+1).equals("#initial")){
                   System.out.println("busco"); // agregar alfabeto e identicar intervalo de alfabeto
                   
                   i++;
               }
           }
           

           
       }
        
        afdController = new AFDController();
        AFD pepito = new AFD();

        Scanner sn = new Scanner(System.in);
        int option, optionIn;
        boolean exit = false;
        boolean result=false;
        while (!exit) {

            System.out.println("Automatas");
            System.out.println(" ");
            System.out.println("1.AFD");
            System.out.println("2.AFN");
            System.out.println("3.AFN-lambda");
            System.out.println("4. Salir");

            try {

                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Autómata determinista");
                        System.out.println("Escribe una de las opciones");
                        System.out.println("1.Procesar Cadena");
                        System.out.println("2.Procesar Cadena con detalles");
                        System.out.println("3.Procesar lista de cadenas");
                        optionIn = sn.nextInt();
                        switch (optionIn) {
                            case 1:
                                result = afdController.procesarCadena();
                                if (result) {
                                    System.out.println("La cadena es aceptada");
                                } else {
                                    System.out.println("La cadena NO es aceptada");
                                }
                                break;
                                case 2:
                                result = afdController.procesarCadenaConDetalle();
                                if (result) {
                                    System.out.println("La cadena es aceptada");
                                } else {
                                    System.out.println("La cadena NO es aceptada");
                                }
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Autómata no determinista");
                        break;
                    case 3:
                        System.out.println("Autómata lambda no determinista");
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

}
