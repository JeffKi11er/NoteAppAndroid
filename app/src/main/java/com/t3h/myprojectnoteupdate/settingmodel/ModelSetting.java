package com.t3h.myprojectnoteupdate.settingmodel;

public class ModelSetting {
    String title;
    String descrip;

    public ModelSetting(String title, String descrip) {
        this.title = title;
        this.descrip = descrip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
