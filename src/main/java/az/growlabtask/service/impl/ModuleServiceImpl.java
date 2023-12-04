package az.growlabtask.service.impl;

import az.growlabtask.dto.request.AttributeRequest;
import az.growlabtask.dto.request.ModuleRequest;
import az.growlabtask.entity.Attribute;
import az.growlabtask.entity.Module;
import az.growlabtask.exception.CustomNotFoundException;
import az.growlabtask.repository.AttributeRepository;
import az.growlabtask.repository.ModuleRepository;
import az.growlabtask.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModelMapper modelMapper;
    private final AttributeRepository attributeRepository;

    @Override
    public Module save(ModuleRequest moduleRequest) {
        if(!moduleRepository.findByName(moduleRequest.getName()).isPresent()){
            Module module = new Module();
            module.setName(moduleRequest.getName());
            return moduleRepository.save(module);
        }
        throw new IllegalArgumentException("Module already exist");
    }

    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Module findById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Can't find module with given id"));
    }

    public void deleteById(Long id) {
        moduleRepository.deleteById(id);
    }
}
