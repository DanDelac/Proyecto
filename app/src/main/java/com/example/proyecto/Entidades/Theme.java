package com.example.proyecto.Entidades;

import java.io.Serializable;

public class Theme implements Serializable {
    private String idSes;
    private String sesDesc;
    private String idTheme;
    private String theDesc;
    private String imgDesc;

    public Theme() {
    }

    public Theme(String idSes, String sesDesc, String idTheme, String theDesc, String imgDesc) {
        this.idSes = idSes;
        this.sesDesc = sesDesc;
        this.idTheme = idTheme;
        this.theDesc = theDesc;
        this.imgDesc = imgDesc;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public String getIdSes() {
        return idSes;
    }

    public void setIdSes(String idSes) {
        this.idSes = idSes;
    }

    public String getSesDesc() {
        return sesDesc;
    }

    public void setSesDesc(String sesDesc) {
        this.sesDesc = sesDesc;
    }

    public String getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(String idTheme) {
        this.idTheme = idTheme;
    }

    public String getTheDesc() {
        return theDesc;
    }

    public void setTheDesc(String theDesc) {
        this.theDesc = theDesc;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "idSes='" + idSes + '\'' +
                ", sesDesc='" + sesDesc + '\'' +
                ", idTheme='" + idTheme + '\'' +
                ", theDesc='" + theDesc + '\'' +
                ", imgDesc='" + imgDesc + '\'' +
                '}';
    }
}
