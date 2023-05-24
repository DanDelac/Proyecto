package com.example.proyecto.Entidades;

public class _Session {
    private Integer idSes;
    private Integer idUnit;
    private String sesDesc;
    private Integer sesPorc;

    private Integer idUseSes;
    public _Session() {
    }

    public _Session(Integer idSes, Integer idUnit, String sesDesc, Integer sesPorc, Integer idUseSes) {
        this.idSes = idSes;
        this.idUnit = idUnit;
        this.sesDesc = sesDesc;
        this.sesPorc = sesPorc;
        this.idUseSes = idUseSes;
    }

    public Integer getIdUseSes() {
        return idUseSes;
    }

    public void setIdUseSes(Integer idUseSes) {
        this.idUseSes = idUseSes;
    }

    public Integer getSesPorc() {
        return sesPorc;
    }

    public void setSesPorc(Integer sesPorc) {
        this.sesPorc = sesPorc;
    }

    public Integer getIdSes() {
        return idSes;
    }

    public void setIdSes(Integer idSes) {
        this.idSes = idSes;
    }

    public Integer getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    public String getSesDesc() {
        return sesDesc;
    }

    public void setSesDesc(String sesDesc) {
        this.sesDesc = sesDesc;
    }
}
