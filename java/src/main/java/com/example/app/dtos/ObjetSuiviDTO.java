package com.example.app.dtos;

public class ObjetSuiviDTO {
    private Long id;
    private String nomObjet;
    private String typeObjet;
    private Long utilisateurId;

    // Constructeurs
    public ObjetSuiviDTO() {}

    public ObjetSuiviDTO(Long id, String nomObjet, String typeObjet, Long utilisateurId) {
        this.id = id;
        this.nomObjet = nomObjet;
        this.typeObjet = typeObjet;
        this.utilisateurId = utilisateurId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomObjet() {
        return nomObjet;
    }

    public void setNomObjet(String nomObjet) {
        this.nomObjet = nomObjet;
    }

    public String getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(String typeObjet) {
        this.typeObjet = typeObjet;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
