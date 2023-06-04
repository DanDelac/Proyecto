package com.example.proyecto.Entidades;

import android.widget.ImageView;

public class Glosary_listElement {
    public ImageView Icon;
    public String Title;
    public String Description;

    public Glosary_listElement(ImageView icon, String title, String description) {
        Icon = icon;
        Title = title;
        Description = description;
    }

    public ImageView getIcon() {
        return Icon;
    }

    public void setIcon(ImageView icon) {
        Icon = icon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
