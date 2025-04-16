package Avancement_module.example.Avancement.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ChapitreAvancement {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "chapitre_id")
        private Chapitre chapitre;

        @ManyToOne
        @JoinColumn(name = "professeur_id")
        private Utilisateur professeur;

        private int heuresRealisees;
        private LocalDate dateValidation;
        private boolean valide;


        // Nouveaux champs ajoutés
        private LocalDate dateEcheance; // Date limite pour valider cet avancement
        private String commentaire;    // Commentaire du chef de module lors de la validation

};
