package az.growlab.growlabtask.repository;

import az.growlab.growlabtask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
