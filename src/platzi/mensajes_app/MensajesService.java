package platzi.mensajes_app;

import java.util.Scanner;

public class MensajesService {

    public static void crearMensaje(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu mensaje \n");
        String mensaje = sc.nextLine();

        System.out.println("Tu nombre \n");
        String nombre = sc.nextLine();

        Mensajes registro = new Mensajes();
        registro.setMensaje(mensaje);
        registro.setAutor_mensaje(nombre);
        MensajesDAO.crearMensajeDB(registro);

    }

    public static void listarMensajes(){
        MensajesDAO.leerMensajesDB();
    }

    public static void borrarMensaje(){

        Scanner sc = new Scanner(System.in);

        System.out.println("ID del mensaje a eliminar: ");
        int id_mesage = sc.nextInt();
        MensajesDAO.borrarMensajeDB(id_mesage);

    }

    public static void editarMensaje(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Nuevo mensaje: ");
        String nuevo_mensaje = sc.nextLine();

        System.out.println("ID del mensaje a editar: ");
        int id_mensaje = sc.nextInt();

        Mensajes actualizacion = new Mensajes();
        actualizacion.setId_mensaje(id_mensaje);
        actualizacion.setMensaje(nuevo_mensaje);

        MensajesDAO.actualizarMensajeDB(actualizacion);

    }

}
