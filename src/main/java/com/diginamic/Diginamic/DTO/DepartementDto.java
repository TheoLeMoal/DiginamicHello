package com.diginamic.Diginamic.DTO;

public class DepartementDto {
    private String code;
    private String nom;

    public DepartementDto() {
    }

    public DepartementDto(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "DepartementDto{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}