package Avancement_module.example.Avancement.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        PROFESSEUR, CHEF_MODULE
    }
}

