package ua.hehmde.kvjewelry.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.hehmde.kvjewelry.dto.UserDTO;
import ua.hehmde.kvjewelry.entity.User;

import java.util.Optional;

/**
 * Repository to interact with the database for user need
 *
 * @author hehmde
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.login = :login")
    Optional<UserDTO> findByLogin(String login);
}
