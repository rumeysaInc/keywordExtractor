package projects.keywordextractor.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.keywordextractor.core.entities.Role;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
