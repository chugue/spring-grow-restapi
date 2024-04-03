package shop.mtcoding.blog._core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.mtcoding.blog.model.user.SessionUser;
import shop.mtcoding.blog.model.user.User;

import java.util.Date;

public class JWTUtil {

    //생성
    public static String create(User user){

        String jwt = JWT.create()
                .withSubject("grow")
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*60))
                .withClaim("id",user.getId())
                .withClaim("email",user.getEmail())
                .sign(Algorithm.HMAC512("grow-no1"));

        return jwt;
    }

    //검증
    public static SessionUser verify(String jwt){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("grow-no1")).build().verify(jwt);
        Integer id = decodedJWT.getClaim("id").asInt();
        String email = decodedJWT.getClaim("email").asString();

        //임시 session 이용
        return SessionUser.builder()
                .id(id)
                .email(email)
                .build();
    }

}

