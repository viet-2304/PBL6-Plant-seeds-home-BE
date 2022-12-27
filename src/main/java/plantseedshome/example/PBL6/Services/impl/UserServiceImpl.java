package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.ImageAvatar;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.DAO.repository.ImagesAvatarRepository;
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

    @Autowired
    ImagesAvatarRepository imagesAvatarRepository;


    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtos = userRepository.findAll().stream().map(user -> userMapper.userToUserDto(user)).collect(Collectors.toList());
        userDtos.forEach(userDto -> {
            userDto.setImageAvatar(imagesAvatarRepository.getImageAvatarByUserId(userDto.getId()));
        });
        return userDtos;
    }

    @Override
    public String createUser(UserRegisterDto userRegisterDto) {
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isEmpty()){
            userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
            userRegisterDto.setRoleId("1");
            User user = userMapper.userRegisterToUser(userRegisterDto);
            user.setActive(true);
            userRepository.save(user);
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public UserDto getCurrentUser(String email) {
       UserDto userDto = userMapper.userToUserDto(userRepository.findByEmail(email).get());

           String imageAvatar = imagesAvatarRepository.getImageAvatarByUserId(userDto.getId());
           if (imageAvatar != null) {
               userDto.setImageAvatar(imageAvatar);
           }

       return userDto;
    }

    @Override
    public UserDto editCurrentUser(UserDto userDto) {
        if(userRepository.findById(userDto.getId()).isPresent()) {
            User user = userRepository.findById(userDto.getId()).get();
                user.setUserName(userDto.getUserName());
                user.setAddress(userDto.getAddress());
                user.setEmail(userDto.getEmail());
                user.setPhoneNumber(userDto.getPhoneNumber());
                user.setActive(user.isActive());
            userRepository.save(user);
            updateImageAvatar(userDto.getId(), userDto.getImageAvatar());
            UserDto userDto1 = userMapper.userToUserDto(user);
            userDto1.setImageAvatar(userDto.getImageAvatar());
            return userDto1;
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

    @Override
    public UserDto changeActive(String userId, boolean isActive) {
        userRepository.changeActiveUser(userId, isActive);
        return userMapper.userToUserDto(userRepository.findById(userId).get());
    }

    @Override
    public UserDto getUserById(String userId) {
        UserDto userDto = userMapper.userToUserDto(userRepository.findById(userId).get());
        try {
            String imageAvatar = imagesAvatarRepository.getImageAvatarByUserId(userId);
            userDto.setImageAvatar(imageAvatar);
        } catch (NullPointerException e) {}
        return userDto;
    }

    private void updateImageAvatar(String userId, String imageAvatar) {
        try{
            if (imagesAvatarRepository.getImageAvatarByUserId(userId) == null) {
                imagesAvatarRepository.save(new ImageAvatar("", imageAvatar, userRepository.findById(userId).get(), null));
            }else {
                imagesAvatarRepository.updateAvatarImage(userId, imageAvatar);
            }
        } catch (NullPointerException e){

        }

    }
}
