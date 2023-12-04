package az.growlabtask.service.impl;

import az.growlabtask.dto.request.AttributeRequest;
import az.growlabtask.entity.Attribute;
import az.growlabtask.entity.Module;
import az.growlabtask.entity.User;
import az.growlabtask.exception.CustomNotFoundException;
import az.growlabtask.repository.AttributeRepository;
import az.growlabtask.repository.ModuleRepository;
import az.growlabtask.repository.UserRepository;
import az.growlabtask.service.AttributeService;
import az.growlabtask.service.ModuleService;
import az.growlabtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final ModuleService moduleService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;


    @Override
    public Attribute save(AttributeRequest attributeRequest) {
        if(!attributeRepository.findByAttribute(attributeRequest.getAttribute()).isPresent()){
            Attribute attribute = new Attribute();
            attribute.setAttribute(attributeRequest.getAttribute());
            return attributeRepository.save(attribute);
        }
        throw new IllegalArgumentException("Attribute already exist");
    }

    @Override
    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute findById(Long id) {
        return attributeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Can't find attribute with given id"));
    }

    @Override
    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }

    @Override
    public void addAttributeToModule(Long attributeId, Long moduleId) {
        Attribute attribute = findById(attributeId);
        Module module = moduleService.findById(moduleId);
        module.getAttributes().add(attribute);
        moduleRepository.save(module);
    }

    @Override
    public void addAttributeToUser(Long attributeId, Long userId) {
        Attribute attribute = findById(attributeId);
        User user = userService.findById(userId);
        user.getAttributes().add(attribute);
        userRepository.save(user);
    }
}
