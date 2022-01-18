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

    }

    public static void borrarMensaje(){

    }

    public static void editarMensaje(){

    }

}
