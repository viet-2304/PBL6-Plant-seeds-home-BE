package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private final  String token;
    private final UserDto userDto;
}
