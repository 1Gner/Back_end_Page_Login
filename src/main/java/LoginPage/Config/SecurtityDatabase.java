package LoginPage.Config;

import LoginPage.Entities.User;
import LoginPage.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;

@Service
public class SecurtityDatabase implements UserDetailsService {
    @Autowired
    private UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = UserRepository.findByUsername(username);

        if(userEntity == null){
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> authoritities = new HashSet<GrantedAuthority>();
        userEntity.getRoles().forEach(role -> {
            if (!role.startsWith("ROLE_")) {
                authoritities.add(new SimpleGrantedAuthority("ROLE_" + role));
            } else {
                authoritities.add(new SimpleGrantedAuthority(role));
            }
        });

        UserDetails user1 = new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
                userEntity.getPassword(),
                authoritities);

        return user1;
    }
}
