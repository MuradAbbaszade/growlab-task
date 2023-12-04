package az.growlabtask.service;

import az.growlabtask.dto.request.ModuleRequest;
import az.growlabtask.entity.Module;
import az.growlabtask.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void deleteById(Long id);
}
