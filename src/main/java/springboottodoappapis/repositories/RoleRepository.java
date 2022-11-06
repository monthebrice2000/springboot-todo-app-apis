package springboottodoappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboottodoappapis.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
