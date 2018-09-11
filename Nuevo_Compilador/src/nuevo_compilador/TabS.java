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
      else{
       fin.pointer=nuevo;
      }
      
      fin=nuevo;
      tam++;
      
   }
   
   public boolean buscar(String nombre){//true si esta, false no esta
      
      Nodo busq=inicio;
      boolean ban=false;
      
      while(busq!=null && ban==false){
         if(busq.getNom().equals(nombre)){
            ban=true;
         }
         else{
            busq=busq.pointer;
         }
      }
      return ban;     
   }
   
   public void imprimir(){
   
      Nodo linea=inicio;
      
      if(linea!=null){         
      while(linea!=null){
         System.out.println(linea.nom+" "+linea.id+" "+linea.tipo+" "+linea.val+" "+linea.lect);
         linea=linea.pointer;
      }
         
      }
      else System.out.println("Tabla de simbolos vacía");
      
   }
   

}