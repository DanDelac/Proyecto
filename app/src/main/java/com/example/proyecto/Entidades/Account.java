package com.example.proyecto.Entidades;

public class Account {
    private String idUser;
    private String useName;
    private String useLastN;
    private String useCorre;

    public Account() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getUseLastN() {
        return useLastN;
    }

    public void setUseLastN(String useLastN) {
        this.useLastN = useLastN;
    }

    public String getUseCorre() {
        return useCorre;
    }

    public void setUseCorre(String useCorre) {
        this.useCorre = useCorre;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idUser='" + idUser + '\'' +
                ", useName='" + useName + '\'' +
                ", useLastN='" + useLastN + '\'' +
                ", useCorre='" + useCorre + '\'' +
                '}';
    }
}
