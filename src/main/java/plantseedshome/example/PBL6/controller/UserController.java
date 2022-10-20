package plantseedshome.example.PBL6.controller;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.DAO.repository.UserRepository;
import plantseedshome.example.PBL6.Services.LoginService;
import plantseedshome.example.PBL6.Services.impl.LoginServiceImpl;
import plantseedshome.example.PBL6.dto.LoginRequestDto;
import plantseedshome.example.PBL6.dto.UserDto;

import java.util.*;

@RestController
@RequestMapping( value="api/v1/users")
public class UserController {
//    @Autowired
//    private final ModelMapper mapper;
    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;

//    public UserController(ModelMapper mapper) {
//        this.mapper = mapper;
//    }

    @GetMapping("/getAll")
    public List<User> getUser() {
        List<User> tempUsers = userRepository.findAll();
//        List<UserDto> tempUsersDto = new List<UserDto>() ;
//        for (User temp:tempUsers
//             ) {
//            UserDto tempDto = null;
//            tempDto.setId(tempDto.getId());
//            tempDto.setUserName(temp.getUserName());
//            tempDto.setRoleName(temp.getRoles().getRoleName());
//            tempDto.setAddress(temp.getAddress());
//            tempDto.setEmail(temp.getEmail());
//            tempDto.setImageAvatar("temp.getImageAvatar().getAvatarUrl()");
//            tempDto.setPhoneNumber(temp.getPhoneNumber());
//            tempUsersDto.add(tempDto);
//        }
        return tempUsers;    }

//    @GetMapping
//    public UserDto getUserByName(@RequestBody LoginRequestDto loginRequestDto) {
//        return loginService.Login(loginRequestDto);
////        User user =  userRepository.findById("1").get();
////        UserDto tempDto = new UserDto();
////        tempDto.setId(user.getId());
////        tempDto.setUserName(user.getUserName());
////        tempDto.setRoleName(user.getRoles().getRoleName());
////        tempDto.setAddress(user.getAddress());
////        tempDto.setEmail(user.getEmail());
////        tempDto.setPhoneNumber(user.getPhoneNumber());
//
////        tempDto.setImageAvatar("temp.getImageAvatar().getAvatarUrl()");
//
////        UserDto userDto = mapper.map(user, UserDto.class);
////        return tempDto;
//    }
}
