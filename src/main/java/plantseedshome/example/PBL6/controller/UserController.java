package plantseedshome.example.PBL6.controller;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.DAO.repository.UserRepository;
import plantseedshome.example.PBL6.Security.CustomerUserDetails;
import plantseedshome.example.PBL6.Services.LoginService;
import plantseedshome.example.PBL6.Services.UserService;
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

    CustomerUserDetails customerUserDetails;
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserRegisterDto userRegisterDto) {
       String res =  userService.createUser(userRegisterDto);
       if(res == "error") {
           return new ResponseEntity<>("Email is exist", HttpStatus.EXPECTATION_FAILED);
       }
        return new ResponseEntity<>("Create success", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUser() {
        List<UserDto> tempUsers = userService.getAllUser();
        return new ResponseEntity<>(tempUsers, HttpStatus.OK);    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
      return  new ResponseEntity<>(userService.getCurrentUser(authentication.getName()), HttpStatus.OK);
    }

    @PostMapping("/editUser")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto) {
        UserDto response = userService.editCurrentUser(userDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/unActiveUser")
    public ResponseEntity<UserDto> unActiveUser(@RequestParam String userId, @RequestParam boolean isActive) {
           UserDto userDto =  userService.changeActive(userId, isActive);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
}
