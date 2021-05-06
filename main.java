public class main{ 
    public static void main(String args[]){
        Calendarizador calendarizador = new Calendarizador();
        Memoria memoria = new Memoria();
        Tareas tareas = new Tareas();
        Proceso procesos = new Proceso();
        for (int i = 0; i < 10; i++) {
            System.out.println(tareas.getProceso(i).getEstado());
        }
    }
}