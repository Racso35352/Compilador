/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_compilador;
import java.io.*;
import java.util.regex.*;
/**
 *
 * @author Oscar
 */
public class Nuevo_Compilador {

    public static FileWriter fichero = null;
    public static PrintWriter pw = null;
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        cnt=0;
        cont=0;
        
      /*
      System.out.println("Ingrese el nombre del archivo destino con su extension");
      String destino=ent.next();
      */
      Pattern comentario = Pattern.compile("[ ]*");
      Matcher mat;//Declaración del matcher
      //Cleación del nuevo archivo
      String ori=Origen.getText();
      //String ori="";
      
      //String dest=Destino.getText();
      String dest="";
    }
    
    
}
