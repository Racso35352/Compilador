class Nodo{

   public String nom;
   public String id;
   public String tipo;
   public String val;
   public String lect; // N  / S
   public Nodo pointer;
   
   //Constructor
   public Nodo(String nom, String id, String tipo, String val, String lect){
      
      this.nom=nom;
      this.id=id;
      this.tipo=tipo;
      this.val=val;
      this.lect=lect;
      pointer=null;
      
   }
   

}