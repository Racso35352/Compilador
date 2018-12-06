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
    
    public static boolean Ensamblar=true;
    public static Semantico seman= new Semantico();
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
            else if(tokens.get(0).equals("Lectura"))e=3;
            else if(tokens.get(0).equals("Hacer"))e=4;            
            else if(tokens.get(0).equals("Desicion"))e=5;         
            else if(tokens.get(0).equals("Variable"))e=6;
            else if(tokens.get(0).equals("Repite"))e=7;
            

            else {
                System.out.println("Linea "+cont+ "     Error en palabra reservada ");
                Ensamblar=false;
            }

        }
        else{
            System.out.println("Linea "+cont+ "     Error de termino de linea, ausencia de ; ");
            Ensamblar=false;
        }


        if(e==1)declaracion(cont);
        if(e==2)impresion(cont);
        if(e==3)lectura(cont);
        if(e==4)ciclo(cont);
        if(e==5)des(cont);
        if(e==6)vari(cont,lex,token);
        if(e==7)rep(cont);
        


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
      boolean a6 = true;
      
      boolean tnum = false;
      boolean tcad = false;
      boolean tcar = false;
      boolean tbool = false;
      
      boolean btipo = false;

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
            else if(lex.get(x).equals("num")) c=2;
            else if(lex.get(x).equals("cad")) c=9;
            else if(lex.get(x).equals("car")) c=11;
            else if(lex.get(x).equals("bool")) c=13;
            else if(token.get(x).equals("Final")){
                c=8;
                a5=true;
            }
            else c=8;


            break;
          case 2:
              a1=true;
              tnum=true;
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
            
            case 9:
              a1=true;
              tcad=true;
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
            
            case 11:
              a1=true;
              tcar=true;
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
            
            case 13:
              a1=true;
              tbool=true;
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
            else if(token.get(x).equals("Cadena")) c=10;
            else if(token.get(x).equals("Caracter")) c=12;
            else if(token.get(x).equals("Booleano")) c=14;
            else if(token.get(x).equals("Final")){
                c=8;
                a6=false;
            }
            else c=8;

            break;
            case 7:
                
                h=true;
                if(tnum==true)btipo=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")){
                c=4;
                
            }
            else c=8;


            break;
            
            case 10:
                h=true;
                if(tcad==true)btipo=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")){
                c=4;
                
            }
            else c=8;

            break;
            
            case 12:
                h=true;
                if(tcar==true)btipo=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")){
                c=4;
                
            }
            else c=8;

            break;
            
            case 14:
                h=true;
                if(tbool==true)btipo=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")){
                c=4;
                
            }
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
                Ensamblar=false;
                if (a5==true)System.out.println("Linea "+cont+ "     Error: No agrego tipo de dato");
                else if(a1==false) System.out.println("Linea "+cont+ "     Error en el tipo de dato ");
                else if (a1==true&&a4==true)System.out.println("Linea "+cont+ "     Error: No agrego nombre de variable");
                else if(a1==true&&a2==false)System.out.println("Linea "+cont+ "     Error: Nombre de variablea no valido ");
                else if (a1==true&&a2==true&&a3==true)System.out.println("Linea "+cont+ "     Error: Se espera otra variable ");
               else if (a6==false)System.out.println("Linea "+cont+ "     Error: Falta asignar valor a la variable ");
                else if (btipo==false)System.out.println("Linea "+cont+ "     Error: Tipo de variable incorrecto ");
                // System.out.println("Linea "+cont+ "     Error en la declaracion de variables ");
            }
            if(k==true){
                if(j==true){
                    for(int m=0;m<a;m++){
                        if(token.get(m).equals("Variable")){
                            if(tablaA.buscar(lex.get(m))==null)tablaA.agregar(lex.get(m),lex.get(1),"0","N");
                            else {
                                System.err.println("Linea "+cont+ "     Variable ya declarada");
                                Ensamblar=false;
                            }
                        }
                    }
                }

                if(h==true){
                    if(btipo==false){
                        Ensamblar=false;
                        System.out.println("Linea "+cont+ "     Error: Tipo de variable incorrecto ");
                    }
                    else if(tablaA.buscar(lex.get(2))==null)tablaA.agregar(lex.get(2),lex.get(1),lex.get(4),"N");
                    else {
                        Ensamblar=false;
                        System.err.println("Linea "+cont+ "     Variable ya declarada");
                    }
                }
            }
    }


     public static void impresion(int cont){

      int a=token.size();
      boolean j=false;
      boolean h=false;

      boolean a1 = false;
      boolean a2 = false;
      boolean a22 = false;
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
                Ensamblar=false;
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
                    if(tablaA.buscar(lex.get(2))==null){
                        Ensamblar=false;
                        System.out.println("Linea "+cont+ "     Error: La variable no se encuentra en la tabla de simbolos");
                    }
                            else consola+=(tablaA.buscar(lex.get(2)).val)+"\n";
                }
                else consola+=(lex.get(2)+"\n");
            }
    }

     public static void lectura(int cont){
         int a=token.size();
         int c=1; //caso
         int x=1;

         boolean f1=false; //bandera de aceptación
         boolean f2=false; //bandera de si ya se ha desplegado mensaje de error

         do{
             switch(c){
                 case 1:
                     if(token.get(x).equals("Variable")){
                         if(tablaA.buscar(lex.get(x))!=null){
                             c=2;
                         }
                         else{
                             f2=true;
                             System.out.println("Linea "+cont+"     Error, variable no declarada");
                             Ensamblar=false;
                             c=3;
                         }
                     }
                     else{
                         f2=true;
                         System.out.println("Linea "+cont+"     Error de sintaxis, se espera una variable");
                         Ensamblar=false;
                         c=3;
                     }
                     break;
                 case 2:
                     if(token.get(x).equals("Final")){
                         f1=true;
                     }
                     c=3;
                     break;
                 case 3:
                     f1=false;
                     //Si entra a este caso, quiere decir que la sintaxis está mal
                     break;
                 default:
                     f2=true;
                     f1=false;
                    // System.out.println("Error de sintaxis");
             }
             x++;
         }while(x<a);

         if(f1==true){
             Nodo var=tablaA.buscar(lex.get(1));
             var.setLect("S");
             String tipo=var.getTipo();
             //System.out.println(cont+"  Aceptacion");
             //Bloque de aceptación, aqui ira la concatenacion en ensamblador
             //Se buscara el tipo de dato
         }
         else if(f2==false){
             System.out.println("Linea "+cont+"     Error de sintaxis");
             Ensamblar=false;
         }
     }
     
     public static void ciclo (int cont){
        int a= token.size();
        int c=1;
        int x=1;
        
        String type="";
        
        boolean f1=false;
        boolean f2=false;
        
        do{
            switch(c){
                case 1:
                        if(token.get(x).equals("Variable")){
                           if(tablaA.buscar(lex.get(x))!=null){
                            c=2;
                            type=tablaA.buscar(lex.get(x)).getTipo();
                        }
                        else{
                            f2=true;
                            System.out.println("Linea "+cont+"     Error, variable no declarada");
                            Ensamblar=false;
                            c=6;
                        }
                    }
                    else{
                        f2=true;
                        System.out.println("Linea "+cont+"     Error de sintaxis, se espera una variable");
                        Ensamblar=false;
                        c=6;
                     }
                        break;
                case 2:
                    if(token.get(x).equals("Operador")){
                        c=3;
                    }
                    else{
                        f2=true;
                        System.out.println("Linea "+cont+"      Error, se espera operador de decisión");
                        Ensamblar=false;
                        c=6;
                    }
                    break;
                case 3:
                    if(!token.get(x).equals("Final") && !token.get(x).equals("Variable")){
                    switch(type){
                        case "num":
                            if(token.get(x).equals("Numero")){
                                c=4;
                            }
                            else{
                                c=6;
                                f2=true;
                                System.out.println("Linea "+cont+"      Error, diferentes tipos de dato");
                                Ensamblar=false;
                            }
                            break;
                        case "cad":
                            if(token.get(x).equals("Cadena")){
                                c=4;
                            }
                            else{
                                c=6;
                                f2=true;
                                System.out.println("Linea "+cont+"      Error, diferentes tipos de dato");
                                Ensamblar=false;
                            }
                            break;
                        case "car":
                            if(token.get(x).equals("Caracter")){
                                c=4;
                            }else{
                                c=6;
                                f2=true;
                                System.out.println("Linea "+cont+"      Error, diferentes tipos de dato");
                                Ensamblar=false;
                            }
                            break;
                        case "bool":
                            if(token.get(x).equals("Booleano")){
                                c=4;
                            }
                            else{
                                c=6;
                                f2=true;
                                System.out.println("Linea "+cont+"      Error, diferentes tipos de dato");
                                Ensamblar=false;
                            }
                            break;
                        default: 
                            System.out.println("Linea "+cont+"       se espera una variable o tipo de dato");
                            Ensamblar=false;
                            f2=true;
                            c=6;
                    }
                    }
                    else if(token.get(x).equals("Variable")){
                        if(tablaA.buscar(lex.get(x)).getTipo().equals(type)){
                            c=4;
                        }
                        else{
                            c=6;
                            f2=true;
                            System.out.println("Linea "+cont+"      Error, diferentes tipos de dato");
                            Ensamblar=false;
                        }
                    }
                    else{
                        f2=true;
                        c=6;
                        System.out.println("Linea "+cont+"     Se espera una variable o tipo de dato");
                        Ensamblar=false;
                    }
                    break;
                case 4:
                    if(lex.get(x).equals("+") || lex.get(x).equals("-")){
                        c=5;
                    }
                    else if(!token.get(x).equals("Final")){
                        f2=true;
                        c=6;
                        System.out.println("Linea "+cont+"      Error, operador de conteo invalido, se espera '-' o '+'");
                        Ensamblar=false;
                    }
                    else{
                        f2=true;
                        c=6;
                        System.out.println("Linea "+cont+"      Error, falta operador de conteo");
                        Ensamblar=false;
                    }
                    break;
                case 5:
                    if(token.get(x).equals("Final")){
                        f1=true;
                    }
                    c=6;
                    break;
                case 6:
                    f1=false;
                    break;
                default:
                    f1=false;
                    f2=true;
            }
            x++;    
            }while(x<a);

        if(f1==true){
            
         }
         else if(f2==false){
             System.out.println("Linea "+cont+"     Error de sintaxis");
             Ensamblar=false;
         }
        
        }
     
     
     
     public static void des(int cont){

      int a=token.size();
      boolean j=false;
      boolean h=false;

      boolean a1 = false;
      boolean a2 = false;
      boolean a3 = false;
      boolean a4 = false;
      boolean a5 = false;
      boolean a7 = false;
      boolean a22 = false;
      boolean a23 = false;
      boolean a33 = false;
      boolean a43 = false;
      boolean a200 = false;
      boolean a300 = false;
      boolean a400 = false;
      

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
                c=9;
                a5=true;
            }
            else c=9;


            break;
          case 2:
              a1=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Variable")||token.get(x).equals("Cadena")||token.get(x).equals("Caracter")||token.get(x).equals("Numero")) c=3;
            
            else if(token.get(x).equals("Parametro2")){
                c=9;
                a4=true;
            }

            else if(token.get(x).equals("Final")){
                c=9;

            }
            else c=9;
            break;

            
            case 3:
                a2=true;
                a7=true;
            if(x==a){
               k=false;

            }
            else if(token.get(x).equals("Operador")){
                c=4;
            }
            else if(token.get(x).equals("Final")||token.get(x).equals("Parametro2")){
                c=9;
                a33=true;
            }
            else c=9;
            break;
            
            case 4:
                a23=true;
            if(x==a){
               k=false;

            }
            else if(token.get(x).equals("Variable")||token.get(x).equals("Cadena")||token.get(x).equals("Caracter")||token.get(x).equals("Numero")) c=5;
           
            
            
            else if(token.get(x).equals("Final")||token.get(x).equals("Parametro2")){
                c=9;
                a43=true;
            }
            else c=9;
            
            


            break;
            
            case 5:
                a22=true;
              
            if(x==a){
               k=false;

            }
            else if(token.get(x).equals("Parametro2")){
                c=6;
            }
            else if(token.get(x).equals("Final")){
                c=9;
                a3=true;
            }
            else c=9;


            break;

              case 6:
               a200=true;
            if(x==a){
               k=false;
            }
            else if(lex.get(x).equals("[")){
                c=7;
            }
            else if(token.get(x).equals("Final")){
                c=9;
                a300=true;
            }
            else c=9;

            break;

            case 7:
                a400=true;
            if(x==a){
               k=false;
            }
            else if(token.get(x).equals("Final")){
                c=8;
                j=true;
            }
            else c=9;

            break;

            case 8:
            if(x==a){
               k=true;
            }
            else c=9;

            break;


            case 9:
            if(x==a){
               k=false;
            }
            else c=9;

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
                Ensamblar=false;
                if (a5==true)System.out.println("Linea "+cont+ "     Error: No indico la desicion");
                else if(a1==false) System.out.println("Linea "+cont+ "     Error: Ausencia de '(' ");
                else if (a4==true)System.out.println("Linea "+cont+ "     Error: No agrego datos a comparar");
                else if(a2==false)System.out.println("Linea "+cont+ "     Error: Primer dato de la comparacion no valido ");
                else if(a33==true)System.out.println("Linea "+cont+ "     Error: Ausencia de operador para comparar ");
                else if(a23==false)System.out.println("Linea "+cont+ "     Error en el simbolo de comparacion ");
                else if(a43==true)System.out.println("Linea "+cont+ "     Error: Falta segundo elemento a comparar");
                
                else if(a22==false)System.out.println("Linea "+cont+ "     Error: Segundo dato de la comparacion no valido ");
                else if (a3==true)System.out.println("Linea "+cont+ "     Error: Ausencia de ')' ");
                else if (a200==false)System.out.println("Linea "+cont+ "     Error al declarar la desicion");
                
                else if(a300==true)System.out.println("Linea "+cont+ "     Error: Ausencia de simbolo [ ");
                else if(a400==false)System.out.println("Linea "+cont+ "     Error: No se inicia el bloque ");
                
                
                //else if (a1==true&&a2==true&&a3==true)System.out.println("Linea "+cont+ "     Error: Se espera otra variable ");
               // System.out.println("Linea "+cont+ "     Error en la declaracion de variables ");
            }
            /*
            if(k==true){
                if(a7==true){
                    if(tablaA.buscar(lex.get(2))==null)System.out.println("Linea "+cont+ "     Error: La variable no se encuentra en la tabla de simbolos");
                            else consola+=(tablaA.buscar(lex.get(2)).val)+"\n";
                }
                else consola+=(lex.get(2)+"\n");
            }
*/
    }
     
    
    public static void vari(int cont, List<String> lex,List<String> token){
        
        boolean x=seman.revisar(cont,lex,token);
        if(x==true){
            System.out.println("Si");
            seman.hacer(cont,lex,token,tablaA);
        }
        else {   
            System.out.println("No");
        }
        
    }
    
    

  public static boolean Ens(){
      return Ensamblar;
  }

  public static void rep(int cont){
      int x=1;
      if(tablaA.buscar(lex.get(x))!=null){
          
                        }
                        else{
                            System.out.println("Linea "+cont+"     Error, variable no declarada");
                            Ensamblar=false;
                        }
  }

}
