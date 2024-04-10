package com.example.employee.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")

public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 20, message = "Le nom ne peut pas dépasser 20 caractères")
    @Column(name="name")
    private String nom;

    @Email(message = "L'adresse email doit être au format valide")
    @NotBlank(message = "L'adresse email ne peut pas être vide")
    private String email;



}
