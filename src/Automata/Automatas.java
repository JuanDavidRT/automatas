/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

import Controller.AFDController;
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
        
        
        

        Scanner sn = new Scanner(System.in);
        int option, optionIn;
        boolean exit = false;
        boolean result=false;
        String cadena= new String();
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
                                System.out.println("Por favor inrese la cadena a evaluar:");
                                cadena=sn.next();
                                result = afdController.procesarCadena(cadena);
                                if (result) {
                                    System.out.println("La cadena es aceptada");
                                } else {
                                    System.out.println("La cadena NO es aceptada");
                                }
                                break;
                                case 2:
                                    System.out.println("Por favor inrese la cadena a evaluar:");
                                 cadena=sn.next();
                                result = afdController.procesarCadenaConDetalle(cadena);
                                if (result) {
                                    System.out.println("La cadena es aceptada");
                                } else {
                                    System.out.println("La cadena NO es aceptada");
                                }
                                break;
                                 case 3:
                                result = afdController.procesarListaDeCadenas();
                                
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Autómata no determinista");
                        afdController.generarSigma();
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
