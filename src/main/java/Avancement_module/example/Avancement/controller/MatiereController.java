package Avancement_module.example.Avancement.controller;
import Avancement_module.example.Avancement.entity.*;
import Avancement_module.example.Avancement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.Module;
import java.util.List;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {
    @Autowired
    private MatiereService matiereService;

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<Matiere>> getMatieresByModuleId(@PathVariable Long
                                                                       moduleId) {
        return ResponseEntity.ok(matiereService.getMatieresByModuleId(moduleId));
    }

    @PostMapping("/add")
    public ResponseEntity<Matiere> createMatiere(@RequestBody Matiere matiere) {
        return ResponseEntity.ok(matiereService.save(matiere));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMatiereById(@PathVariable Long id) {
        Matiere matiere = matiereService.findById(id);
        if (matiere != null) {
            return ResponseEntity.ok(matiere);
        } else {
            return ResponseEntity.status(404).body("Matiere not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMatiere(@PathVariable Long id, @RequestBody Matiere
            matiere) {
        matiere.setId(id); // Assurez-vous que l'ID est correct
        Matiere updatedMatiere = matiereService.update(matiere);
        if (updatedMatiere != null) {
            return ResponseEntity.ok(updatedMatiere);
        } else {
            return ResponseEntity.status(404).body("Matiere not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMatiere(@PathVariable Long id) {
        matiereService.deleteById(id);
        return ResponseEntity.ok("Matiere deleted successfully");
    }

    @GetMapping("/{matiereId}/professeur")
    public ResponseEntity<?> getProfesseurByMatiereId(@PathVariable Long matiereId) {
        Utilisateur professeur = matiereService.getProfesseurByMatiereId(matiereId);
        if (professeur != null) {
            return ResponseEntity.ok(professeur);
        } else {
            return ResponseEntity.status(404).body("Professeur not found for this matiere");
        }
    }

    @GetMapping("/professeur/{professeurId}")
    public ResponseEntity<?> getMatieresByProfesseurId(@PathVariable Long professeurId) {
        List<Matiere> matieres = matiereService.getMatieresByProfesseurId(professeurId);
        if (!matieres.isEmpty()) {
            return ResponseEntity.ok(matieres);
        } else {
            return ResponseEntity.status(404).body("Aucune matière trouvée pour ce professeur");
        }
    }



}

