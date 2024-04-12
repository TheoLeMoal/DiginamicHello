package com.diginamic.Diginamic.DTO;

public class VilleDto {
    private String nom;
    private int nbHabitants;
    private String codeDepartement;

    public VilleDto() {
    }

    public VilleDto(String nom, int nbHabitants, String codeDepartement) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.codeDepartement = codeDepartement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    @Override
    public String toString() {
        return "VilleDto{" +
                "nom='" + nom + '\'' +
                ", nbHabitants=" + nbHabitants +
                ", codeDepartement='" + codeDepartement + '\'' +
                '}';
    }
}