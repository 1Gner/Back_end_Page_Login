package LoginPage.Service;


import LoginPage.Entities.Order;
import LoginPage.Entities.Produto;
import LoginPage.Entities.User;
import LoginPage.Repository.OrderRepository;
import LoginPage.Security.JWTCreator;
import LoginPage.Security.JWTObject;
import LoginPage.Security.SecurityConfigJwt;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired UserService userService;


    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order salvarOrder(Order order) {
        return repository.save(order);
    }

    public Order findOrderId(Integer id){
        return repository.findById(id).orElse(null);
    }

    public User getUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {

                    token = new String(Base64.getUrlDecoder().decode(cookie.getValue()));
                    break;
                }
            }
        }
        try {
            if (token != null && !token.isEmpty()) {

                JWTObject tokenObject = JWTCreator.ExtractInfoToken(token, SecurityConfigJwt.PREFIX, SecurityConfigJwt.KEY);

                return userService.findUsername(tokenObject.getSubject());
            }
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            e.printStackTrace();

            return null;
        }
        return null;
    }

}
