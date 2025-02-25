package co.istad.backend.features.user;

import co.istad.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.uuid = :uuid AND u.isDeleted = false")
    User findUserByUuid(String uuid);

    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findUsersByEmail(String email);



}
