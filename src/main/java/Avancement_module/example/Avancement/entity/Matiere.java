package Avancement_module.example.Avancement.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Utilisateur professeur;
}
