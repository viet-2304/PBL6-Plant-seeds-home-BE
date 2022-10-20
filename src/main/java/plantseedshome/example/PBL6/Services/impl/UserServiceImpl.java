package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.User;
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


    @Override
    public List<UserDto> getAllUser() {
        return null;
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


}
