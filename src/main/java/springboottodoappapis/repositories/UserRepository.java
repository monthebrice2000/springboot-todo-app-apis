package springboottodoappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboottodoappapis.entities.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByUsername(String username);
    public List<UserEntity> findAll();
}
