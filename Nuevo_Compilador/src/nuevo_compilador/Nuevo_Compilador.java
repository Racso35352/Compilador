/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_compilador;
import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Oscar
 */
public class Nuevo_Compilador {

    public static FileWriter fichero = null;
    public static PrintWriter pw = null;
    public static int cnt=0;
    public static int cont=0;
    public static String imp="";
    public static List<String> tpalabras = new ArrayList<String>();
    public static int np=0;
    
    
   public static Lexico lex=new Lexico();
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
      //String ori=Origen.getText();
      String ori="p2";
      
      //String dest=Destino.getText();
      String dest="n";
      
      try{
         fichero = new FileWriter("C:\\Users\\Oscar\\Documents\\GitHub\\Compilador\\Pruebas\\"+dest+".txt");
         pw = new PrintWriter(fichero);
      
      
         File archivo = null;
         FileReader fr = null;
         BufferedReader br = null;
         
         archivo = new File ("C:\\Users\\Oscar\\Documents\\GitHub\\Compilador\\Pruebas\\"+ori+".txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         int cant=0;
         int cont=0;
         String newlinea="";
         
         String linea;
         //System.out.println("Linea\t\tToken\t\t\t\tLexema");//Impresión de la tabla
         while((linea=br.readLine())!=null){
             
            cnt++;
            tpalabras.clear();
            //errban=true;
            //ArOrig+=cnt+": " +linea+"\n";
            mat = comentario.matcher(linea);
            if (mat.matches()==false){
               for(int x=0;x<(linea.length()-1);x++){
                  if(linea.charAt(x)=='/'&&linea.charAt(x+1)=='/'){
                     for(int y=0;y<x;y++){
                        newlinea+=linea.charAt(y);//Creación de la nueva linea sin los comentarios
                     }
                     linea=newlinea;
                     newlinea="";  
                  }
               }
               newlinea="";
                        
               linea=revisarespacios(linea);
               //Eliminación de espacios
                                
               int f=linea.length();
               for(int x=0;x<(linea.length()-1);x++){
                  if(linea.charAt(x)==' '&&linea.charAt(x+1)==' '){      
                  }
                  else{
                     newlinea+=linea.charAt(x);
                     if(x==(f-2)){
                        if(linea.charAt(x+1)!=' ')
                           newlinea+=linea.charAt(x+1);
                     }
                  }                        
               }
               linea=newlinea;
               newlinea="";
                   
               cont++;
               //ban1=false;
               //ban2=false;
               //ban3=false;
               int c1=0;
               int c2=0;
               String palabra="";
               
               //En este ciclo comienza a depurar el codigo
               //Y manda los lexemas al Lexico
               for(int x=0;x<linea.length();x++){
                  
                  
                  if(linea.charAt(x)==' '){
                        
                     c2=x-1;
                     for(int y=c1;y<=c2;y++){
                        palabra+=linea.charAt(y);
                     }
                     c1=x+1;
                     imp+=lex.revisar(palabra)+"\n";//Aqui guardo en la cadena el token recibido el lexico nomas para saber que tipo es y si funciona o no
                     tpalabras.add(lex.revisar(palabra));
                     /* Lo de las clases que no quiere alexius
                     revClass(Ant,palabra);
                     Ant=palabra;
                     */
                     palabra="";        
                  }
                  if(x==linea.length()-1){
                        
                     for(int y=c1;y<linea.length();y++){
                        palabra+=linea.charAt(y);
                     }
                     c1=x+1;
                     
                     
                     imp+=lex.revisar(palabra)+"\n";//Aqui guardo en la cadena el token recibido el lexico nomas para saber que tipo es y si funciona o no
                     /* Lo de las clases que no quiere alexius
                     revClass(Ant,palabra);
                     Ant=palabra;
                     */
                     tpalabras.add(lex.revisar(palabra));
                     palabra="";        
                  }
                  
               } 
               Automatas.revisar(cont,tpalabras);
               
  //AQUI MERO!!!!! 
  pw.println("" + linea);//Guardar la linea en el nuevo archivo generado
                  //ArNew=ArNew+linea+"\n";
                  
                  
            }  /*   
            if(k5==false){
                System.out.println("Error en linea "+cnt+": Codigo fuera de la clase");
            }
            else{ */   
            //if(errban==true)System.out.println("Error en linea "+cnt+": Error de termino de linea, ausencia de ;");
            //}
            //if(ban1==true&&ban2==true&&errban==false)System.out.println("Error en linea "+cnt+": Error de sintaxis ");
         }
         
       
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
            if (null != fichero)
               fichero.close();//Cerrar y finalizar el archivo.
         } catch (Exception e2) {
            e2.printStackTrace();
         }           
         
      }
      
      ///////////////////////////////System.out.println(imp);
      /*
      int cc=variables.size();
      int cc2=0;
      if (k5==true){
      for(int x=0;x<cc;x++){
         
         newlin=variables.get(x);
         sintac.append(newlin.get(0)+"\t"+newlin.get(1)+"\t"+newlin.get(2)+"\t"+newlin.get(3)+"\n");
         
      }
      }
      lexico.append(ArOrig);
*/
    }
    
    public static String revisarespacios(String linea){
      int x=0;
      int y=linea.length();
      int c=0;
      String newlinea="";
      for(x=0;x<y;x++){
         if(linea.charAt(x)==' '||linea.charAt(x)==(char)9)c++;
         else 
            break;
      }
      for(;c<y;c++){
         newlinea+=linea.charAt(c);
      }
      return newlinea;
   }
}
