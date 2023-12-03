package az.growlabtask.service;

import az.growlabtask.dto.request.AttributeRequest;
import az.growlabtask.entity.Attribute;
import az.growlabtask.entity.Module;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttributeService {
    Attribute save(AttributeRequest attributeRequest);

    List<Attribute> findAll();

    Attribute findById(Long id);

    void deleteById(Long id);

    void addAttributeToModule(Long attributeId, Long moduleId);

    void addAttributeToUser(Long attributeId, Long userId);
}
