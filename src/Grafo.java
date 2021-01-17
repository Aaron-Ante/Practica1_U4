/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class Grafo {
    NodoGrafo ini,fin;
    NodoArista ant, sig;
    NodoGrafo aristas;
    public Grafo(){
        ini = null;
        fin = null;  
    }
    
    public boolean insertarNodo(char v){
        NodoGrafo nuevo = new NodoGrafo(v);
        if(nuevo == null){
            return false;
        }
        if(ini == null && fin == null){
            ini = fin = nuevo;
            return true;
        }
        fin.sig = nuevo;
        nuevo.ant=fin;
        fin = nuevo;
        return true;
    }
    
    
    public boolean insertarArista(char orig, char dest){
    NodoGrafo origen  = null;
    NodoGrafo destino = null;
   
   
    
    for(NodoGrafo temp = ini; temp!=null; temp = temp.sig){
        if(temp.valor == orig){
            origen = temp;
        }
        if(temp.valor == dest){
            destino = temp;
        }
    }
    
    if(origen  == null){
        return false;
    }
    
    if(destino == null){
        return false;
    }
     
    if(origen.aristas == null){
        NodoArista temp2= new NodoArista(destino);
        origen.aristas = temp2;
        return true;
    }
    
    if(origen.aristas.sig ==null){
        NodoArista temp2 = new NodoArista(destino);
        origen.aristas.sig = temp2;
        temp2.ant = origen.aristas;
        return true;
    }
    
    
   if(origen.aristas.sig == null){
       NodoArista temp2 = new NodoArista(destino);
       origen.aristas.sig=temp2;
       temp2.ant = origen.aristas;
       return true;
   }
   NodoArista temp2 = new NodoArista(destino);
   for(NodoArista temp3= origen.aristas; temp3!=null; temp3 = temp3.sig){
       if(temp3.sig ==null){
           temp3.sig = temp2;
           temp2.ant = temp3;
           return true;
       }
   }
    return true;
    
}
    private NodoGrafo buscarNodoGrafo(char valorBuscado){
        NodoGrafo temp = ini;
        do{
            if(temp.valor == valorBuscado){
                return temp;
            }
            temp = temp.sig;
        }while(temp!=null);
        return temp; 
        
    }
    
   
    
    
    
    public boolean eliminarArista(char orig, char dest){
        NodoGrafo origen = buscarNodoGrafo(orig);
        
        if(origen == null){
            return false;
        }
        if(origen.aristas == null){
            return false;
        }
        
        for(NodoArista temp =origen.aristas; temp!= null;temp =temp.sig){
           if(temp.direccion.valor == dest){
            if(temp == origen.aristas){
               //revisando si la arista a Eliminar esta en el primer nodo
               
               origen.aristas = temp.sig;
                temp.direccion = null;
                temp.sig = null;
                temp.ant = null;
                return true;
               
            }else{
                //revisar si la arista a Eliminar esta como ultimo nodo
                if(temp.sig == null){
                    temp.ant.sig= null;
                    temp.direccion = null;
                    temp.ant = null;
                    return true; 
                }
               
                 //desconectar  la arista a eliminar estando en medio 
                temp.ant.sig = temp.sig; // Nodo arriba de temp enlazando con nodo de abajo
                temp.sig.ant = temp.ant; // nodo abajo de temp enlazando con nodo de arriba
                temp.sig = temp.ant = null;
                temp.direccion = null;
                return true;
            }
               
           
           }
        }
        
        return false;
    
    }
    
    public boolean eliminarNodo(char v){
        if(ini == null && fin == null){
            return false;
        }
        NodoGrafo nodoAEliminar = buscarNodoGrafo(v);
        if(nodoAEliminar == null){
            return false;
        }
        //verificar si es isla el nodo eliminar
        //que no tenga aristas
        if(nodoAEliminar.aristas!=null){
            return false;
        }
        //que otro nodo no tenga aristas a nodoEliminar
        for(NodoGrafo temp = ini; temp!=null; temp = temp.sig){
            if(encontrarAristas(temp, nodoAEliminar) == true){
                return false;
            }
        }
        if(ini == fin && ini == nodoAEliminar){
            ini = fin = null;
            return true;
        }
        //eliminar si el nodoEliminar esta en ini
        if(ini == nodoAEliminar){
            NodoGrafo temp = ini.sig;
            ini.sig = null;
            temp.ant = null;
            ini = temp;
            return true;
        }
         //eliminar si el nodoEliminar esta en fin
        if(fin == nodoAEliminar){
            NodoGrafo temp = fin.ant;
            temp.sig = null;
            fin.ant = null;
            fin = temp;
            return true;
        }
        //eliminar si el nodoEliminar esta en medio
       nodoAEliminar.ant.sig = nodoAEliminar.sig;
       nodoAEliminar.sig.ant = nodoAEliminar.ant;
       nodoAEliminar.sig = nodoAEliminar.ant = null;
        return true;
    }
    

    private boolean encontrarAristas(NodoGrafo temp, NodoGrafo nodoAEliminar) {
       for(NodoArista temp2 = temp.aristas; temp2!= null; temp2 = temp2.sig){
           
           if(temp2.direccion ==  nodoAEliminar){
           return true;
           
           }
       }
       return false;
    }
    
    
    
    
    
    
     public  boolean buscarArista(char orig, char dest){
        NodoGrafo origen = buscarNodoGrafo(orig);
        NodoGrafo destino = buscarNodoGrafo(dest);
        if(origen==null || destino == null){
            return false;
        }
        if(origen.aristas == null){
            return false;
        }
       
       
        for(NodoArista temp= origen.aristas; temp!= null; temp=temp.sig){
             System.out.print(temp.direccion.valor+"\n");
            if(dest == temp.direccion.valor){
                return true;
            }
        }
     return false;
     }
     
     
    
    
        public String mostrarNodo(NodoGrafo temp){
            if (temp == null){
            return "";
           
        }
        
         return temp.valor +"\n"+mostrarNodo(temp.sig);
     
    }
     
        public boolean Mostrara(char orig){
            NodoGrafo origen = buscarNodoGrafo(orig);
            if(origen == null){
               return false;
            }
            return true;
        }
        
        public String  verArista(char arista){
           NodoGrafo nodo = buscarNodoGrafo(arista);
           String cad="";
           if(nodo.aristas == null){
               cad+="El nodo: "+nodo.valor+"no tiene aristas";
               return cad;
           }
         
         cad+="Nodo: "+nodo.valor+"\n";
           for(NodoArista temp = nodo.aristas; temp!=null; temp = temp.sig){
               cad+=temp.direccion.valor+"\n";
           }
           return cad;
        }
        
        public String mostrarAristas(NodoGrafo temp){
          if (temp == null){
            return "";
           
        }
        
         return temp.valor +"\n"+mostrarNodo(temp.sig);
        }
    
    
    public NodoGrafo getIni(){
        return ini;
    }
    
    public NodoGrafo getD(){
        return aristas;
    }
    
   
    
}
