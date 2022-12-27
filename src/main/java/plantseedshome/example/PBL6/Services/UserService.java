package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.dto.UserDto;
import plantseedshome.example.PBL6.dto.UserRegisterDto;

import java.util.List;

public interface UserService {
    String createUser(UserRegisterDto userRegisterDto);
    List<UserDto> getAllUser();

    UserDto getCurrentUser(String email);

    UserDto editCurrentUser(UserDto userDto);

    UserDto getUserById(String userId);

    void updateUserRole(String userId,String role);

    UserDto changeActive(String userId, boolean isActive);
}
