package plantseedshome.example.PBL6.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.dto.UserDto;
import plantseedshome.example.PBL6.dto.UserRegisterDto;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    @Mapping(source = "roleId", target = "roles.roleId")
    User userRegisterToUser(UserRegisterDto userRegisterDto);

    @Mapping(source = "roles.roleId", target = "roleId")
    UserDto userToUserDto(User user);

//    @InheritConfiguration(name = "userDtoToUser")
//    UserDto userToUserDto(User user);

}
