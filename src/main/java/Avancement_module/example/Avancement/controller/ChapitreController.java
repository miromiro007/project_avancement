package Avancement_module.example.Avancement.controller;
import Avancement_module.example.Avancement.entity.*;
import Avancement_module.example.Avancement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/chapitres")
public class ChapitreController {
    @Autowired
    private ChapitreService chapitreService;

    @GetMapping("/matiere/{matiereId}")
    public ResponseEntity<List<Chapitre>> getChapitresByMatiereId(@PathVariable Long
                                                                          matiereId) {
        return ResponseEntity.ok(chapitreService.getChapitresByMatiereId(matiereId));
    }

    @PostMapping("/add")
    public ResponseEntity<Chapitre> createChapitre(@RequestBody Chapitre chapitre) {
        return ResponseEntity.ok(chapitreService.save(chapitre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChapitreById(@PathVariable Long id) {
        Chapitre chapitre = chapitreService.findById(id);
        if (chapitre != null) {
            return ResponseEntity.ok(chapitre);
        } else {
            return ResponseEntity.status(404).body("Chapitre not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateChapitre(@PathVariable Long id, @RequestBody
    Chapitre chapitre) {
        chapitre.setId(id); // Assurez-vous que l'ID est correct
        Chapitre updatedChapitre = chapitreService.update(chapitre);
        if (updatedChapitre != null) {
            return ResponseEntity.ok(updatedChapitre);
        } else {
            return ResponseEntity.status(404).body("Chapitre not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChapitre(@PathVariable Long id) {
        chapitreService.deleteById(id);
        return ResponseEntity.ok("Chapitre deleted successfully");
    }

    @GetMapping("/{chapitreId}/matiere")
    public ResponseEntity<?> getMatiereByChapitreId(@PathVariable Long chapitreId) {
        Matiere matiere = chapitreService.getMatiereByChapitreId(chapitreId);
        if (matiere != null) {
            return ResponseEntity.ok(matiere);
        } else {
            return ResponseEntity.status(404).body("Matiere not found for this chapitre");
        }
    }
}


