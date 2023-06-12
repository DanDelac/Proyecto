package com.example.proyecto.data.model;

public class Account {
    private String idUser;
    private String useAccount;
    private String useName;
    private String useLastN;
    private String useCorre;

    public Account() {
    }

    public Account(String idUser, String useAccount, String useName, String useLastN, String useCorre) {
        this.idUser = idUser;
        this.useAccount = useAccount;
        this.useName = useName;
        this.useLastN = useLastN;
        this.useCorre = useCorre;
    }

    public String getUseAccount() {
        return useAccount;
    }

    public void setUseAccount(String useAccount) {
        this.useAccount = useAccount;
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
                ", useAccount='" + useAccount + '\'' +
                ", useName='" + useName + '\'' +
                ", useLastN='" + useLastN + '\'' +
                ", useCorre='" + useCorre + '\'' +
                '}';
    }
}
