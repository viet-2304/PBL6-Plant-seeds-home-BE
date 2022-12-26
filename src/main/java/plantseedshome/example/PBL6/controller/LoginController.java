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
    public  ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = loginService.authenticate(loginRequestDto);
        if (loginResponseDto.getUserDto() ==  null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(loginResponseDto.getToken() == "unActive") {
            return  new ResponseEntity<>(null, HttpStatus.LOCKED);
        }

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}