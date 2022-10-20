package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.UserDto;
import plantseedshome.example.PBL6.dto.UserRegisterDto;

import java.util.List;

public interface UserService {
    void createUser(UserRegisterDto userRegisterDto);
    List<UserDto> getAllUser();
}
