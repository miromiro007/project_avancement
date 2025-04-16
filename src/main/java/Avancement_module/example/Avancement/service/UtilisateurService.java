package Avancement_module.example.Avancement.service;

import Avancement_module.example.Avancement.entity.Utilisateur;
import Avancement_module.example.Avancement.entity.Utilisateur.Role;
import Avancement_module.example.Avancement.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> authenticate(String email, String motDePasse) {
        return utilisateurRepository.findByEmail(email)
                .filter(u -> u.getMotDePasse().equals(motDePasse));
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

}
