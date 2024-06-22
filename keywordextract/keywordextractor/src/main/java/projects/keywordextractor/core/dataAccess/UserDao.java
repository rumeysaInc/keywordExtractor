package projects.keywordextractor.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.keywordextractor.core.entities.UserEntity;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity,Integer> {
    UserEntity getByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
