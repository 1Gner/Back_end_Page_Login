package LoginPage.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class JWTFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //String token =  request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
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
            if(token!=null && !token.isEmpty()) {

                JWTObject tokenObject = JWTCreator.ExtractInfoToken(token, SecurityConfigJwt.PREFIX, SecurityConfigJwt.KEY);

                List<SimpleGrantedAuthority> authorities = authorities(tokenObject.getRoles());

                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(
                                tokenObject.getSubject(),
                                null,
                                authorities);

                SecurityContextHolder.getContext().setAuthentication(userToken);

            }else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            e.printStackTrace();


            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("jwtToken".equals(cookie.getName())) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
            response.setStatus(HttpStatus.FORBIDDEN.value());

            return;
        }
    }
    private List<SimpleGrantedAuthority> authorities(List<String> roles){
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
