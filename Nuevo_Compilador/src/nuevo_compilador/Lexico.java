/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_compilador;

import java.util.regex.*;
import java.io.*;
/**
 *
 * @author Oscar
 */
public class Lexico {
   
    //Tabla de tokens
   public static String repite="Repite";
   public static String hacer="Hacer";
   public static String mientras="Mientras";
   public static String variable="Variable";
   public static String numero="Numero";
   public static String operador="Operador";
   public static String desicion="Desicion";
   public static String imprime="Imprime";
   public static String error="Error";
   public static String lee="Lectura";
   public static String asignacion="Asignacion";
   public static String fina="Final";   
   public static String tipo="Tipo";
   public static String parametro="Parametro"; 
   public static String param1="Parametro1"; 
   public static String param2="Parametro2"; 
   public static String agregacion="Agregacion";
   public static String cadena="Cadena";
   public static String caracter="Caracter";
   public static String dec="DEC";
   
   
   public static String token=""; //Es el token que regres al suntactico de acuerdo al lexema mandado
   
   
   //En este metodo, de acuerdo a la palabra mandada regresa el token correspondiente
   //Se asigna a la cadena 'token' y es la que se manda con el return del final
   public static String revisar(String palabra){
      int c1=0;
      int c2=palabra.length()-1;
      int x=0;
      int y=palabra.length()-1;
      
      String cad="";
      String npal="";
      boolean tiene1=false;
      boolean tiene2=false;
      boolean k=true;
      
      //Comparacion de tokens de acuerdo al lexema leido
      
      
      if(palabra.charAt(c1)=='"'&&palabra.charAt(c2)=='"'){
         cad="\"";
         token=cadena;
         //AnLex+=("\t"+palabra);
         k=false;
      }
      
      else if(palabra.charAt(c1)=='\''&&palabra.charAt(c2)=='\''){
         tiene1=true;
         cad="'";
         token=caracter;
         //AnLex+=("\t"+palabra);
         k=false;
      }
      
      for(;x<=y;x++){
         npal+=palabra.charAt(x);
      }
      
      if(k){    
      if(npal.equals(repite)){
         token=repite;
         
      }
      else if(palabra.equals("(")){
         token=param1;
         
      }
      else if(palabra.equals(")")){
         token=param2;
         
      }
      else if(npal.equals(hacer)){
         token=hacer;
         
      }
      else if(npal.equals(mientras)){
         token=mientras;
         
      }
      else if(npal.equals(dec)){
         token="Declaracion";
         //ban1=true;
      }
      else if((npal.matches("^-?[0-9]+[.]?[0-9]+"))||(npal.matches("^-?[0-9]+"))){
         token=numero;
      }
      
      else if(npal.matches("^[<>]=?$") || npal.matches("^[!=]=$")){
         token=operador;
      }
      else if(npal.equals("Si") || npal.equals("Sino")){
         token=desicion;
      }
      else if(npal.equals("num") || npal.equals("bool")|| npal.equals("cad")|| npal.equals("car")){
         token=tipo;
         //ban2=true;
         //ant=npal;
      }
      else if(npal.equals("Imprime")){
         token=(imprime);
      }
      else if(npal.equals("&")){
         token=agregacion;
         /*if(ban3==true){
             ban1=true;
             ban2=true;
             ban3=false;             
         }
*/
      }
      else if(npal.equals("Lee")){
         token=lee;
      }
      
      //Revision del lexeman con respecto a tokens
      else if(npal.equals("=")){
         token=(asignacion);
      }
      else if(npal.equals(";")){
         token=(fina);
       //  errban=false;
      }
      else if(npal.matches("^[a-zA-Z]\\w*$")){
         token=variable;
        /* ban3=true;
         if(ban1==true&&ban2==true){
         RevVariable(npal);
         ban1=false;
         ban2=false;
         
         }
         */
      }
      //Indicación de error por default al no coincidir con ningun token
      else{
         token=(error);
      }
      
      }
      
          
      
      return(token);
   }
}
