package id.tunas.coffee.dto;

import java.util.List;

public class OnBoardingItem {
    String img;
    String title;
    String description;

    public OnBoardingItem(String img, String title, String description) {
        this.img = img;
        this.title = title;
        this.description = description;
    }

    public static List<OnBoardingItem> createDummy(){
        return List.of(
                new OnBoardingItem("","Arabica Beans","Arabica adalah jenis biji kopi yang paling populer di dunia. Memiliki rasa yang lebih halus, sedikit asam, dan aroma yang kaya."),
                new OnBoardingItem("","Robusta Beans","Robusta memiliki rasa yang lebih kuat dan pahit dengan kandungan kafein lebih tinggi. Biasanya digunakan untuk espresso dan kopi dengan rasa yang lebih bold."),
                new OnBoardingItem("","Liberica Beans","Liberica adalah jenis biji kopi yang lebih jarang ditemukan dibanding Arabica dan Robusta. Memiliki aroma khas yang smoky dan woody dengan karakter rasa yang unik."),
                new OnBoardingItem("","Excelsa Beans","Excelsa dikenal dengan rasa yang kompleks dan sedikit fruity. Jenis ini sering digunakan dalam campuran kopi untuk menambah kedalaman dan karakter rasa."));
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
