package nuevo_compilador;

public class pruebaTab{

   public static TabS tabla= new TabS();

   public static void main(String[]args){
   
      
      
      tabla.agregar("Var_1","Int","30","N");
      tabla.agregar("Var_2","String","null","S");
      tabla.agregar("Var_3","Bool","True","N");
      tabla.agregar("Var_4","Int","100","N");
      
//      if(tabla.buscar("Var_3")==true)System.out.println("Si est� Var_4");
      
      tabla.imprimir();
   
   }

}