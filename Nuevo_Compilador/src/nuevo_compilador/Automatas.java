/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_compilador;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Oscar
 */
public class Automatas {
    
    public static String consola="";
    public static int f=0;   
    public static int e=0;  
    public static List<String> token = new ArrayList<String>();
    
    public static List<String> lex = new ArrayList<String>();
    public static TabS tablaA= new TabS();
    
    public static void revisar(int cont,List<String> tokens,List<String> lexs){
        f=tokens.size();
        e=0;
        token=tokens;
        lex=lexs;
        if(tokens.get(f-1).equals("Final")){
            
            if(tokens.get(0).equals("Declaracion"))e=1;
            else if(tokens.get(0).equals("Imprime"))e=2;
            
            else System.out.println("Linea "+cont+ "     Error en palabra reservada ");
            
        }
        else{
            System.out.println("Linea "+cont+ "     Error de termino de linea, ausencia de ; ");
        }
        
        
        if(e==1)declaracion(cont);
        if(e==2)impresion(cont);
        
        
    }
    
    
    
    public static void declaracion(int cont){
      
      int a=token.size();
      boolean j=false;
      boolean h=false;
      
      boolean a1 = false;
      boolean a2 = false;
      boolean a3 = false;
      boolean a4 = false;
      boolean a5 = false;
      
      int x=1;
      int c=1;
      String g="";
      boolean k=false;
      
      
      do{
          
         
         switch(c){
          case 1:
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Tipo")) c=2;
            else if(token.get(x).equals("Final")){
                c=8;
                a5=true;
            }
            else c=8;
        
            
            break;
          case 2:
              a1=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Variable")) c=3;
            else if(token.get(x).equals("Final")){
                c=8;
                a4=true;
            }
            else c=8;
            break;
            
            case 3:
                a2=true;
                a3=false;
            if(x==a){
               k=false;
               
            }
            else if(token.get(x).equals("Final")){
                c=4;
                j=true;
            }
            else if(token.get(x).equals("Agregacion")) c=5;
            else if(token.get(x).equals("Asignacion")) c=6;
            else c=8;
            
              
            break;
            
              case 4:
                
            if(x==a){
               k=true;
            }
            else c=8;
            
            break;
            
            case 5:
                a3=true;
                j=true;
                
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Variable")) c=3;
            else c=8;
             
            break;
                
            case 6:
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Numero")) c=7;
            else c=8;
             
            break;
            case 7:
                h=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")) c=4;
            else c=8;
            
             
            break;
            
            case 8:
            if(x==a){
               k=false;
            }
            else c=8;
             
            break;
            default: 
               System.out.println("\nError de Sintaxis");
            break;
            
            }
            
            if(k==false)g="error";
            else if(k==true)g="Aceptar";
             
             
            x++;
            }while(x<=a);
            
            if(k==false){
                if (a5==true)System.out.println("Linea "+cont+ "     Error: No agrego tipo de dato");
                else if(a1==false) System.out.println("Linea "+cont+ "     Error en el tipo de dato ");
                else if (a1==true&&a4==true)System.out.println("Linea "+cont+ "     Error: No agrego nombre de variable");
                else if(a1==true&&a2==false)System.out.println("Linea "+cont+ "     Error: Nombre de variablea no valido ");
                else if (a1==true&&a2==true&&a3==true)System.out.println("Linea "+cont+ "     Error: Se espera otra variable ");
               // System.out.println("Linea "+cont+ "     Error en la declaracion de variables ");
            }
            if(k==true){
                if(j==true){
                    for(int m=0;m<a;m++){
                        if(token.get(m).equals("Variable")){
                            if(tablaA.buscar(lex.get(m))==null)tablaA.agregar(lex.get(m),lex.get(1),"0","N");
                            else System.err.println("Linea "+cont+ "     Variable ya declarada");
                        }
                    }
                }
                
                if(h==true){
                    
                    if(tablaA.buscar(lex.get(2))==null)tablaA.agregar(lex.get(2),lex.get(1),lex.get(4),"N");
                    else System.err.println("Linea "+cont+ "     Variable ya declarada");
                }
            }
    }
    
    
     public static void impresion(int cont){
      
      int a=token.size();
      boolean j=false;
      boolean h=false;
      
      boolean a1 = false;
      boolean a2 = false;
      boolean a3 = false;
      boolean a4 = false;
      boolean a5 = false;
      boolean a7 = false;
      
      int x=1;
      int c=1;
      String g="";
      boolean k=false;
      
      
      do{
          
         
         switch(c){
          case 1:
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Parametro1")) c=2;
            else if(token.get(x).equals("Final")){
                c=7;
                a5=true;
            }
            else c=7;
        
            
            break;
          case 2:
              a1=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Variable")) c=3;
            else if(token.get(x).equals("Cadena")||token.get(x).equals("Caracter")) c=4;
            else if(token.get(x).equals("Parametro2")){
                c=7;
                a4=true;
            }
            
            else if(token.get(x).equals("Final")){
                c=7;
                
            }
            else c=7;
            break;
            
            case 3:
                a2=true;
                a7=true;
            if(x==a){
               k=false;
               
            }
            else if(token.get(x).equals("Parametro2")){
                c=5;    
            }
            else if(token.get(x).equals("Final")){
                c=7;
                a3=true;
            }
            else c=7;
            
              
            break;
            
              case 4:
                a2=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Parametro2")){
                c=5;       
            }
            else if(token.get(x).equals("Final")){
                c=7;
                a3=true;
            }
            else c=7;
            
            break;
            
            case 5:
                
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")){
                c=6;
                j=true;
            }
            else c=7;
             
            break;
                
            case 6:
            if(x==a){
               k=true;
            }
            else c=7;
             
            break;
            
            
            case 7:
            if(x==a){
               k=false;
            }
            else c=7;
             
            break;
            default: 
               System.out.println("\nError de Sintaxis");
            break;
            
            }
            
            if(k==false)g="error";
            else if(k==true)g="Aceptar";
             
             
            x++;
            }while(x<=a);
            
            if(k==false){
                if (a5==true)System.out.println("Linea "+cont+ "     Error al indicar la cadena");
                else if(a1==false) System.out.println("Linea "+cont+ "     Error: Ausencia de '(' ");                
                else if (a4==true)System.out.println("Linea "+cont+ "     Error: No agrego cadena a imprimir");
                else if(a2==false)System.out.println("Linea "+cont+ "     Error: Cadena no valida ");                
                else if (a3=true)System.out.println("Linea "+cont+ "     Error: Ausencia de ')' ");
                //else if (a1==true&&a2==true&&a3==true)System.out.println("Linea "+cont+ "     Error: Se espera otra variable ");
               // System.out.println("Linea "+cont+ "     Error en la declaracion de variables ");
            }
            if(k==true){
                if(a7==true){
                    if(tablaA.buscar(lex.get(2))==null)System.out.println("Linea "+cont+ "     Error: La variable no se encuentra en la tabla de simbolos");
                            else consola+=(tablaA.buscar(lex.get(2)).val)+"\n";
                }
                else consola+=(lex.get(2)+"\n");
            }
    }
}
