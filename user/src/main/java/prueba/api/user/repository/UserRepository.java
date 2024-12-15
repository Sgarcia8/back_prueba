package prueba.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.api.user.entity.UserP;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserP, Long> {
    Optional<UserP> findByEmail(String email);
}
