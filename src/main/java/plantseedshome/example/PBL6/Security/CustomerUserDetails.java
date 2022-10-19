package plantseedshome.example.PBL6.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import plantseedshome.example.PBL6.DAO.entity.User;

import java.util.Collection;
import java.util.List;

public class CustomerUserDetails implements UserDetails {

    private final String username;

    private final String password;
    private final List<GrantedAuthority> authorityList;

    private final User user;

    public CustomerUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorityList = List.of(new SimpleGrantedAuthority(user.getRoles().getRoleName().toUpperCase()));
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
