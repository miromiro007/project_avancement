package Avancement_module.example.Avancement.service;
import Avancement_module.example.Avancement.entity.Matiere;
import Avancement_module.example.Avancement.entity.Utilisateur;
import Avancement_module.example.Avancement.repository.MatiereRepository;
import Avancement_module.example.Avancement.repository.ModuleRepository;
import Avancement_module.example.Avancement.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    public List<Matiere> getMatieresByModuleId(Long moduleId) {
        return matiereRepository.findByModuleId(moduleId);
    }

    public Matiere save(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public Matiere findById(Long id) {
        return matiereRepository.findById(id).orElse(null);
    }

    public Matiere update(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public void deleteById(Long id) {
        matiereRepository.deleteById(id);
    }
    public Utilisateur getProfesseurByMatiereId(Long matiereId) {
        Matiere matiere = matiereRepository.findById(matiereId).orElse(null);
        if (matiere != null) {
            return matiere.getProfesseur();
        }
        return null;
    }

    public List<Matiere> getMatieresByProfesseurId(Long professeurId) {
        return matiereRepository.findByProfesseurId(professeurId);
    }
}
