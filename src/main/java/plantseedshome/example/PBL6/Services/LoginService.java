package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.LoginRequestDto;
import plantseedshome.example.PBL6.dto.LoginResponseDto;
import plantseedshome.example.PBL6.dto.UserDto;

import java.util.List;

public interface LoginService {
//LoginResponseDto authenticate(LoginRequestDto loginRequestDto);

    LoginResponseDto authenticate(LoginRequestDto loginRequestDto);
}
