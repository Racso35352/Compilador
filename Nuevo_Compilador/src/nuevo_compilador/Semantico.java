/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_compilador;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Oscar
 */
public class Semantico {
    public static String tabla="Pila\t\t\tEntrada\t\t\tSalida\n";
    public static boolean l=true;
    public static Lexico le=new Lexico();
    public static         int o=1;
    
    public static boolean revisar(int cont, List<String> lex,List<String> token){
        boolean x=false;
        List<String> lexi=lex;

        List<String> pil = new ArrayList<String>();        
        List<String> entrada = new ArrayList<String>();
        pil.add("0");
        int tam=lex.size();
        String aux="id";
        for(int a=0;a<tam;a++){
            if(le.revisar(lexi.get(a)).equals("Variable")){
                entrada.add(aux);
            }
            else entrada.add(lex.get(a));
        }
        
        String salida="";
      
        int[] estado={0,1,2,3,4,5,6,7,8,9,10,11};
        String[][] accion=new String[12][6];
        int [][] ir_a=new int [12][3];
        
        for(int a=0;a<12;a++){
            for(int b=0;b<6;b++){
            accion[a][b]="error";
            }
        }
        for(int a=0;a<12;a++){
            for(int b=0;b<3;b++){
            ir_a[a][b]=0;
            }
        }
        
        accion[0][0]="d5";
        accion[0][3]="d4";
        accion[1][1]="d6";
        accion[1][5]="acep";
        accion[2][1]="r2";        
        accion[2][2]="d7";
        accion[2][4]="r2";
        accion[2][5]="r2";        
        accion[3][1]="r4";
        accion[3][2]="r4";
        accion[3][4]="r4";
        accion[3][5]="r4";
        accion[4][0]="d5";
        accion[4][3]="d4";
        accion[5][1]="r6";
        accion[5][2]="r6";
        accion[5][4]="r6";
        accion[5][5]="r6";
        accion[6][0]="d5";
        accion[6][3]="d4";
        accion[7][0]="d5";
        accion[7][3]="d4";        
        accion[8][1]="d6";
        accion[8][4]="d11";
        accion[9][1]="r1";
        accion[9][2]="d7";
        accion[9][4]="r1";
        accion[9][5]="r1";        
        accion[10][1]="r3";
        accion[10][2]="r3";
        accion[10][4]="r3";
        accion[10][5]="r3";        
        accion[11][1]="r5";
        accion[11][2]="r5";
        accion[11][4]="r5";
        accion[11][5]="r5";
  
        ir_a[0][0]=1;
        ir_a[0][1]=2;
        ir_a[0][2]=3;
        ir_a[4][0]=8;
        ir_a[4][1]=2;
        ir_a[4][2]=3;
        ir_a[6][1]=9;
        ir_a[6][2]=3;
        ir_a[7][2]=10;

        /*for(int a=0;a<12;a++){
            for(int b=0;b<1;b++){
            System.out.print("\t"+estado[a]);
            }
            System.out.print("\n");
            
        }
*/
       
        
        
        String ac="";
        
        String p1[]={"E","E","T","T","F","F"};
        int p2[]={3,1,3,1,3,1};
        
        String result="No se pudo";
        
                    
        int a=calcA(entrada.get(0));
        int s=Integer.parseInt(pil.get(0));
        int j=pil.size();
        entrada.remove(0);
        entrada.remove(0);
        
        int k=entrada.size();
        char p='j';
        
        int u=0;
        do{
            
            
        s=Integer.parseInt(pil.get(pil.size()-1));
            a=calcA(entrada.get(0));
            if(a==9){
                l=false;
                ac="error";
            }
            else{
            j=pil.size();
            k=entrada.size();
            
            
            
            //System.out.println(""+s+a );
            for(int g=0;g<j;g++){
                tabla+=pil.get(g);
            }
            tabla+="\t\t\t";
            for(int g=0;g<k;g++){
                tabla+=entrada.get(g);
            }
            tabla+="\t\t\t"+accion[s][a]+"\n";
            
            p=accion[s][a].charAt(0);
            
            //System.out.println(""+p );
            //System.out.println(""+s+a );
            //System.out.println(""+tabla );
            
            
            if(accion[s][a].charAt(0)=='d'){
            
            pil.add(entrada.get(0));
            if(s==8&&a==4)pil.add(accion[s][a].charAt(1)+""+accion[s][a].charAt(2));
            else pil.add(accion[s][a].charAt(1)+"");
            entrada.remove(0);            
            a=calcA(entrada.get(0));
            if(a==9){
                ac="error";
                l=false;
            }
            else ;
            }
            
            else if(accion[s][a].charAt(0)=='r'){
                /*System.out.println(s+" "+a);
               char z=accion[s][a].charAt(1);
               k=Character.getNumericValue(z);
               System.out.println(p2[k-1]);
            System.out.println(k);
            */
            
            k=(p2[Character.getNumericValue(accion[s][a].charAt(1))-1]);
            //System.out.println(""+k );
                for(int v=1;v<=(2*k);v++){
                    j=pil.size()-1;
                    pil.remove(j);
                }
                j=pil.size()-1;
                pil.add(p1[Character.getNumericValue(accion[s][a].charAt(1))-1]);
                //System.out.println(p1[accion[s][a].charAt(1)]);
                //pil.add(let(p2[(accion[s][a].charAt(1)])));
                
                
                k=let(p1[Character.getNumericValue(accion[s][a].charAt(1))-1]);
                //System.out.println(""+j );
                pil.add(ir_a[Integer.parseInt(pil.get(j))][k]+"");
                
                
                //System.err.println(""+j+k );
            }
            
            
            
      
            
            else{
            ac="error";
            }
            
            
            //System.out.println(""+s + a );
            }
    }    
         while(!ac.equals("error") ) ;
        
        if(l==true){
            p=accion[s][a].charAt(0);
        if(p=='a') {
            //System.out.println(tabla);
            x=true;
        }
        if(p=='e') System.out.println("Linea "+cont+ "     Error: Semantica de la expresion incorrecta ");
        
        }
        else {
            System.out.println("Linea "+cont+ "     Error: Caracteres no permitidos ");
        }
        
        
        return x;
    }
    
    public static int calcA(String pal){
        int y=7;
        
        if(pal.equals("id"))y=0;
        else if(pal.equals("+"))y=1;
        else if(pal.equals("*"))y=2;
        else if(pal.equals("("))y=3;
        else if(pal.equals(")"))y=4;
        else if(pal.equals(";"))y=5;
        else y=9;
                
        return y;
    }
    
    public static int let(String le){
        int num=7;
        
        if(le.equals("E"))num=0;
        else if(le.equals("T"))num=1;
        else if(le.equals("F"))num=2;
        //System.out.println(""+num );
        return num;
    }
    
    public static String tabla(){
        
        return tabla;
    }
    
    public static void hacer(int cont,List<String> lex,List<String> token,TabS tablaA){
        
        boolean l=true;
        
        int tam=lex.size();
        for(int a=0;a<tam;a++){
            if(le.revisar(lex.get(a)).equals("Variable")){
                if(tablaA.buscar(lex.get(a))==null){
                    System.out.println("Linea "+cont+ "     Error: La variable no se encuentra en la tabla de simbolos");
                    l=false;
                }
                else{
                    if(a>=2&&tablaA.buscar(lex.get(a))!=null){
                    lex.set(a,(tablaA.buscar(lex.get(a)).val));
                }       
                }
            }
        }
        
        if(l==true){
        for(int a=0;a<tam;a++){
            System.out.print(" "+lex.get(a));
        }
        
        }
        
           do{            
        int hay=0;
        int hay2=0;
        
        for(int a=0;a<tam;a++){
            if((lex.get(a)).equals("(")){
                hay++;
                if((lex.get(a+2)).equals(")")) {
                    lex=unopar(tam,a+1,lex);
                    tam=lex.size();                    
                }
                else {
                    
                    lex=recur(tam,a+1,lex);
                    tam=lex.size();
                }
            }
            
        }
        
        
        if(hay==0&&lex.size()>4){
             
            tam=lex.size();
        for(int a=0;a<tam;a++){
            if(lex.get(a).equals("*")){
                lex=multi(tam,a,lex);
                    tam=lex.size();
                    a--;
            }
        }
        for(int a=0;a<tam;a++){
            if(lex.get(a).equals("+")){
                lex=suma(tam,a,lex);
                    tam=lex.size();
                    a--;
            }
        }
         
        }
            
            /*
        tam=lex.size();
        for(int a=0;a<tam-1;a++){
           
            if(lex.get(a+1).equals("*")&&!lex.get(a).equals("(")&&!lex.get(a+2).equals("(")){
                    lex=multi(tam,a+1,lex);
                    tam=lex.size();
                     hay2++;
                     
        System.out.println("Si entra"+ hay2+ lex.get(a+1));
        System.out.println(" ");
        for(int c=0;c<tam;c++){
            System.out.print(" "+lex.get(c));
        }
                    a--;
                }
        }
        }
        System.out.println(" ");
        for(int a=0;a<tam;a++){
            System.out.print(" "+lex.get(a));
        }
        if(hay2==0&&lex.size()>4){
                
            
        tam=lex.size();
        for(int a=0;a<tam-1;a++){
            
            if(lex.get(a+1).equals("+")&&!lex.get(a).equals("(")&&!lex.get(a+2).equals("(")){
                
                    lex=suma(tam,a+1,lex);
                    tam=lex.size();
                    a--;
                }
            
        }
        }
        */
        
           }while(lex.size()>4);
        
        tam=lex.size();
        System.out.println(" ");
        for(int a=0;a<tam;a++){
            System.out.print(" "+lex.get(a));
        }
        
        tablaA.buscar(lex.get(0)).setVal(""+lex.get(2));
        
    }
    
    public static List<String> unopar(int tam,int b, List<String> lex){
        List<String> nueva = new ArrayList<String>();
            for(int a=0;a<(b-1);a++){
                nueva.add(lex.get(a));
            }
            nueva.add(lex.get(b));
            for(int a=b+2;a<tam;a++){
                nueva.add(lex.get(a));
            }
        return nueva;
    }
    
    
    public static List<String> recur (int tam,int b, List<String> lex){

                boolean u =false;
                
                for(int a=b;a<tam;a++){
                if(lex.get(a).equals("*")){
                    u=true;
                   // b=a;
                    break;
                }
            }
                       
            List<String> nueva = new ArrayList<String>();
            if(lex.get(b).equals("(")){
                
                lex=recur(tam,b+1,lex);
                    tam=lex.size();
            }
            
                else if(lex.get(b+1).equals("+")&&!lex.get(b).equals("(")&&!lex.get(b+2).equals("(")){
                    lex=suma(tam,b+1,lex);
                    tam=lex.size();
                    
                    
                }
                else if(lex.get(b+1).equals("*")&&!lex.get(b).equals("(")&&!lex.get(b+2).equals("(")){
                    lex=multi(tam,b+1,lex);
                    tam=lex.size();
                    
                }
            
            else ;
            for(int a=0;a<tam;a++){
                nueva.add(lex.get(a));
            }
        return nueva;
    }


    public static List<String> suma (int tam,int b, List<String> lex){
        List<String> nueva = new ArrayList<String>();
        int n1=Integer.parseInt(lex.get(b-1));
        int n2=Integer.parseInt(lex.get(b+1));
        int n3=n1+n2;
        
        for(int a=0;a<(b-1);a++){
                nueva.add(lex.get(a));
            }
            nueva.add(n3+"");
            for(int a=b+2;a<tam;a++){
                nueva.add(lex.get(a));
            }
            
             
        
    return nueva;
}
    
  public static List<String> multi (int tam,int b, List<String> lex){
List<String> nueva = new ArrayList<String>();
        int n1=Integer.parseInt(lex.get(b-1));
        int n2=Integer.parseInt(lex.get(b+1));
        int n3=n1*n2;
        
        for(int a=0;a<(b-1);a++){
                nueva.add(lex.get(a));
            }
            nueva.add(n3+"");
            for(int a=b+2;a<tam;a++){
                nueva.add(lex.get(a));
            }
        
    return nueva;
}
  
}