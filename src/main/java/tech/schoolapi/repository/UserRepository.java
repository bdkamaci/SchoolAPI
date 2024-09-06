package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
