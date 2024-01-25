package zonix.chat.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zonix.chat.entity.Role;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository underTest;

    @Test
    void ifFindByNameExistWorks() {
        Role role = new Role();
        role.setId(1000L);
        role.setName("ROLE_USER");
        role.setUsers(new ArrayList<>());

        underTest.save(role);

        Role exists = underTest.findByName("ROLE_USER");

        assertThat(exists).isNotNull();
        assertThat(exists.getId()).isEqualTo(role.getId());
        assertThat(exists.getName()).isEqualTo(role.getName());
    }

    @Test
    void ifFindByNameDontExistWorks() {
        String name = "ROLE_MASCARPONE";
        Role exists = underTest.findByName(name);

        assertThat(exists).isNull();
    }
}