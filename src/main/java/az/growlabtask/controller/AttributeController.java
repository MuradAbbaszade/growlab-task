package az.growlabtask.controller;

import az.growlabtask.dto.request.AttributeRequest;
import az.growlabtask.dto.request.ModuleRequest;
import az.growlabtask.entity.Attribute;
import az.growlabtask.entity.Module;
import az.growlabtask.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attribute")
public class AttributeController {
    private final AttributeService attributeService;

    @GetMapping("/{id}")
    public Attribute findById(@PathVariable Long id){
        return attributeService.findById(id);
    }
    @GetMapping
    public List<Attribute> findAll(){
        return attributeService.findAll();
    }
    @PutMapping
    public Attribute add(@Valid @RequestBody AttributeRequest attributeRequest){
        return attributeService.save(attributeRequest);
    }
    @PutMapping("/add/module")
    public void addAttributeToModule(@RequestParam("attributeId") Long attributeId,
                                          @RequestParam("moduleId") Long moduleId){
        attributeService.addAttributeToModule(attributeId,moduleId);
    }
    @PutMapping("/add/user")
    public void addAttributeToUser(@RequestParam("attributeId") Long attributeId,
                                     @RequestParam("userId") Long userId){
        attributeService.addAttributeToUser(attributeId,userId);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        attributeService.deleteById(id);
    }
}
