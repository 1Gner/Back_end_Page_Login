/*package LoginPage.Controller;


import LoginPage.Entities.Login;
import LoginPage.Entities.Sessao;
import LoginPage.Entities.User;
import LoginPage.Repository.UserRepository;
import LoginPage.Security.JWTCreator;
import LoginPage.Security.JWTObject;
import LoginPage.Security.SecurityConfigJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/entry")
public class LoginController {

    @Autowired
    private SecurityConfigJwt securityConfig;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @PostMapping(value = "/login")
    public ResponseEntity<Sessao> logar(@RequestBody Login login) {
        User user = repository.findByUsername(login.getUsername());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            Sessao sessao = new Sessao();
            sessao.setUsername(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + securityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());


            sessao.setToken(JWTCreator.createToken(securityConfig.PREFIX,securityConfig.KEY,jwtObject));
            return ResponseEntity.ok().body(sessao);
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }

}
*/