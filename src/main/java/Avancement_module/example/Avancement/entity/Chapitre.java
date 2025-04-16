package Avancement_module.example.Avancement.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private int heuresAllouees;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;
}
