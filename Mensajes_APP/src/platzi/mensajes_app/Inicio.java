package platzi.mensajes_app;

import java.sql.Connection;
import java.util.Scanner;

public class Inicio {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        do{

            System.out.println("---------- Menu ----------");
            System.out.println("      App de Mensajes");
            System.out.println(" 1. Crear Mensaje");
            System.out.println(" 2. Listar Mensajes");
            System.out.println(" 3. Eliminar Mensaje");
            System.out.println(" 4. Editar  Mensaje");
            System.out.println(" 5. Salir \n");

            //leer la opcion del usuario
            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    MensajesService.crearMensaje();
                    break;
                case 2:
                    MensajesService.listarMensajes();
                    break;
                case 3:
                    MensajesService.borrarMensaje();
                    break;
                case 4:
                    MensajesService.editarMensaje();
                    break;
                default:
                    break;

            }

        }while(opcion != 5);

    }

}
