package plantseedshome.example.PBL6.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.Services.LoginService;
import plantseedshome.example.PBL6.dto.LoginRequestDto;
import plantseedshome.example.PBL6.dto.LoginResponseDto;
import plantseedshome.example.PBL6.dto.UserDto;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDto authenticate(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = loginService.authenticate(loginRequestDto);

        return loginResponseDto;
    }
}
