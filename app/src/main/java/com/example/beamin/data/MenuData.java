package com.example.beamin.data;

public class MenuData {
    private int imgUrl=0;
    private String name;
    private String menu;
    public int numId;

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getMenu() {
        return menu;
    }

    public MenuData(int imgUrl, String name, String menu) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.menu = menu;
    }
}
//Store list Data for listview
