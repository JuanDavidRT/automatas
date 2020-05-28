/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Automata;
import Controller.AFDController;

/**
 *
 * @author Darkluiggi
 */
public class Automatas {

    public static AFDController afdController;
    /**
     * @param args the command line arguments
     */
    public Automatas(){
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        afdController= new AFDController();
        
       boolean result= afdController.procesarCadena();
        if(result){
            System.out.println("La cadena es aceptada");
        }
        else{
            System.out.println("La cadena NO es aceptada");
        }
    }
    
}
