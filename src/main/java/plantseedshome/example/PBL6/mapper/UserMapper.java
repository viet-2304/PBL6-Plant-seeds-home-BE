package plantseedshome.example.PBL6.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.dto.UserDto;

public interface UserMapper {

    @Mapping(source = "roleName", target = "roles.roleName")
    User userDtoToUser(UserDto userDto);

    @InheritInverseConfiguration(name = "userDtoToUser")
    UserDto userToUserDto(User user);
}
