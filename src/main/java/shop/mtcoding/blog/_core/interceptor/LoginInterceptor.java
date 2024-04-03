package shop.mtcoding.blog._core.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception500;
import shop.mtcoding.blog._core.util.JWTUtil;
import shop.mtcoding.blog.model.user.SessionUser;
import shop.mtcoding.blog.model.user.User;

// /api/** 인증 필요한 주소 설정
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //Bearer jwt토큰

        String jwt = request.getHeader("Authorization");

        if (jwt == null){
            throw new Exception401("jwt 토큰이 비어있습니다.");
        }

        jwt = jwt.replace("Bearer ","");

        //검증

        try{
            SessionUser sessionUser = JWTUtil.verify(jwt);

            //임시 세션 (jsessionId는 필요하지 않음)
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser",sessionUser);
            return true;
        }catch (TokenExpiredException e){
            throw new Exception401("토큰 만료시간이 지났습니다 다시 로그인해주세요");
        }catch (JWTDecodeException e){
            throw new Exception401("토큰이 유효하지않습니다");
        }catch (Exception e){
            throw new Exception500(e.getMessage());
        }

    }
}
