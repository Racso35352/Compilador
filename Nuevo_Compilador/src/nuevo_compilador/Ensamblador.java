/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_compilador;

import java.io.*;

/**
 *
 * @author Oscar
 */
public class Ensamblador {
    
    public static String cadPila="";
    public static String cadDatos="";
    public static String cc="";
    public static String cadExtra="";
    public static int cont =1;
    
    public static FileWriter fichero = null;
    public static PrintWriter pw = null;
    
    public static void empezar(Automatas auto) {
        try{
         fichero = new FileWriter("C:\\Users\\Oscar\\Desktop\\masm\\Ens.asm");
         pw = new PrintWriter(fichero);
         
         
         
         
         pila();
         abrir_datos();
         datos(auto);
         cerrar_datos();
         extra();
         abrir_codigo();
         codigo(auto);
         cerrar_codigo();
         
         
         String pil[]=cadPila.split("\n");
         for(int x=0;x<pil.length;x++){
             pw.println(pil[x]);
         }
         
         String dat[]=cadDatos.split("\n");
         for(int x=0;x<dat.length;x++){
             pw.println(dat[x]);
         }
         
         String ex[]=cadExtra.split("\n");
         for(int x=0;x<ex.length;x++){
             pw.println(ex[x]);
         }
                 
         String cod[]=cc.split("\n");
         for(int x=0;x<cod.length;x++){
             pw.println(cod[x]);
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
}
    
    
    
    public static void datos(Automatas auto){
        Nodo linea=auto.tablaA.obtener();
        char n=34;
        String npal=linea.val;
        if(linea!=null){
            while(linea!=null){
                npal=linea.val;
                if(linea.tipo.equals("cad")){
                    String np[]=npal.split("\"");
                    npal="";
                    for(int x=0;x<np.length;x++)npal+=np[x];
                }
            cadDatos+=linea.nom+" db "+n+npal+"$"+n+"\n";
            linea=linea.pointer;
            }
        }
        
        }
        
    
    public static void codigo(Automatas auto){
        Nodo linea=auto.tablaA.obtener();
        
               
        if(linea!=null){
            while(linea!=null){
                
                
            cc+="mov ah,2\n" +
"mov bh,0\n" +
"mov dh,"+cont+"\n" +
"mov dl,35\n" +
"int 10h\n" +
"\n" +
"LEA dx,"+linea.nom+"\n" +
"mov ah,09\n" +
"int 21H\n";
            cont ++;
            linea=linea.pointer;
            }
        }
    }
    
  
    public static void pila(){
        cadPila+="pila segment para stack 'stack'\n";
        cadPila+="db 1000 dup(?)\n";
        cadPila+="pila ends\n";
    }
    
    public static void extra(){
        cadExtra+="extra segment para public 'data'\n";
        cadExtra+="extra ends\n";
    }
    
    public static void abrir_datos(){
        cadDatos+="datos segment para public 'data'\n";
    }
    
    public static void cerrar_datos(){
        cadDatos+="datos ends\n";
    }
       
    public static void abrir_codigo(){
        cc+="assume	cs:codigo, ds:datos, ss:pila,	es:extra\n";
        cc+="public p0\n";
        cc+="codigo segment para public 'code'\n";
        cc+="p0	proc far \n";
        cc+="push ds\n";
        cc+="mov ax, 0\n";
        cc+="push ax\n";
        cc+="mov ax,datos\n";
        cc+="mov ds,ax\n";
        cc+="mov ax, extra\n";
        cc+="mov es,ax\n";
        cc+="mov ah,06\n" +
"	mov al,0\n" +
"	mov bh,7\n" +
"	mov ch,00\n" +
"	mov cl,0\n" +
"	mov dh,24\n" +
"	mov dl,79\n" +
"	int 10h\n";
        
    }
    
    public static void cerrar_codigo(){
        cc+="	ret\n" +
"p0	endp\n" +
"\n" +
"\n" +
"codigo ends\n" +
"	end p0\n";
    }
    
    
            
}
