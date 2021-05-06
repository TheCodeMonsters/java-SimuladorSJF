package Backend;

import java.util.Arrays;
import java.util.Collections;
public class Tareas { 
    private static Proceso[] procesos; 
    public static Proceso[] getProcesos() {
        if (procesos == null) {
            procesos = new Proceso[25]; 
            procesos[0]=new Proceso(1,5,5760, Constantes.INACTIVO); 
            procesos[1]=new Proceso(2,4,4190, Constantes.INACTIVO); 
            procesos[2]=new Proceso(3,8,3290, Constantes.INACTIVO);  
            procesos[3]=new Proceso(4,2,2030, Constantes.INACTIVO);
            procesos[4]=new Proceso(5,2,2550, Constantes.INACTIVO);
            procesos[5]=new Proceso(6,6,6990, Constantes.INACTIVO);  
            procesos[6]=new Proceso(7,8,8940, Constantes.INACTIVO); 
            procesos[7]=new Proceso(8,10,740, Constantes.INACTIVO); 
            procesos[8]=new Proceso(9,7,3930, Constantes.INACTIVO); 
            procesos[9]=new Proceso(10,6,6890, Constantes.INACTIVO);
            procesos[10]=new Proceso(11,5,6580, Constantes.INACTIVO); 
            procesos[11]=new Proceso(12,8,3820, Constantes.INACTIVO);  
            procesos[12]=new Proceso(13,9,9140, Constantes.INACTIVO); 
            procesos[13]=new Proceso(14,10,420, Constantes.INACTIVO);
            procesos[14]=new Proceso(15,10,220, Constantes.INACTIVO); 
            procesos[15]=new Proceso(16,7,7540, Constantes.INACTIVO); 
            procesos[16]=new Proceso(17,3,3210, Constantes.INACTIVO); 
            procesos[17]=new Proceso(18,1,1380, Constantes.INACTIVO); 
            procesos[18]=new Proceso(19,9,9350, Constantes.INACTIVO);
            procesos[19]=new Proceso(20,3,3610, Constantes.INACTIVO);
            procesos[20]=new Proceso(21,7,7540, Constantes.INACTIVO);  
            procesos[21]=new Proceso(22,2,2710, Constantes.INACTIVO); 
            procesos[22]=new Proceso(23,8,8390, Constantes.INACTIVO); 
            procesos[23]=new Proceso(24,5,5950, Constantes.INACTIVO);
            procesos[24]=new Proceso(25,10,760, Constantes.INACTIVO);
            
            Collections.sort(Arrays.asList(procesos), Proceso.OrdTiempo);
        } 
        return procesos;
    } 
    
    public static Proceso getProceso(int p) {
        return procesos[p];
    }
}