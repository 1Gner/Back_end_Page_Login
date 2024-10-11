package LoginPage.Controller;


import LoginPage.Entities.Login;
import LoginPage.Entities.Sessao;
import LoginPage.Entities.User;
import LoginPage.Repository.UserRepository;
import LoginPage.Security.JWTCreator;
import LoginPage.Security.JWTObject;
import LoginPage.Security.SecurityConfigJwt;
import LoginPage.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.List;


@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SecurityConfigJwt securityConfig;



    @PostMapping(value = "/save")
    public ResponseEntity<User> salvaUser(@RequestBody User user){
        user = service.salvarUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> procuraUser(@PathVariable Integer id){
        User user = service.findById(id);
        user = service.dencryptUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> procuraTodos(){
        List<User> user = service.findAll();
        user.forEach(use -> use = service.dencryptUser(use));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> Login (@RequestBody Login login, HttpServletResponse response){

        User user = repository.findByUsername(login.getUsername());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            Sessao sessao = new Sessao();
            sessao.setUsername(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setSubject(user.getUsername());
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + securityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());

            sessao.setToken(JWTCreator.createToken(securityConfig.PREFIX,securityConfig.KEY,jwtObject));


            String encodedToken  = Base64.getUrlEncoder().encodeToString(sessao.getToken().getBytes());

            Cookie jwtCookie = new Cookie("jwtToken", encodedToken);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(5 * 60 * 60);

            response.addCookie(jwtCookie);

            return ResponseEntity.ok().body(sessao);
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}



