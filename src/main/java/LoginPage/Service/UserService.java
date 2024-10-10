package LoginPage.Service;

import LoginPage.Entities.User;
import LoginPage.Repository.UserRepository;
import LoginPage.Security.Aes;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    private Aes AesEncoder = new Aes();


    public User salvarUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEmail(AesEncoder.encrypt(user.getEmail()));
        user.setNome(AesEncoder.encrypt((user.getNome())));
        return repository.save(user);
    }

    public User findById(Integer id) {
        return repository.findById(id).orElse(null);

    }
    public User dencryptUser(User user){
        user.setNome(AesEncoder.decrypt(user.getNome()));
        user.setEmail(AesEncoder.decrypt(user.getEmail()));
        return user;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findLogin(String Username, String Password){
        List<User> users = findAll();

        Optional<User> userR = users.stream().filter(user -> user.getUsername().equals(Username)
                && encoder.matches(Password,user.getPassword())).findFirst();

        return userR.orElse(null);
    }
}
