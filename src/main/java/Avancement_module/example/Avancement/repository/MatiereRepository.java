package Avancement_module.example.Avancement.repository;
import Avancement_module.example.Avancement.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    List<Matiere> findByModuleId(Long moduleId);

    @Query("SELECT m FROM Matiere m WHERE m.professeur.id = :professeurId")
    List<Matiere> findByProfesseurId(@Param("professeurId") Long professeurId);


}