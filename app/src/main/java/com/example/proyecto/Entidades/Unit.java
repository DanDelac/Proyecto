package com.example.proyecto.Entidades;

import java.io.Serializable;

public class Unit {

    private Integer idUnit;
    private String uniDesc;
    private Integer idUseUni;
    private Integer sesD;

    public Unit(){}

    public Unit(Integer idUnit, String uniDesc, Integer idUseUni, Integer sesD) {
        this.idUnit = idUnit;
        this.uniDesc = uniDesc;
        this.idUseUni = idUseUni;
        this.sesD = sesD;
    }

    public Integer getIdUseUni() {
        return idUseUni;
    }

    public void setIdUseUni(Integer idUseUni) {
        this.idUseUni = idUseUni;
    }

    public Integer getSesD() {
        return sesD;
    }

    public void setSesD(Integer sesD) {
        this.sesD = sesD;
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
