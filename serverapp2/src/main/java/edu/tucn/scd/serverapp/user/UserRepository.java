package edu.tucn.scd.serverapp.user;

import edu.tucn.scd.serverapp.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    @Query(nativeQuery = true, value = "SELECT id FROM user WHERE username=?1")
    Integer findIdByUsername(String username);
}
