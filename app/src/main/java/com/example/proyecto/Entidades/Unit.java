package com.example.proyecto.Entidades;

import java.io.Serializable;

public class Unit {

    private Integer idUnit;
    private String uniDesc;

    public Unit(){}
    public Unit(Integer idUnit, String uniDesc) {
        this.idUnit = idUnit;
        this.uniDesc = uniDesc;
    }

    public Integer getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    public String getUniDesc() {
        return uniDesc;
    }

    public void setUniDesc(String uniDesc) {
        this.uniDesc = uniDesc;
    }
}
