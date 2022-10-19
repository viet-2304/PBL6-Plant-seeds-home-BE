package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.UserDto;

import java.util.List;

public interface CustomerService {
    void createUser (UserDto userDto);

    List<UserDto> getAllUsers();
}
