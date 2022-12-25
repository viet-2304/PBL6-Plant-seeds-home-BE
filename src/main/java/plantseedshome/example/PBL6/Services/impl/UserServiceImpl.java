package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.DAO.repository.RoleRepository;
import plantseedshome.example.PBL6.DAO.repository.UserRepository;
import plantseedshome.example.PBL6.Services.UserService;
import plantseedshome.example.PBL6.dto.UserDto;
import plantseedshome.example.PBL6.dto.UserRegisterDto;
import plantseedshome.example.PBL6.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<UserDto> getAllUser() {
     return   userRepository.findAll().stream().map(user -> userMapper.userToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public String createUser(UserRegisterDto userRegisterDto) {
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isEmpty()){
            userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
            userRegisterDto.setRoleId("1");
            User user = userMapper.userRegisterToUser(userRegisterDto);
            userRepository.save(user);
            return "success";
        } else {
            return "error";
        }

    }

    @Override
    public UserDto getCurrentUser(String email) {
       return userMapper.userToUserDto(userRepository.findByEmail(email).get());
    }

    @Override
    public UserDto editCurrentUser(UserDto userDto) {
        if(userRepository.findById(userDto.getId()).isPresent()) {
            User user = userRepository.findById(userDto.getId()).get();
                user.setUserName(userDto.getUserName());
                user.setAddress(userDto.getAddress());
                user.setEmail(userDto.getEmail());
                user.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(user);
            return userMapper.userToUserDto(user);
        }
        return null;
    }

    @Override
    public void updateUserRole(String userId,String role) {
        if(userRepository.findById(userId).isPresent()) {
            User user =userRepository.findById(userId).get();
            user.setRoles(roleRepository.getReferenceById(role));
            userRepository.save(user);
        }
    }
}
