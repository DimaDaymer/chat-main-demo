package zonix.chat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import zonix.chat.dto.UserDto;
import zonix.chat.entity.Role;
import zonix.chat.entity.User;
import zonix.chat.repository.RoleRepository;
import zonix.chat.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveUser() {
        UserDto userDto = new UserDto(1000L, "Dima", "ml@gb.com", "123123");

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(role);

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn(encodedPassword);

        userService.saveUser(userDto);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isNotNull();
        assertThat(capturedUser.getName()).isEqualTo(userDto.getName());
        assertThat(capturedUser.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(capturedUser.getPassword()).isEqualTo(encodedPassword);
        assertThat(capturedUser.getRoles()).hasSize(1);

        verify(roleRepository).findByName("ROLE_ADMIN");
    }

    @Test
    void findUserByEmail() {
        String userEmail = "ml@gb.com";
        User expectedUser = new User();
        expectedUser.setName("Dima");
        expectedUser.setEmail(userEmail);

        when(userRepository.findByEmail(userEmail)).thenReturn(expectedUser);

        User result = userService.findUserByEmail(userEmail);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(userEmail);

        verify(userRepository).findByEmail(userEmail);
    }
}
