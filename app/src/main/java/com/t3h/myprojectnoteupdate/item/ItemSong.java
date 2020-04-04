package com.t3h.myprojectnoteupdate.item;

public class ItemSong {
    private String data;
    private String name;

    public ItemSong(String data, String name) {
        this.data = data;
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
