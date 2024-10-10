package LoginPage.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String createToken(String prefix, String Secretkey, JWTObject jwtObject){

        Key key = Keys.hmacShaKeyFor(Secretkey.getBytes());

        String token = Jwts.builder().setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAt()).
                setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES,checkRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key).compact();

        return prefix + " " + token;
    }

    public static JWTObject ExtractInfoToken (String token , String prefix,String Secretkey) throws ExpiredJwtException, UnsupportedJwtException
            , MalformedJwtException, SignatureException {

        Key key = Keys.hmacShaKeyFor(Secretkey.getBytes());

        JWTObject object = new JWTObject();
        token = token.replace(prefix, "");
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(token)
                .getBody();


        object.setSubject(claims.getSubject());
        object.setExpiration(claims.getExpiration());
        object.setIssuedAt(claims.getIssuedAt());
        object.setRoles((List) claims.get(ROLES_AUTHORITIES));
        return object;

    }



    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
    }

}
