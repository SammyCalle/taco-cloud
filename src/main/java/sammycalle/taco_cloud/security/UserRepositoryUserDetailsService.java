package sammycalle.taco_cloud.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sammycalle.taco_cloud.security.model.User;
import sammycalle.taco_cloud.security.repository.UserRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
        return user;
        }
        throw new UsernameNotFoundException(
                        "User '" + username + "' not found");
    }
}
