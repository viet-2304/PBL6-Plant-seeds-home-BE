package plantseedshome.example.PBL6.controller;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.DAO.repository.UserRepository;
import plantseedshome.example.PBL6.Services.LoginService;
import plantseedshome.example.PBL6.Services.UserService;
import plantseedshome.example.PBL6.Services.impl.LoginServiceImpl;
import plantseedshome.example.PBL6.dto.LoginRequestDto;
import plantseedshome.example.PBL6.dto.UserDto;
import plantseedshome.example.PBL6.dto.UserRegisterDto;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping( value="api/v1/users")
public class UserController {
//    @Autowired
//    private final ModelMapper mapper;
    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserRegisterDto userRegisterDto) {
       String res =  userService.createUser(userRegisterDto);
       if(res == "error") {
           return new ResponseEntity<>("Email is exist", HttpStatus.EXPECTATION_FAILED);
       }
        return new ResponseEntity<>("Create success", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<UserDto> getUser() {
        List<UserDto> tempUsers = userService.getAllUser();
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
}
