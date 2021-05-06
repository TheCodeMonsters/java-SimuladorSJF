/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend; 

import Backend.Calendarizador;
import Backend.Proceso;

public class main { 
    public static void main(String[] args) throws InterruptedException {
        // FrontEnd
        Calendarizador calendarizador = new Calendarizador();
        Interfaz calendarizadorUI = new Interfaz(calendarizador);
        calendarizadorUI.setVisible(true); 
        calendarizadorUI.setLocationRelativeTo(null);   
        
        
        
        calendarizador.inicializarBloquesMemoria();
         
        // BackEnd
        calendarizador.procesar(); 
    }
    
}