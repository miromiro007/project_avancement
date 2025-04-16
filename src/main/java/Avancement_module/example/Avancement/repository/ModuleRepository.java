package Avancement_module.example.Avancement.repository;

import Avancement_module.example.Avancement.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("SELECT m FROM Module m WHERE m.chefModule.id = :chefModuleId")
    Optional<Module> findByChefModuleId(@Param("chefModuleId") Long chefModuleId);
}