package Avancement_module.example.Avancement.service;
import Avancement_module.example.Avancement.entity.Chapitre;
import Avancement_module.example.Avancement.entity.Matiere;
import Avancement_module.example.Avancement.repository.ChapitreRepository;
import Avancement_module.example.Avancement.repository.MatiereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChapitreService {

    @Autowired
    private ChapitreRepository chapitreRepository;


    public List<Chapitre> getChapitresByMatiereId(Long matiereId) {
        return chapitreRepository.findByMatiereId(matiereId);
    }


    public Chapitre save(Chapitre chapitre) {
        return chapitreRepository.save(chapitre);
    }


    public Chapitre update(Chapitre chapitre) {
        // Vérifier si le chapitre existe avant de le mettre à jour
        Optional<Chapitre> existingChapitre =
                chapitreRepository.findById(chapitre.getId());
        if (existingChapitre.isPresent()) {
            return chapitreRepository.save(chapitre);
        } else {
            throw new RuntimeException("Chapitre non trouvé avec l'ID : " +
                    chapitre.getId());
        }
    }


    public void deleteById(Long id) {
        chapitreRepository.deleteById(id);
    }

    public Chapitre findById(Long id) {
        return chapitreRepository.findById(id).orElse(null);
    }

    public Matiere getMatiereByChapitreId(Long chapitreId) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId).orElse(null);
        if (chapitre != null) {
            return chapitre.getMatiere();
        }
        return null;
    }
}
