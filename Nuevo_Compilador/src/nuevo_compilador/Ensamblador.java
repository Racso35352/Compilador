/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_compilador;

import java.io.*;
import java.util.StringTokenizer;

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
    public static int s =1;
    public static int ss =25;
    public static int et = 0; //cuenta etiqueta, para que no se repita
    public static int net = 0; //cuenta nueva etiqueta, para que no se repita
    
    
    public static FileWriter fichero = null;
    public static PrintWriter pw = null;
    
    public static void empezar(Automatas auto) {
        try{
         fichero = new FileWriter("C:\\Users\\Oscar\\Documents\\Github\\Compilador\\masm\\Ens.asm"); //Aqui acomodale tu ruta a donde este tu carpeta masm, tambien acuerdate de instalar el DosBox, si no lo tienes dime y te lo paso
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
        
        Nodo linea=auto.tablaA.obtener();  //Obtiene el primer nodo de la tabla de simbolos
        cadDatos+="no_val DB 'Dato invalido, se espera un numero. Terminando programa$'\n";
        cadDatos+="e DB 'No se cumplio la condicion$'\n";

        if(linea!=null){
            while(linea!=null){
                if(linea.lect.equals("S")){ //Si la variable es de lectura
                    if(linea.tipo.equals("cad")){
                        cadDatos+=linea.nom+" DB 61,?,61 DUP(?)\n";
                   }
                    else if(linea.tipo.equals("num")){
                        cadDatos+=linea.nom+" DB 61,?,61 DUP(?)\n";
                    }
                }
                else{
                    if(linea.tipo.equals("cad")){
                        String cad=linea.val;
                        String cad1=cad.replace((char)34,(char)0);
                        cadDatos+=linea.nom+" DB '"+cad1+"$'\n";
                        
                    }
                    else if(linea.tipo.equals("num")){
                        String cad=linea.val;
                        cadDatos+=linea.nom+" DW "+cad+"\n";
                    }
                }
                /*
                npal=linea.val;
                if(linea.tipo.equals("cad")){
                    String np[]=npal.split("\"");
                    npal="";
                    for(int x=0;x<np.length;x++)npal+=np[x];
                }
            cadDatos+=linea.nom+" db "+n+npal+"$"+n+"\n";
            */
            linea=linea.pointer;
            }
        }
        
        //Probablemente aqui tambien le debas mover si es que utilizas instrucciones del tipo "n1 db 61,?,61 dup(?)"
        //Para leer creo que sera necesario pero como tu veas, si es asi aqui tambien se va a hacer un buen desmadre
        //Tu ve como lo haces para que te sea mas facil
        
        //Nodo linea=auto.tablaA.obtener();  //Obtiene el primer nodo de la tabla de simbolos
        /*
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
*/
        
        }
        
    
    public static void codigo(Automatas auto){
        Nodo linea;
        int cont =0;
        
            File archivo = new File ("C:\\Users\\Oscar\\Documents\\Github\\Compilador\\Pruebas\\n.txt");
            String line;
            try{
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            
           
                while((line=br.readLine())!= null){
                    StringTokenizer st = new StringTokenizer(line);
                    String pal=st.nextToken();//Almacena la instrucción
                    //System.out.println(line);

                    //int nbytes;
                    
                    if(!pal.equals("DEC")){
                    //linea=auto.tablaA.buscar(var); //Busca la variable en la tabla de simbolos
                    //String tipo =linea.tipo; //Obtiene el tipo de dato de la variable, esto es importante para saber el nbits que ocupa en memoria 
                    if(pal.equals("Lee")){
                        String var=st.nextToken();
                        linea=auto.tablaA.buscar(var);
                        String tipo=linea.tipo;
                        if(tipo.equals("cad")){
                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea dx,"+var+"\n"+
                                    "mov ah,0ah\n"+
                                    "int 21h\n";
                                cont++;
                            }
                        if(tipo.equals("num")){
                                cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea dx,"+var+"\n"+
                                    "mov ah,0ah\n"+
                                    "int 21h\n"+
                                    "lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "ciclo"+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "cmp dl,48\n"+
                                    "jb sale"+et+"\n"+
                                    "cmp dl,57\n"+
                                    "ja sale"+et+"\n"+
                                    "loop ciclo"+et+"\n"+
                                    "mov ax,byte ptr 0\n"+
                                    "jmp fin"+et+"\n"+
                                    "sale"+et+":\n"+
                                    "mov ax,1\n"+
                                    "fin"+et+":\n"+
                                    "cmp ax,byte ptr 1\n"+
                                    "jne val"+et+"\n"+
                                    "lea dx,no_val\n"+
                                    "mov ah,9\n"+
                                    "int 21h\n"+
                                    "mov ah,4ch\n"+
                                    "int 21h\n"+
                                    "val"+et+":\n";
                                cont++;
                                et++;
                        }
                    }
                    
                    else if(pal.equals("Repite")){
                        cc+=obtValor(st.nextToken());
                        net++;
                        cc+="mov cx, ax\n";
                         cc+=
	"mov dh,"+s+"\n";
        s++;
                        cc+="repite"+net+": \n";
                        cc+="push dx\n";
                        cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
                        line=br.readLine();
                        if(line!=null){
                            st = new StringTokenizer(line);
                            st.nextToken();
                            st.nextToken();
                        String var=st.nextToken();
                        linea=auto.tablaA.buscar(var);
                        String tipo=linea.tipo;
                            cc+="push cx\n";
                            if(linea.lect.equals("S")){
                            if(tipo.equals("cad")){
                                                           
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                            else if(tipo.equals("num")){
                                cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
        
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                        }
                        else{
                            if(tipo.equals("cad")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss-1)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea dx,"+var+"\n"+
                                        "mov ah,9\n"+
                                        "int 21h\n";
                                    cont++;
                            }
                            if(tipo.equals("num")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea bx,"+var+"\n"+
                                        "mov ax,[bx]\n"+
                                        "mov bp,byte ptr 0ah\n"+
                                        "mov cx,0\n"+
                                        "CI"+et+":\n"+
                                        "inc cx\n"+
                                        "mov dx,0\n"+
                                        "div bp\n"+
                                        "mov di,dx\n"+
                                        "push di\n"+
                                        "cmp ax,word ptr 0\n"+
                                        "jne CI"+et+"\n"+
                                        "jmp C2"+et+"\n"+
                                        "C2"+et+":\n"+
                                        "pop dx\n"+
                                        "add dl,30h\n"+
                                        "mov ah,2\n"+
                                        "int 21h\n"+
                                        "dec cx\n"+
                                        "cmp cx,0\n"+
                                        "jne c2"+et+"\n";
                                          
                                et++;
                                cont++;
                            }
                        }
                            cc+="pop cx\n";
                            cc+="pop dx\n";
                            cc+="inc dh\n";
                            
                            cc+="loop repite"+net+"\n"+
                                    "jmp fina\n";
                        }
                        else ;
                        
                    /*    
                    
                    line=br.readLine())!= null){
                    StringTokenizer st = new StringTokenizer(line);
                    String pal=st.nextToken();//Almacena la instrucción
                        
                        
                        
                        
                        cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                        
                    }
*/
                    }
                    else if(pal.equals("Imprime")){
                        st.nextToken();
                        String var=st.nextToken();
                        linea=auto.tablaA.buscar(var);
                        String tipo=linea.tipo;
                        if(linea.lect.equals("S")){
                            if(tipo.equals("cad")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                            else if(tipo.equals("num")){
                                cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
        
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                        }
                        else{
                            if(tipo.equals("cad")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss-1)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea dx,"+var+"\n"+
                                        "mov ah,9\n"+
                                        "int 21h\n";
                                    cont++;
                            }
                            if(tipo.equals("num")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea bx,"+var+"\n"+
                                        "mov ax,[bx]\n"+
                                        "mov bp,byte ptr 0ah\n"+
                                        "mov cx,0\n"+
                                        "CI"+et+":\n"+
                                        "inc cx\n"+
                                        "mov dx,0\n"+
                                        "div bp\n"+
                                        "mov di,dx\n"+
                                        "push di\n"+
                                        "cmp ax,word ptr 0\n"+
                                        "jne CI"+et+"\n"+
                                        "jmp C2"+et+"\n"+
                                        "C2"+et+":\n"+
                                        "pop dx\n"+
                                        "add dl,30h\n"+
                                        "mov ah,2\n"+
                                        "int 21h\n"+
                                        "dec cx\n"+
                                        "cmp cx,0\n"+
                                        "jne c2"+et+"\n";
                                          
                                et++;
                                cont++;
                            }
                        }
                    }
                    else if(pal.equals("Si")){
                        st.nextToken();
                        String comparador1=st.nextToken();
                        linea=auto.tablaA.buscar(comparador1);
                        String operador=st.nextToken();
                        String comparador2=st.nextToken();
                        String deci="";
                        
                        cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
           
                        if(operador.equals("<")){
                            deci="JGE";
                        }
                        else if(operador.equals(">")){
                            deci="JLE";
                        }
                        else if(operador.equals(">=")){
                            deci="JL";
                        }
                        else if(operador.equals("<=")){
                            deci="JG";
                        }
                        else if(operador.equals("==")){
                            deci="JNE";
                        }
                        else if(operador.equals("!=")){
                            deci="JE";
                        }
        
        
                        if(linea!=null){// si el primero es variable
                            if(auto.tablaA.buscar(comparador2)!=null){//Si el comparador2 es variable
                                Nodo linea2 = auto.tablaA.buscar(comparador2);
                                if(linea.lect.equals("S") && linea2.lect.equals("S")){ //Si ambos son de lectura
                                    cc+=obtValor(comparador1);
                                    cc+="push ax\n";
                                    cc+=obtValor(comparador2);
                                    cc+="pop dx\n";
                                }
                                else if(linea.lect.equals("S") && linea2.lect.equals("N")){
                                    cc+=obtValor(comparador1);
                                    cc+="lea bx,"+comparador2+"\n"+
                                        "mov dx,[bx]\n"; 
                                }
                                else if(linea.lect.equals("N") && linea2.lect.equals("S")){
                                    cc+=obtValor(comparador2);
                                    cc+="mov dx,ax\n"+
                                        "lea bx,"+comparador1+"\n"+
                                        "mov ax[bx]\n";
                                }
                                else{//Si ambos estan en memoria
                                    cc+="lea bx,"+comparador1+"\n"+
                                        "mov ax,[bx]\n"+
                                        "lea bx,"+comparador2+"\n"+
                                        "mov dx,[bx]\n"; 
                                }
                            }
                            else{//Si el comparador2 no es variable
                                if(linea.lect.equals("S")){
                                    cc+=obtValor(comparador1);
                                    cc+="mov dx,"+comparador2+"\n";
                                }
                                else{
                                    cc+="lea bx,"+comparador1+"\n"+
                                        "mov ax,[bx]\n"+
                                        "mov dx,"+comparador2+"\n";
                                }
                            } 
                        }
                        else{//el primero no es variable
                            if(auto.tablaA.buscar(comparador2)!=null){//Si e comparador2 es variable
                                Nodo linea2 = auto.tablaA.buscar(comparador2);
                                if(linea2.lect.equals("S")){ //Si el comp2 es de lectura
                                    cc+=obtValor(comparador2);
                                    cc+="mov dx,ax\n"+
                                        "mov ax,"+comparador1+"\n";
                                }
                                else{//si comp2 no es de lectura
                                    cc+="lea bx,"+comparador2+"\n"+
                                        "mov dx,[bx]\n"+
                                        "mov ax,"+comparador1+"\n";
                                }
                            }
                            else{//Si ambos no son variables
                                cc+="mov ax,"+comparador1+"\n"+
                                    "mov dx,"+comparador2+"\n";
                            }
                        }
                        cc+="cmp ax,dx\n"+
                            deci+" salto"+et+"\n";
                        int thisEt=et;
                        //aqui va para traducir la siguiente linea
                        line=br.readLine();
                        nextLine(line,linea,auto,br);//metodo para la siguiente instrucción
                        
                        
                        //si la condicion no se cumple, se salta aca
                        cc+="jmp fina\n";
                        cc+="salto"+thisEt+":\n";
                        cc+="lea dx,e\n"+
                                "mov ah,9\n"+ "int 21h\n";
                        cont++;
                        et++;
                    }
                        
                    }
            }//while
                //System.out.println(cont);
        }//try
        catch(IOException e){
            
        }
    }
    
    public static String obtValor(String numero){
        String block="";
        /*
        block+="lea bx,["+var+"]\n"+
               "inc bx\n"+
               "mov cl,[bx]\n"+
               "mov ch,0\n"+
               "push cx\n"+
               "CDR"+et+":\n"+
               "inc bx\n"+
               "sub [bx], byte ptr 30h\n"+
               "loop CDR"+et+"\n"+
               "lea bx,"+var+"+2\n"+
               "mov al,[bx]\n"+
               "mov di,0ah\n"+
               "mov ah,0\n"+
               "pop cx\n"+
               "push cx\n"+
               "dec cx\n"+
               "MYS"+et+":\n"+
               "inc bx\n"+
               "mul di\n"+
               "mov dl,[bx]\n"+
               "mov dh,0\n"+
               "add ax,dx\n"+
               "loop MYS"+et+"\n"+
               "SC"+et+":\n"+
               "lea bx,["+var+"+2]\n"+
               "pop cx\n"+
               "DIVI"+et+":\n"+
               "add [bx],byte ptr 30h\n"+
               "loop DIVI"+et+"\n";
        */
        	block+="lea bx,"+numero+"\n"+
	"inc bx\n"+
	"mov cl,[bx]\n"+
	"mov ch,0\n"+
	"push cx\n"+

"cdr"+et+":\n"+
	"inc bx\n"+
	"sub [bx], byte ptr 30h\n"+
	
	"loop cdr"+et+"\n"+
	"lea bx,"+numero+"+2\n"+
	"mov al,[bx]\n"+
	"mov di,0ah\n"+
	"mov ah,0\n"+
	
	"pop cx\n"+
	"cmp cl,1\n"+
	"je sale"+et+"\n"+
	"dec cx\n"+
	
"mys"+et+":\n"+
	"inc bx\n"+
	"mul di\n"+
	"mov dl,[bx]\n"+
	"mov dh,0\n"+
	"add ax,dx\n"+
	"loop mys"+et+"\n"+
   
"sale"+et+":\n";                     
               
        et++;
        return block;
    }
    
    public static void nextLine(String line,Nodo linea,Automatas auto,BufferedReader br){
        StringTokenizer st = new StringTokenizer(line);
                    String pal=st.nextToken();//Almacena la instrucción
                    //System.out.println(line);

                    //int nbytes;
                    try{
                    if(!pal.equals("DEC")){
                    //linea=auto.tablaA.buscar(var); //Busca la variable en la tabla de simbolos
                    //String tipo =linea.tipo; //Obtiene el tipo de dato de la variable, esto es importante para saber el nbits que ocupa en memoria 
                    if(pal.equals("Lee")){
                        String var=st.nextToken();
                        linea=auto.tablaA.buscar(var);
                        String tipo=linea.tipo;
                        if(tipo.equals("cad")){
                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea dx,"+var+"\n"+
                                    "mov ah,0ah\n"+
                                    "int 21h\n";
                                cont++;
                            }
                        if(tipo.equals("num")){
                                cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea dx,"+var+"\n"+
                                    "mov ah,0ah\n"+
                                    "int 21h\n"+
                                    "lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "ciclo"+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "cmp dl,48\n"+
                                    "jb sale"+et+"\n"+
                                    "cmp dl,57\n"+
                                    "ja sale"+et+"\n"+
                                    "loop ciclo"+et+"\n"+
                                    "mov ax,byte ptr 0\n"+
                                    "jmp fin"+et+"\n"+
                                    "sale"+et+":\n"+
                                    "mov ax,1\n"+
                                    "fin"+et+":\n"+
                                    "cmp ax,byte ptr 1\n"+
                                    "jne val"+et+"\n"+
                                    "lea dx,no_val\n"+
                                    "mov ah,9\n"+
                                    "int 21h\n"+
                                    "mov ah,4ch\n"+
                                    "int 21h\n"+
                                    "val"+et+":\n";
                                cont++;
                                et++;
                        }
                    }
                    
                    else if(pal.equals("Repite")){
                        cc+=obtValor(st.nextToken());
                        net++;
                        cc+="mov cx, ax\n";
                         cc+=
	"mov dh,"+s+"\n";
        s++;
                        cc+="repite"+net+": \n";
                        cc+="push dx\n";
                        cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
                        line=br.readLine();
                        if(line!=null){
                            st = new StringTokenizer(line);
                            st.nextToken();
                            st.nextToken();
                        String var=st.nextToken();
                        linea=auto.tablaA.buscar(var);
                        String tipo=linea.tipo;
                            cc+="push cx\n";
                            if(linea.lect.equals("S")){
                            if(tipo.equals("cad")){
                                                           
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                            else if(tipo.equals("num")){
                                cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
        
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                        }
                        else{
                            if(tipo.equals("cad")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss-1)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea dx,"+var+"\n"+
                                        "mov ah,9\n"+
                                        "int 21h\n";
                                    cont++;
                            }
                            if(tipo.equals("num")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea bx,"+var+"\n"+
                                        "mov ax,[bx]\n"+
                                        "mov bp,byte ptr 0ah\n"+
                                        "mov cx,0\n"+
                                        "CI"+et+":\n"+
                                        "inc cx\n"+
                                        "mov dx,0\n"+
                                        "div bp\n"+
                                        "mov di,dx\n"+
                                        "push di\n"+
                                        "cmp ax,word ptr 0\n"+
                                        "jne CI"+et+"\n"+
                                        "jmp C2"+et+"\n"+
                                        "C2"+et+":\n"+
                                        "pop dx\n"+
                                        "add dl,30h\n"+
                                        "mov ah,2\n"+
                                        "int 21h\n"+
                                        "dec cx\n"+
                                        "cmp cx,0\n"+
                                        "jne c2"+et+"\n";
                                          
                                et++;
                                cont++;
                            }
                        }
                            cc+="pop cx\n";
                            cc+="pop dx\n";
                            cc+="inc dh\n";
                            
                            cc+="loop repite"+net+"\n";
                        }
                        else ;
                        
                    /*    
                    
                    line=br.readLine())!= null){
                    StringTokenizer st = new StringTokenizer(line);
                    String pal=st.nextToken();//Almacena la instrucción
                        
                        
                        
                        
                        cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                        
                    }
*/
                    }
                    else if(pal.equals("Imprime")){
                        st.nextToken();
                        String var=st.nextToken();
                        linea=auto.tablaA.buscar(var);
                        String tipo=linea.tipo;
                        if(linea.lect.equals("S")){
                            if(tipo.equals("cad")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                            else if(tipo.equals("num")){
                                cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
        
                                cc+="lea bx,"+var+"\n"+
                                    "inc bx\n"+
                                    "mov cx,[bx]\n"+
                                    "mov ch,0\n"+
                                    "L"+var+et+":\n"+
                                    "inc bx\n"+
                                    "mov dl,[bx]\n"+
                                    "mov ah,2\n"+
                                    "int 21h\n"+
                                    "loop L"+var+et+"\n";
                                et++;
                                cont++;
                            }
                        }
                        else{
                            if(tipo.equals("cad")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss-1)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea dx,"+var+"\n"+
                                        "mov ah,9\n"+
                                        "int 21h\n";
                                    cont++;
                            }
                            if(tipo.equals("num")){
                                                            cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+(ss)+"\n"+
	"int 10h\n";
        s++;
                                    cc+="lea bx,"+var+"\n"+
                                        "mov ax,[bx]\n"+
                                        "mov bp,byte ptr 0ah\n"+
                                        "mov cx,0\n"+
                                        "CI"+et+":\n"+
                                        "inc cx\n"+
                                        "mov dx,0\n"+
                                        "div bp\n"+
                                        "mov di,dx\n"+
                                        "push di\n"+
                                        "cmp ax,word ptr 0\n"+
                                        "jne CI"+et+"\n"+
                                        "jmp C2"+et+"\n"+
                                        "C2"+et+":\n"+
                                        "pop dx\n"+
                                        "add dl,30h\n"+
                                        "mov ah,2\n"+
                                        "int 21h\n"+
                                        "dec cx\n"+
                                        "cmp cx,0\n"+
                                        "jne c2"+et+"\n";
                                          
                                et++;
                                cont++;
                            }
                        }
                    }
                    else if(pal.equals("Si")){
                        st.nextToken();
                        String comparador1=st.nextToken();
                        linea=auto.tablaA.buscar(comparador1);
                        String tipo=linea.tipo;
                        String operador=st.nextToken();
                        String comparador2=st.nextToken();
                        String deci="";
                        
                        cc+="mov ah,2\n"+
	"mov bh,0\n"+
	"mov dh,"+s+"\n"+
	"mov dl,"+ss+"\n"+
	"int 10h\n";
        s++;
           
                        if(operador.equals("<")){
                            deci="JG";
                        }
                        else if(operador.equals(">")){
                            deci="JL";
                        }
                        else if(operador.equals(">=")){
                            deci="JLE";
                        }
                        else if(operador.equals("<=")){
                            deci="JGE";
                        }
                        else if(operador.equals("==")){
                            deci="JNE";
                        }
                        else if(operador.equals("!=")){
                            deci="JE";
                        }
        
        
                        if(linea!=null){// si el primero es variable
                            if(auto.tablaA.buscar(comparador2)!=null){//Si el comparador2 es variable
                                Nodo linea2 = auto.tablaA.buscar(comparador2);
                                if(linea.lect.equals("S") && linea2.lect.equals("S")){ //Si ambos son de lectura
                                    cc+=obtValor(comparador1);
                                    cc+="push ax\n";
                                    cc+=obtValor(comparador2);
                                    cc+="pop dx\n";
                                }
                                else if(linea.lect.equals("S") && linea2.lect.equals("N")){
                                    cc+=obtValor(comparador1);
                                    cc+="lea bx,"+comparador2+"\n"+
                                        "mov dx,[bx]\n"; 
                                }
                                else if(linea.lect.equals("N") && linea2.lect.equals("S")){
                                    cc+=obtValor(comparador2);
                                    cc+="mov dx,ax\n"+
                                        "lea bx,"+comparador1+"\n"+
                                        "mov ax[bx]\n";
                                }
                                else{//Si ambos estan en memoria
                                    cc+="lea bx,"+comparador1+"\n"+
                                        "mov ax,[bx]\n"+
                                        "lea bx,"+comparador2+"\n"+
                                        "mov dx,[bx]\n"; 
                                }
                            }
                            else{//Si el comparador2 no es variable
                                if(linea.lect.equals("S")){
                                    cc+=obtValor(comparador1);
                                    cc+="mov dx,"+comparador2+"\n";
                                }
                                else{
                                    cc+="lea bx,"+comparador1+"\n"+
                                        "mov ax,[bx]\n"+
                                        "mov dx,"+comparador2+"\n";
                                }
                            } 
                        }
                        else{//el primero no es variable
                            if(auto.tablaA.buscar(comparador2)!=null){//Si e comparador2 es variable
                                Nodo linea2 = auto.tablaA.buscar(comparador2);
                                if(linea2.lect.equals("S")){ //Si el comp2 es de lectura
                                    cc+=obtValor(comparador2);
                                    cc+="mov dx,ax\n"+
                                        "mov ax,"+comparador1+"\n";
                                }
                                else{//si comp2 no es de lectura
                                    cc+="lea bx,"+comparador2+"\n"+
                                        "mov dx,[bx]\n"+
                                        "mov ax,"+comparador1+"\n";
                                }
                            }
                            else{//Si ambos no son variables
                                cc+="mov ax,"+comparador1+"\n"+
                                    "mov dx,"+comparador2+"\n";
                            }
                        }
                        cc+="cmp ax,dx\n"+
                            deci+" salto"+et;
                        
                        //Aqui va lo de la siguiente linea
                        
                        cc+="salto"+et+":\n";
                        cont++;
                        et++;
                    }
                        
                    }
                    }
                    catch(IOException ex){}
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
"mov al,0\n" +
"mov bh,7\n" +
"mov ch,00\n" +
"mov cl,0\n" +
"mov dh,24\n" +
"mov dl,79\n" +
"int 10h\n";
        
    }
    
    public static void cerrar_codigo(){
        cc+="fina: \n" + "	ret\n" +
"p0	endp\n" +
"\n" +
"\n" +
"codigo ends\n" +
"	end p0\n";
    }
    
    
            
}
