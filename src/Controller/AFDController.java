/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AFDEntity;
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

    AFDEntity afdEntity;
    int countf=0;
    int contc=0;
    List<String> lineasArchivo = new ArrayList<>();

    public AFDController(){}
    
    //metodo para crear un AFDEntity HARCODED
    public void crearAutomata() {
        
        
        //declara HARDCODED las variables para crear el AFD
        ArrayList<Character> sigma = new ArrayList<Character>();
        ArrayList<String> states = new ArrayList<String>();
        String initialState = new String();
        ArrayList<String> acceptanceStates = new ArrayList<String>();
        String[][] transition = new String[3][2];
        
       

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

        afdEntity = new AFDEntity(sigma, states, initialState, acceptanceStates, transition);
        
    }
    
    
    
    public boolean procesarCadena(){
       System.out.println("-------- AFDEntity procesar cadena --------");
       boolean resultado ;
       return resultado = afdEntity.procesarCadena();
       
   }
    
    public boolean procesarCadenaConDetalle(){
       boolean resultado;
       return resultado = afdEntity.procesarCadenaConDetalle();
       
   }    
    
    public boolean procesarListaDeCadenas(){
       boolean resultado;
       return resultado = afdEntity.procesarListaDeCadenas();
       
   } 
    
    public void generarSigma(){
        String alfabetoA="a,b";
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
            afdEntity.sigma.add(alfabetoA.charAt(0));     // LOS ARCHIVOS ACEPTADOS NO TIENEN LISTA DE
            System.out.println(afdEntity.sigma.get(0));   // CARACTERES EN UNA SOLA LINEA
            afdEntity.sigma.add(alfabetoA.charAt(2)); 
            System.out.println(afdEntity.sigma.get(1));
        }
        else if(type.equals("intervalo")){
            int a=(int)alfabetoA.charAt(0);
            int b=(int)alfabetoA.charAt(2);
            int i=0;
            for(int x=a;x<=b;x++){
                
                afdEntity.sigma.add((char)x);
                System.out.println(afdEntity.sigma.get(i));
                i++;
            }
            
        }
         
    }


// fin de Clase
}
