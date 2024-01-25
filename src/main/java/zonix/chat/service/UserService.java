package zonix.chat.service;

import org.springframework.stereotype.Service;
import zonix.chat.dto.UserDto;
import zonix.chat.entity.User;

import java.util.List;

@Service
public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
