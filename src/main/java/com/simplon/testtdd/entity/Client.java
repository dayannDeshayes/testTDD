package com.simplon.testtdd.entity;

import lombok.Data;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String numeroTelephone;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    public enum Sexe {
        HOMME,
        FEMME
    }
}