package Avancement_module.example.Avancement.service;
import Avancement_module.example.Avancement.entity.Module;
import Avancement_module.example.Avancement.entity.Utilisateur;
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
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    public Module findById(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }

    public Module update(Module module) {
        return moduleRepository.save(module);
    }

    public void deleteById(Long id) {
        moduleRepository.deleteById(id);
    }

    public Utilisateur getChefModuleByModuleId(Long moduleId) {
        Module module = moduleRepository.findById(moduleId).orElse(null);
        if (module != null) {
            return module.getChefModule();
        }
        return null;
    }
    public Module getModuleByChefModuleId(Long chefModuleId) {
        return moduleRepository.findByChefModuleId(chefModuleId).orElse(null);
    }
}