package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.dto.UserDto;
import plantseedshome.example.PBL6.dto.UserRegisterDto;

import java.util.List;

public interface UserService {
    String createUser(UserRegisterDto userRegisterDto);
    List<UserDto> getAllUser();

    UserDto getCurrentUser(String email);

    void editCurrentUser(UserDto userDto);

    void updateUserRole(String userId,String role);
}
