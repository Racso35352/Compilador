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
            else System.out.println("Linea "+cont+ "     Error de sintaxis ");
            
        }
        else{
            System.out.println("Linea "+cont+ "     Error de termino de linea, ausencia de ; ");
        }
        
        
        if(e==1)declaracion(cont);
        
    }
    
    
    
    public static void declaracion(int cont){
      
      int a=token.size();
      boolean j=false;
      boolean h=false;
      
      
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
            else c=8;
        
            
            break;
          case 2:
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Variable")) c=3;
            else c=8;
             
            break;
            
            case 3:
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")){
                c=4;
                j=true;
                k=true;
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
            
            if(k==false)System.out.println("Linea "+cont+ "     Error en la declaracion de variables ");
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
}
