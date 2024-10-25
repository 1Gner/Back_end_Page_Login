package LoginPage.Controller;


import LoginPage.Entities.Order;
import LoginPage.Entities.User;
import LoginPage.Service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@Controller
@RestController
@RequestMapping(value ="/order")
public class OrderControler {


    @Autowired
    private OrderService service;

    @PostMapping(value = "/receive")
    public ResponseEntity<?> ReceberOrder(@RequestBody List<Order> orders, HttpServletRequest request){
       User user =  service.getUser(request);
       if(user != null){
           orders.forEach(order ->{
               order.setUser(user);
               service.salvarOrder(order);
           });
           return ResponseEntity.ok().body(orders);
       }
       return ResponseEntity.status(500).body("Erro");


    }
}
