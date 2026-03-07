package id.tunas.coffee.dto;

import java.util.List;

public class Barista {
    private String id;
    private String name;
    private String img;
    private String description;
    private String badge;

    public Barista(String id, String name, String img, String badge) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.badge = badge;
    }

    public static List<Barista> createDummy(){
        return List.of(
                new Barista("a1", "Victor", "https://i.ibb.co.com/CrfGCvS/Photo-Profile.jpg", "green"),
                new Barista("a2", "Andrey", "https://i.ibb.co.com/b5Skzy0R/Photo-Profile-1.jpg", "green"),
                new Barista("a3", "Vera", "https://i.ibb.co.com/PskLnXvB/Photo-Profile-2.jpg", "red"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
