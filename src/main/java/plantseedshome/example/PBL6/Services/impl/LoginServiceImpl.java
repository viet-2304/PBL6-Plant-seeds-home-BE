package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.DAO.repository.UserRepository;
import plantseedshome.example.PBL6.Security.CustomAuthenticationManager;
import plantseedshome.example.PBL6.Services.CustomerUserDetailsService;
import plantseedshome.example.PBL6.Services.LoginService;
import plantseedshome.example.PBL6.common.utils.JwtUtil;
import plantseedshome.example.PBL6.dto.LoginRequestDto;
import plantseedshome.example.PBL6.dto.LoginResponseDto;
import plantseedshome.example.PBL6.dto.UserDto;
import plantseedshome.example.PBL6.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final CustomAuthenticationManager customAuthenticationManager;


    private final CustomerUserDetailsService customerUserDetailsService;
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto) {
    try {
        customAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail().trim(),
                        loginRequestDto.getPassword())
        );
    } catch (BadCredentialsException e) {
        throw new BadCredentialsException("Incorrect email or password.", e);
    }

    final UserDetails userDetails = customerUserDetailsService.loadUserByUsername(loginRequestDto.getEmail().trim());
    final String token = jwtUtil.generateToken(userDetails);

        final User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Incorrect email or password."));
        UserDto userDto = userMapper.userToUserDto(user);
        return  new LoginResponseDto(token, userDto);
    }

}