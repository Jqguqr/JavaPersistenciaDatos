package platzi.gatos_app;

import javax.swing.*;
import java.io.IOException;

public class Inicio {

    public static void main(String[] args) throws IOException {

        int option_menu = -1;
        String[] botones = {"1. Ver Gatos", "2. Ver Favoritos", "3. Salir"};
        String option;

        do{

            option = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Menu principal",
                    JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);

            //validamos opcion seleccionada
            for (int i = 0; i < botones.length; i++){

                if(option.equals(botones[i])){
                    option_menu = i;
                }

            }

            switch (option_menu){
                case 0:
                    GatosService.verGatos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    GatosService.verFavoritos(gato.getApikey());
                    break;
                default:
                    break;
            }

        }while(option_menu != 2);

    }

}
