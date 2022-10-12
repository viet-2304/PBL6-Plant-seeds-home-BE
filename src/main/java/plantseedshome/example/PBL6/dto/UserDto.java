package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String email;
    private String userName;
    private String imageAvatar;
    private String roleName;
}
