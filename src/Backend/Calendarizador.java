package Backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calendarizador {
    
    private final Proceso[] procesos = Tareas.getProcesos(); 
    private Memoria[] bloquesMemoria;
    private String txtSiguienteProceso, TxtProcesando,TxtGuardandoContexto,TxtCargandoContexto;
    private int procesosTotalesTerminados = 0;
    private int procesandoActual = 0;
    private boolean seResto = false;
    int procesando = 0;
    
    public void inicializarBloquesMemoria() {
        int tamanos[] = {9500, 7000, 4500, 8500, 3000, 9000, 1000, 5500, 1500, 500};
        bloquesMemoria = new Memoria[10]; 
        for (int i=0;i<bloquesMemoria.length;i++) {
            bloquesMemoria[i] = new Memoria(i, tamanos[i], null); 
            for (Proceso proceso : procesos) { 
                if (comparaEstado(proceso.getEstado(), Constantes.INACTIVO) &&
                    cabeEnBloque(proceso.getTamano(), bloquesMemoria[i].getTamano())) {
                    proceso.setEstado(Constantes.ASIGNADO);
                    bloquesMemoria[i].setProceso(proceso);
                    System.out.format("Proceso asignado en memoria PROCESO: %d\n",proceso.getNum());
                    break;
                }
            }
        }
        bloquesMemoria = getBloquesMemoriaOrdenados();
         
    } 
    public void procesar() throws InterruptedException {
        
        
        Thread.sleep(200);  
        for(int i=0;i<bloquesMemoria.length;i++) {
            setTxtProcesando("");
            setTxtSiguienteProceso("");
            setTxtSiguienteProceso("");
            if (procesosTotalesTerminados == 25) {
                System.out.println("TERMINADO.");
                return;
            }

            if (bloquesMemoria[i] == null || bloquesMemoria[i].proceso == null) {
                seResto = false;
                break;
            }
            
            Proceso procesoActual = bloquesMemoria[i].proceso;
            procesando = procesoActual.getNum();
             
            if((procesandoActual == procesando) && (procesandoActual != 0) && seResto) {
                continue;
            }
            procesandoActual = procesando;


            System.out.println("=====================================");
            System.out.println("Procesando: " + procesando);
            setTxtProcesando(String.valueOf(procesando));


            Proceso procesoSiguiente = getSiguienteProceso(procesando);

            if (procesoSiguiente != null) {
                if (procesosTotalesTerminados == 25) {
                    System.out.println("Siguiente: ninguno");
                } else {
                     
                    System.out.println("Siguiente: " + procesoSiguiente.getNum());  
                    setTxtSiguienteProceso(String.valueOf(procesoSiguiente.getNum()));
                }
            }  

            if (procesoActual.getTiempo() <= Constantes.QUANTUM) {
                bloquesMemoria[i].proceso = null;
                terminarProceso(procesando);
                procesosTotalesTerminados++;
                rellenarBloque();
                seResto = false;
            } else { 
                for (int j = 0; j < Constantes.QUANTUM; j++) { 
                    setTxtGuardandoContexto(String.valueOf(bloquesMemoria[i].proceso.getNum()));
                    Thread.sleep(1000);
                    bloquesMemoria[i].proceso.setTiempo(bloquesMemoria[i].proceso.getTiempo()-1);
                }  
                System.out.println("Quantum aplicado, el proceso no se terminó");
                
                
                seResto = true;
                continue;
            } 
            System.out.println("Total procesados: " + procesosTotalesTerminados);  
            break;
        } 
        
        if(seResto == false) {
            bloquesMemoria = getBloquesMemoriaOrdenados();
            System.out.println("Reordenando...");
        }
        procesar(); 
        setTxtGuardandoContexto("");
    } 
    private void terminarProceso(int id) { 
        for(Proceso proceso : procesos)
        {
            if(proceso == null || proceso.Num != id)
            {
                continue;
            }
            proceso.setEstado(Constantes.TERMINADO); 
            return;
        }
         
    }
    private void rellenarBloque() {
        for(Memoria memoria : bloquesMemoria) {
            if (memoria == null) {
                continue;
            }
            
            if (memoria.proceso != null) {
                continue;
            }
            
            for(Proceso proceso : procesos) {
                if (comparaEstado(proceso.getEstado(), Constantes.EN_ESPERA) ||
                    comparaEstado(proceso.getEstado(), Constantes.INACTIVO) &&
                    cabeEnBloque(proceso.getTamano(), memoria.getTamano()))
                {
                    proceso.setEstado(Constantes.ASIGNADO);
                    memoria.proceso = proceso;
                    System.out.println("==================== \n " +
                                       "Asignado el proceso: " + proceso.toString() + " en: " + memoria.toString());
                    return;
                }
            }
        }
    }
    
    public Proceso[] getProcesos()
    {
        return procesos;
    }
    
    public Memoria[] getBloquesMemoria()
    {
        return bloquesMemoria;
    }
    
    public Proceso getSiguienteProceso(int numero)
    {
        for(int i=0; i<bloquesMemoria.length; i++)
        {
            if(bloquesMemoria[i] == null)
            {
                continue;
            }
            
            if(bloquesMemoria[i].proceso == null)
            {
                continue;
            }
            
            if(bloquesMemoria[i].proceso.getNum() != numero)
            {
                continue;
            }
            
            if(bloquesMemoria.length == i && bloquesMemoria[i+1].proceso != null)
            {
                continue;
            }
            
            if(bloquesMemoria[i+1] == null)
            {
                continue;
            }
            
            return bloquesMemoria[i+1].proceso;

        }
        
        return null;
    }
    
    public void limpiarBloque(int bloque)
    {
        for(int i=0;i<bloquesMemoria.length;i++)
        {
            if (bloquesMemoria[i] != null && bloquesMemoria[i].bloque == bloque)
            {
                bloquesMemoria[i] = null;
                return;
            }
        }
    }
    
    public Memoria getBloquePorProceso(int idProceso)
    {
        if (bloquesMemoria == null || bloquesMemoria.length == 0)
        {
            return null;
        }
        for(Memoria bloqueMemoria : bloquesMemoria)
        {
            if (bloqueMemoria.proceso.Num == idProceso)
            {
                return bloqueMemoria;
            }
        }
        return null;
    }
    
    public Memoria[] getBloquesMemoriaOrdenados()
    {
        List<Memoria> listaMemoriaOrdenada = Arrays.asList(bloquesMemoria);
        List<Memoria> listaMemoriaSinVacios = new ArrayList<Memoria>();
        for (Memoria memoria : listaMemoriaOrdenada) {
            if (memoria != null && memoria.proceso != null)
            {
                listaMemoriaSinVacios.add(memoria);
            }
        }
        Collections.sort(listaMemoriaSinVacios, comparing((t) -> t.proceso.Tiempo));
        return listaMemoriaSinVacios.toArray(bloquesMemoria);
    }
      
    private boolean comparaEstado(String estadoActual, String estadoEsperado)
    {
        return estadoActual.equals(estadoEsperado);
    }    
        
    private boolean cabeEnBloque(int tamanoProceso, int tamanoMemoria)
    {
        return tamanoProceso <= tamanoMemoria;
    }

    /**
     *
     * @return
     */
//    
//    public int getTxtSiguienteProceso() {
//        return txtSiguienteProceso;
//    }

    public void setTxtSiguienteProceso(String txtSiguienteProceso) {
        this.txtSiguienteProceso = txtSiguienteProceso;
    }
    
    public void setTxtGuardandoContexto(String txtGuardandoContexto) {
        this.TxtGuardandoContexto = txtGuardandoContexto;
    }
    
    public void setTxtProcesando(String txtProcesando) {
        this.TxtProcesando = txtProcesando;
    }
    
    
 
    public String getTxtProcesando() {
        return TxtProcesando;
    }
    
    public String getTxtSiguienteProceso() {
        return txtSiguienteProceso;
    }

//    public void setTxtProcesando(int TxtProcesando) {
//        this.TxtProcesando = TxtProcesando;
//    }
    
    public int getProcesosTotalesTerminados() {
        return procesosTotalesTerminados;
    }
//    public void setTxtGuardandoContexto(int TxtGuardandoContexto) {
//        this.TxtGuardandoContexto = TxtGuardandoContexto;
//    }
    
    public String getTxtGuardandoContexto() {
        return TxtGuardandoContexto;
    }
//    public void setTxtCargandoContexto(int TxtCargandoContexto) {
//        this.TxtCargandoContexto = TxtCargandoContexto;
//    } 
    public String getTxtCargandoContexto() {
        return TxtCargandoContexto;
    }
 
}