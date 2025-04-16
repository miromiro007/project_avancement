package Avancement_module.example.Avancement.controller;
import Avancement_module.example.Avancement.entity.*;
import Avancement_module.example.Avancement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String
            motDePasse) {
        var utilisateur = utilisateurService.authenticate(email, motDePasse);
        if (utilisateur.isPresent()) {
            return ResponseEntity.ok(utilisateur.get());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur
                                                          utilisateur) {
        return ResponseEntity.ok(utilisateurService.save(utilisateur));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody
    Utilisateur utilisateur) {
        utilisateur.setId(id); // Assurez-vous que l'ID est correct
        Utilisateur updatedUser = utilisateurService.update(utilisateur);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}