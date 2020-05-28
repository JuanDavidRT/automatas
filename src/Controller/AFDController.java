/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AFD;

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
        String cadena= "abababa";
        String buffer=afd.InitialState;
        boolean result = false;
        for(int i=0;i<cadena.length();i++){
            char read=cadena.charAt(i);
            buffer=procesarTransicion(afd.Transition, buffer, read);
            result=false;
            for(int j=0;j<afd.AcceptanceState.length;j++){
                if(buffer.equals(afd.AcceptanceState[j])){
                    result=true;
                }
            }            
        }
                
        return result;
    }
    
    public String procesarTransicion(String[][] transicion, String buffer, char read)
    {
        String bufferResult=new String();
        for(int i=0;i<afd.States.length;i++){
            if(buffer.equals(afd.States[i])){
                
                for(int j=0; j<afd.Sigma.length; j++){
                    if(read==afd.Sigma[j]){
                        
                       bufferResult =transicion[i][j];    
                        break;
                    }
                }           
            }
        }       
        
        return bufferResult;
    }
}
