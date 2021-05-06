package Backend;

public class Memoria {
   
    public int bloque, tamano;
    public Proceso proceso;

    public Memoria() {

    } 
    public Memoria(int bloque, int tamano, Proceso proceso) {
        this.bloque = bloque;
        this.proceso = proceso;
        this.tamano = tamano;
    }  
    
    public int getBloque() {
        return bloque;
    } 
    public void setBloque(int Bloque) {
        this.bloque = Bloque;
    }
    
    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }
    
    @Override
    public String toString()
    {
        return "Bloque: " + bloque + " Tamaño: " + tamano;
    }
}
