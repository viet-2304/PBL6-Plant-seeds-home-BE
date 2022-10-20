package plantseedshome.example.PBL6.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import plantseedshome.example.PBL6.Services.CustomerUserDetailsService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UserDetails userDetails = customerUserDetailsService.loadUserByUsername(authentication.getName());
        return  new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}
