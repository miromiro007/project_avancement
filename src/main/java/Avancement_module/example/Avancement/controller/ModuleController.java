package Avancement_module.example.Avancement.controller;
import Avancement_module.example.Avancement.entity.Module;
import Avancement_module.example.Avancement.entity.Utilisateur;
import Avancement_module.example.Avancement.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/all")
    public ResponseEntity<List<Module>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAllModules());
    }

    @PostMapping("/add")
    public ResponseEntity<Module> createModule(@RequestBody Module module) {
        return ResponseEntity.ok(moduleService.save(module));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleById(@PathVariable Long id) {
        Module module = moduleService.findById(id);
        if (module != null) {
            return ResponseEntity.ok(module);
        } else {
            return ResponseEntity.status(404).body("Module not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateModule(@PathVariable Long id, @RequestBody Module
            module) {
        module.setId(id); // Assurez-vous que l'ID est correct
        Module updatedModule = moduleService.update(module);
        if (updatedModule != null) {
            return ResponseEntity.ok(updatedModule);
        } else {
            return ResponseEntity.status(404).body("Module not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id) {
        moduleService.deleteById(id);
        return ResponseEntity.ok("Module deleted successfully");
    }

    @GetMapping("/{moduleId}/chef-module")
    public ResponseEntity<?> getChefModuleByModuleId(@PathVariable Long moduleId) {
        Utilisateur chefModule = moduleService.getChefModuleByModuleId(moduleId);
        if (chefModule != null) {
            return ResponseEntity.ok(chefModule);
        } else {
            return ResponseEntity.status(404).body("Chef de module not found for this module");
        }
    }
    @GetMapping("/chef-module/{chefModuleId}")
    public ResponseEntity<?> getModuleByChefModuleId(@PathVariable Long chefModuleId) {
        Module module = moduleService.getModuleByChefModuleId(chefModuleId);
        if (module != null) {
            return ResponseEntity.ok(module);
        } else {
            return ResponseEntity.status(404).body("Module not found for this chef de module");
        }
    }



}
