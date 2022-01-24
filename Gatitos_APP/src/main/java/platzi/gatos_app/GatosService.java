package platzi.gatos_app;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GatosService {

    public static void verGatos() throws IOException {

        //1. Traer datos de la API
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
        Response response = client.newCall(request).execute();

        String elJson = response.body().string();

        //cortar corchetes
        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length()-1);

        //crear un objeto de la clase Json
        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(elJson, Gatos.class);

        //redimensionar imagen
        Image image = null;

        try {
            URL url = new URL(gatos.getUrl());

            //image = ImageIO.read(url);
            //ImageIcon fondoGato = new ImageIcon(image);

            HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
            httpcon.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());
            ImageIcon fondoGato = new ImageIcon(bufferedImage);

            if (fondoGato.getIconWidth() > 800){

                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);

            }

            String menu = "Opciones: \n"
                    + "1. Siguiente \n"
                    + "2. Favorito \n"
                    + "3. Volver \n";

            String[] botones = {"Siguiente", "Favorito", "Volver"};
            String id_gato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato,
                    JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

            int selection = -1;

            //validamos opcion seleccionada
            for (int i = 0; i < botones.length; i++){

                if(opcion.equals(botones[i])){
                    selection = i;
                }

            }

            switch (selection){
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }

        }
        catch (Exception e){
            System.out.println("Error: \n" + e);
        }

    }

    public static void favoritoGato(Gatos gato){

        try{

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\""+gato.getId()+"\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getApikey())
                    .build();
            Response response = client.newCall(request).execute();

        }
        catch (IOException e){
            System.out.println("Error: \n" + e);
        }

    }

    public static void verFavoritos(String apikey) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", apikey)
                .build();

        Response response = client.newCall(request).execute();

        // guardamos la respuesta en elJson
        String elJson = response.body().string();

        if(!response.isSuccessful()) {
            response.body().close();
        }

        //creamos el objeto Gson
        Gson gson = new Gson();
        gatosFav[] gatosArray = gson.fromJson(elJson,gatosFav[].class);

        if(gatosArray.length > 0){
            int min = 1;
            int max  = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max-min)+1)) + min;
            int indice = aleatorio-1;

            gatosFav gatofav = gatosArray[indice];

            //redimensionar imagen
            Image image = null;

            try {
                URL url = new URL(gatofav.imagex.getUrl());
                image = ImageIO.read(url);
                ImageIcon fondoGato = new ImageIcon(image);
                /*
                HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
                httpcon.addRequestProperty("User-Agent", "");
                BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());
                ImageIcon fondoGato = new ImageIcon(bufferedImage);*/

                if (fondoGato.getIconWidth() > 800){

                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);

                }

                String menu = "Opciones: \n"
                        + "1. Siguiente \n"
                        + "2. Eliminar Favorito \n"
                        + "3. Volver \n";

                String[] botones = {"Siguiente", "Eliminar Favorito", "Volver"};
                String id_gato = gatofav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato,
                        JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

                int selection = -1;

                //validamos opcion seleccionada
                for (int i = 0; i < botones.length; i++){

                    if(opcion.equals(botones[i])){
                        selection = i;
                    }

                }

                switch (selection){
                    case 0:
                        verFavoritos(apikey);
                        break;
                    case 1:
                        borrarFavorito(gatofav);
                        break;
                    default:
                        break;
                }

            }
            catch (Exception e){
                System.out.println("Error: \n" + e);
            }

        }

    }

    public static void borrarFavorito(gatosFav gatofav){

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites"+gatofav.getId()+"")
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatofav.getApikey())
                    .build();

            Response response = client.newCall(request).execute();

        }
        catch (Exception e){
            System.out.println("Error: \n" + e);
        }

    }

}
