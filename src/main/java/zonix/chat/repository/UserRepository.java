package zonix.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.testng.annotations.Test;
import zonix.chat.entity.User;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
    User findByName(String username);

    boolean existsUserByEmail(String email);



}
