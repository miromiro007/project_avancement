package Avancement_module.example.Avancement.repository;
import Avancement_module.example.Avancement.entity.Chapitre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {
    // Exemple de méthode personnalisée : récupérer tous les chapitres d'une matièrespécifique
    List<Chapitre> findByMatiereId(Long matiereId);
}