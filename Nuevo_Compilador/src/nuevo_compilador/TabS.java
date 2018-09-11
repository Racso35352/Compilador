class TabS{

   private Nodo inicio;
   private Nodo fin;
   private int tam;
   
   public TabS(){
      
      inicio=null;
      fin=null;
      tam=0;
      
   }
   
   public void agregar(String nom, String tipo, String val, String lect){
      
      Nodo nuevo= new Nodo(nom,"id"+tam,tipo,val,lect);
      
      if(tam==0){
         inicio=nuevo;
      }
      
      fin=nuevo;
      tam++;
      
   }
   
   public boolean buscar(String nombre){//true si esta, false no esta
      
      Nodo busq=inicio;
      
      for(int i=0;i<=tam;i++){
      
         if(busq.nom.equals(nombre)) return true;
         
         busq=busq.pointer;
      
      }
      return false;      
   }
   
   public void imprimir(){
   
      Nodo linea=inicio;
      
      if(inicio!=null){
         
         for(int i=0;i<tam;i++){
            System.out.println(linea.nom+" "+linea.id+" "+linea.tipo+" "+linea.val+" "+linea.lect);
         }
         
      }
      else System.out.println("Tablas de simbolos vacía");
      
   }
   

}