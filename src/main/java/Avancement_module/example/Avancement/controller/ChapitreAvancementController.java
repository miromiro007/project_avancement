package Avancement_module.example.Avancement.controller;
import Avancement_module.example.Avancement.entity.*;
import Avancement_module.example.Avancement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/avancements")
public class ChapitreAvancementController {
    @Autowired
    private ChapitreAvancementService chapitreAvancementService;

    @GetMapping("/professeur/{professeurId}")
    public ResponseEntity<List<ChapitreAvancement>>
    getAvancementsByProfesseurId(@PathVariable Long professeurId) {
        return
                ResponseEntity.ok(chapitreAvancementService.getAvancementsByProfesseurId(professeurId)
                );
    }

    @GetMapping("/chapitre/{chapitreId}")
    public ResponseEntity<List<ChapitreAvancement>>
    getAvancementsByChapitreId(@PathVariable Long chapitreId) {
        return
                ResponseEntity.ok(chapitreAvancementService.getAvancementsByChapitreId(chapitreId));
    }

    @PostMapping("add")
    public ResponseEntity<ChapitreAvancement> createAvancement(@RequestBody
                                                               ChapitreAvancement avancement) {
        return ResponseEntity.ok(chapitreAvancementService.save(avancement));
    }

    @PutMapping("/{id}/validate")
    public ResponseEntity<?> validateAvancement(@PathVariable Long id, @RequestParam
    boolean isValid) {
        ChapitreAvancement avancement =
                chapitreAvancementService.validateAvancement(id, isValid);
        if (avancement != null) {
            return ResponseEntity.ok(avancement);
        } else {
            return ResponseEntity.status(404).body("Avancement not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAvancement(@PathVariable Long id) {
        chapitreAvancementService.deleteById(id);
        return ResponseEntity.ok("Avancement deleted successfully");
    }
    @GetMapping("/non-valides")
    public ResponseEntity<List<ChapitreAvancement>> getAvancementsNonValides() {
        return ResponseEntity.ok(chapitreAvancementService.getAvancementsNonValides());
    }

    @GetMapping("/retards")
    public ResponseEntity<List<ChapitreAvancement>> getAvancementsEnRetard() {
        return ResponseEntity.ok(chapitreAvancementService.getAvancementsEnRetard());
    }

    // Valider un avancement avec un commentaire
    @PutMapping("/{id}/validate-with-comment")
    public ResponseEntity<?> validateAvancementWithComment(
            @PathVariable Long id,
            @RequestParam boolean isValid,
            @RequestParam String commentaire) {
        ChapitreAvancement avancement = chapitreAvancementService.validateAvancementWithComment(id, isValid, commentaire);
        if (avancement != null) {
            return ResponseEntity.ok(avancement);
        } else {
            return ResponseEntity.status(404).body("Avancement not found");
        }
    }

    // Mettre à jour la date d'échéance d'un avancement
    @PutMapping("/{id}/update-date-echeance")
    public ResponseEntity<?> updateDateEcheance(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nouvelleDateEcheance) {
        ChapitreAvancement avancement = chapitreAvancementService.updateDateEcheance(id, nouvelleDateEcheance);
        if (avancement != null) {
            return ResponseEntity.ok(avancement);
        } else {
            return ResponseEntity.status(404).body("Avancement not found");
        }
    }

    // Récupérer le professeur associé à un avancement via son ID
    @GetMapping("/{avancementId}/professeur")
    public ResponseEntity<?> getProfesseurByAvancementId(@PathVariable Long avancementId) {
        Utilisateur professeur = chapitreAvancementService.getProfesseurByAvancementId(avancementId);
        if (professeur != null) {
            return ResponseEntity.ok(professeur);
        } else {
            return ResponseEntity.status(404).body("Professeur not found for this avancement");
        }
    }

    // Récupérer le chapitre associé à un avancement via son ID
    @GetMapping("/{avancementId}/chapitre")
    public ResponseEntity<?> getChapitreByAvancementId(@PathVariable Long avancementId) {
        Chapitre chapitre = chapitreAvancementService.getChapitreByAvancementId(avancementId);
        if (chapitre != null) {
            return ResponseEntity.ok(chapitre);
        } else {
            return ResponseEntity.status(404).body("Chapitre not found for this avancement");
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateAvancement(
            @PathVariable Long id,
            @RequestParam int heuresRealisees,
            @RequestParam(required = false) String commentaire) {

        ChapitreAvancement avancement = chapitreAvancementService.updateAvancement(id, heuresRealisees, commentaire);
        if (avancement != null) {
            return ResponseEntity.ok(avancement);
        } else {
            return ResponseEntity.status(404).body("Avancement not found");
        }
    }



}
