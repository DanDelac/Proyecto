package com.example.proyecto.Entidades;

public class Unit_Session {

    private String Des_Unidad;
    private String Tema_1;
    private String Tema_2;
    private String Tema_3;
    private String Tema_4;

    public Unit_Session() {
    }

    public Unit_Session(String des_Unidad, String tema_1, String tema_2, String tema_3, String tema_4) {
        Des_Unidad = des_Unidad;
        Tema_1 = tema_1;
        Tema_2 = tema_2;
        Tema_3 = tema_3;
        Tema_4 = tema_4;
    }

    public String getDes_Unidad() {
        return Des_Unidad;
    }

    public void setDes_Unidad(String des_Unidad) {
        Des_Unidad = des_Unidad;
    }

    public String getTema_1() {
        return Tema_1;
    }

    public void setTema_1(String tema_1) {
        Tema_1 = tema_1;
    }

    public String getTema_2() {
        return Tema_2;
    }

    public void setTema_2(String tema_2) {
        Tema_2 = tema_2;
    }

    public String getTema_3() {
        return Tema_3;
    }

    public void setTema_3(String tema_3) {
        Tema_3 = tema_3;
    }

    public String getTema_4() {
        return Tema_4;
    }

    public void setTema_4(String tema_4) {
        Tema_4 = tema_4;
    }
}
