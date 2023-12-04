package az.growlabtask.service;

import az.growlabtask.dto.request.ModuleRequest;
import az.growlabtask.entity.Module;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleService {

    Module save(ModuleRequest moduleRequest);

    List<Module> findAll();

    Module findById(Long id);

    void deleteById(Long id);
}
