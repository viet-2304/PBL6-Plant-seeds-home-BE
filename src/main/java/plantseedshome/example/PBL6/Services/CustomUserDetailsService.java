package plantseedshome.example.PBL6.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import plantseedshome.example.PBL6.DAO.repository.UserRepository;

public interface CustomUserDetailsService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
