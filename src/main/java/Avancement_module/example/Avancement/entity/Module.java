package Avancement_module.example.Avancement.entity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "chef_module_id")
    private Utilisateur chefModule;
}