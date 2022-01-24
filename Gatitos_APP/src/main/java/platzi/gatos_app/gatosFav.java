package platzi.gatos_app;

public class gatosFav {

    String id;
    String image_id;
    String apikey = "fb5e0b30-f2a9-4ac4-ba18-3b1abed1fd88";
    ImageX imagex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public ImageX getImagex() {
        return imagex;
    }

    public void setImagex(ImageX imagex) {
        this.imagex = imagex;
    }
}
