package az.growlabtask.controller;

import az.growlabtask.dto.request.ModuleRequest;
import az.growlabtask.entity.Module;
import az.growlabtask.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/module")
public class ModuleController {
    private final ModuleService moduleService;
    @GetMapping("/{id}")
    public Module findById(@PathVariable Long id){
        return moduleService.findById(id);
    }
    @GetMapping
    public List<Module> findAll(){
        return moduleService.findAll();
    }
    @PutMapping
    public Module add(@Valid @RequestBody ModuleRequest moduleRequest){
        return moduleService.save(moduleRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        moduleService.deleteById(id);
    }
}
