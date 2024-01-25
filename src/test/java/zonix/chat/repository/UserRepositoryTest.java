package zonix.chat.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zonix.chat.entity.Role;
import zonix.chat.entity.User;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void TearDown(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }



    @Test
    void checkIfFindByEmailExists() {
        String name = "Dmytro";
        String email = "rdima7934@gmail.com";
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword("dmytrorud");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);

        User exists = userRepository.findByEmail(email);

        assertThat(exists).isNotNull();
        assertThat(exists.getId()).isEqualTo(user.getId());
        assertThat(exists.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void checkIfFindByEmailNotExists() {
        String nonExistingName = "NonExistingUser";
        User nonExistingUser = userRepository.findByEmail(nonExistingName);

        assertThat(nonExistingUser).isNull();
    }

    @Test
    void checkIfFindByNameExists() {
        String name = "Dmytro";
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);

        User user = new User();
        user.setName(name);
        user.setEmail("rdima7934@gmail.com");
        user.setPassword("dmytrorud");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);

        User exists = userRepository.findByName(name);

        assertThat(exists).isNotNull();
        assertThat(exists.getId()).isEqualTo(user.getId());
        assertThat(exists.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void checkIfFindByNameNotExists() {
        String nonExistingName = "NonExistingUser";
        User nonExistingUser = userRepository.findByName(nonExistingName);

        assertThat(nonExistingUser).isNull();
    }


}
