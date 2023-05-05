package com.example.proyecto.Entidades;

public class _Session {
    private Integer idSes;
    private Integer idUnit;
    private String sesDesc;

    public _Session() {
    }

    public _Session(Integer idSes, Integer idUnit, String sesDesc) {
        this.idSes = idSes;
        this.idUnit = idUnit;
        this.sesDesc = sesDesc;
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
