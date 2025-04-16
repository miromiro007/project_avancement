package Avancement_module.example.Avancement.service;

import Avancement_module.example.Avancement.entity.Chapitre;
import Avancement_module.example.Avancement.entity.ChapitreAvancement;
import Avancement_module.example.Avancement.entity.Utilisateur;
import Avancement_module.example.Avancement.repository.ChapitreAvancementRepository;
import Avancement_module.example.Avancement.repository.ChapitreRepository;
import Avancement_module.example.Avancement.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChapitreAvancementService {

    @Autowired
    private ChapitreAvancementRepository chapitreAvancementRepository;

    public List<ChapitreAvancement> getAvancementsByProfesseurId(Long professeurId) {
        return chapitreAvancementRepository.findByProfesseurId(professeurId);
    }

    public List<ChapitreAvancement> getAvancementsByChapitreId(Long chapitreId) {
        return chapitreAvancementRepository.findByChapitreId(chapitreId);
    }

    public ChapitreAvancement save(ChapitreAvancement avancement) {
        // Vérifier si la date de validation est définie
        if (avancement.getDateValidation() == null) {
            avancement.setDateValidation(LocalDate.now());
        }
        return chapitreAvancementRepository.save(avancement);
    }

    public ChapitreAvancement update(ChapitreAvancement avancement) {
        return chapitreAvancementRepository.save(avancement);
    }

    public ChapitreAvancement validateAvancement(Long id, boolean isValid) {
        ChapitreAvancement avancement =
                chapitreAvancementRepository.findById(id).orElse(null);
        if (avancement != null) {
            avancement.setValide(isValid);
            return chapitreAvancementRepository.save(avancement);
        }
        return null;
    }

    public void deleteById(Long id) {
        chapitreAvancementRepository.deleteById(id);
    }

    // Récupérer les avancements non validés
    public List<ChapitreAvancement> getAvancementsNonValides() {
        return chapitreAvancementRepository.findByValideFalse();
    }

    // Récupérer les avancements en retard
    public List<ChapitreAvancement> getAvancementsEnRetard() {
        return chapitreAvancementRepository.findRetards();
    }

    public ChapitreAvancement validateAvancementWithComment(Long id, boolean isValid, String commentaire) {
        ChapitreAvancement avancement = chapitreAvancementRepository.findById(id).orElse(null);
        if (avancement != null) {
            avancement.setValide(isValid);
            avancement.setCommentaire(commentaire);
            avancement.setDateValidation(LocalDate.now());
            return chapitreAvancementRepository.save(avancement);
        }
        return null;
    }

    public ChapitreAvancement updateDateEcheance(Long id, LocalDate nouvelleDateEcheance) {
        ChapitreAvancement avancement = chapitreAvancementRepository.findById(id).orElse(null);
        if (avancement != null) {
            avancement.setDateEcheance(nouvelleDateEcheance);
            return chapitreAvancementRepository.save(avancement);
        }
        return null;
    }

    // Récupérer le professeur associé à un avancement via son ID
    public Utilisateur getProfesseurByAvancementId(Long avancementId) {
        return chapitreAvancementRepository.findProfesseurByAvancementId(avancementId).orElse(null);
    }

    // Récupérer le chapitre associé à un avancement via son ID
    public Chapitre getChapitreByAvancementId(Long avancementId) {
        return chapitreAvancementRepository.findChapitreByAvancementId(avancementId).orElse(null);
    }

    public ChapitreAvancement updateAvancement(long id, int heuresRealisees, String commentaire) {
        ChapitreAvancement avancement = chapitreAvancementRepository.findById(id).orElse(null);
        if (avancement != null) {
            avancement.setHeuresRealisees(heuresRealisees);
            avancement.setCommentaire(commentaire);
            avancement.setValide(false); // Réinitialiser la validation après modification
            return chapitreAvancementRepository.save(avancement);
        }
        return null;
    }

}
