package Avancement_module.example.Avancement.repository;
import Avancement_module.example.Avancement.entity.Chapitre;
import Avancement_module.example.Avancement.entity.ChapitreAvancement;
import Avancement_module.example.Avancement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChapitreAvancementRepository extends JpaRepository<ChapitreAvancement, Long> { // Exemple de méthode personnalisée : récupérer tous les avancements d'unprofesseur spécifique
    List<ChapitreAvancement> findByProfesseurId(Long professeurId);

    // Exemple de méthode personnalisée : récupérer tous les avancements d'un chapitrespécifique
    List<ChapitreAvancement> findByChapitreId(Long chapitreId);

    List<ChapitreAvancement> findByValideFalse();

    @Query("SELECT ca FROM ChapitreAvancement ca WHERE ca.dateEcheance < CURRENT_DATE AND ca.valide = false")
    List<ChapitreAvancement> findRetards();

    // Récupérer le professeur associé à un avancement via son ID
    @Query("SELECT ca.professeur FROM ChapitreAvancement ca WHERE ca.id = :avancementId")
    Optional<Utilisateur> findProfesseurByAvancementId(@Param("avancementId") Long avancementId);

    // Récupérer le chapitre associé à un avancement via son ID
    @Query("SELECT ca.chapitre FROM ChapitreAvancement ca WHERE ca.id = :avancementId")
    Optional<Chapitre> findChapitreByAvancementId(@Param("avancementId") Long avancementId);
}
