/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author andre
 */

import java.awt.event.ActionListener;
import javax.swing.*;


// esta clase va a manejar las interacciones con el usuario y la interfaz GUI

public class AFDView extends JFrame{
    
    //elementos de interfaz que aparencen en el Frame
    private JTextField userInput = new JTextField(10);
    private JTextArea screenOutput = new JTextArea(10,10);
    private JButton exitButton = new JButton();
    
    //dimenciones del frame
    final int WIDTH = 580;
    final int HIGTH = 800;
    
    
    AFDView(){
        
        JPanel automataPanel = new JPanel();
    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HIGTH);
        
        automataPanel.add(screenOutput);
        automataPanel.add(userInput);        
        automataPanel.add(exitButton);
        
        this.add(automataPanel);
    
    }
    
    //consigue el imput del usuario a manera de Strings que teclea
    public String getInput(){
        
        return userInput.getText();
    }
        
    //listener de los eventos en el frame
    void addCalculationListener(ActionListener listenForExitButton){
        
        exitButton.addActionListener(listenForExitButton);
    }
    
    //mensaje de error para excepcines
    void displayErrorMessage(String errerMessage){
    
        JOptionPane.showMessageDialog(this, errerMessage);
    }
    
    
}
