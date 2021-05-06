package Backend;

import java.util.Comparator;
public class Proceso {
    public int Num, Tiempo, Tamano;
    public String Estado; 
    
    public Proceso(){
        
    } 
    public Proceso(int Num, int Tiempo, int Tamano, String Estado) {
        this.Num = Num;
        this.Tiempo = Tiempo;
        this.Tamano = Tamano;
        this.Estado = Estado; 
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public void setTiempo(int Tiempo) {
        this.Tiempo = Tiempo;
    }

    public int getTamano() {
        return Tamano;
    }

    public void setTamano(int Tamano) {
        this.Tamano = Tamano;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    public static Comparator<Proceso> OrdTiempo = new Comparator<Proceso>() {

        @Override
	public int compare(Proceso s1, Proceso s2) {

	   int rollno1 = s1.getTiempo();
	   int rollno2 = s2.getTiempo(); 
	   return rollno1-rollno2; 
    }};
    @Override
    public String toString()
    {
        return Num + " " + "Tamano: " + Tamano + " Estado: " + Estado + " Tiempo: " + Tiempo;
    }
    
}