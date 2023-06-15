package com.simplon.testtdd.entity.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String email;

    private String nom;

    private String prenom;

    private String numeroTelephone;

    private String dateNaissance;

    private String sexe;

    private Boolean isActive;
}
