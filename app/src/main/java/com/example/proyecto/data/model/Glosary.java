package com.example.proyecto.data.model;

public class Glosary {
    public Integer Icon;
    public String Title;
    public String Description;
    public String Url;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Glosary(Integer icon, String title, String description, String url) {
        Icon = icon;
        Title = title;
        Description = description;
        Url = url;
    }

    public Integer getIcon() {
        return Icon;
    }

    public void setIcon(Integer icon) {
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
