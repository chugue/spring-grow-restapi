package shop.mtcoding.blog._core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;

import java.util.Date;


public class JwtUtil {
    public static String create(User user){
        String jwt = JWT.create()
                .withSubject("blog")
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*60*24*7))
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole())
                .sign(Algorithm.HMAC512("grow-no1"));
        return jwt;
    }

    public static SessionUser verify(String jwt){

        DecodedJWT decodedJWT =
                JWT.require(Algorithm.HMAC512("grow-no1")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        int role = decodedJWT.getClaim("role").asInt();

        if (role == 1) { // 개인 사용자
            return SessionUser.builder().id(id).role(role).build();
        } else if (role == 2) { // 기업 사용자
            return SessionUser.builder().id(id).role(role).build();
        } else {
            return null;
        }
    }
}