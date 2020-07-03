/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author crist
 */
public class Transicion {
    public String Sigma;
    public Estado EstadosDestino;

    public Transicion(String sigma, Estado estadosDestino){
        
        this.Sigma = sigma;
        this.EstadosDestino = estadosDestino;
    } 
  
}
