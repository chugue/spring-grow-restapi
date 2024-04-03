package shop.mtcoding.blog._core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.model.user.SessionUser;
import shop.mtcoding.blog.model.user.User;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");

//        User sessionUser = (User) session.getAttribute("sessionUser");
//        User sessionComp = (User) session.getAttribute("sessionComp");
        // Bearer jew 토큰 으로 들어오는 것이 프로토콜이다.
        String jwt = request.getHeader("Authorization");
        jwt = jwt.replace("Bearer ", "");

        // 검증
        try {
            SessionUser sessionUser = shop.mtcoding.blog._core.utils.JwtUtil.verify(jwt);
            System.out.println("token : " + 111111);

            if (sessionUser.getRole() == 1){
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", sessionUser);
            }
            if (sessionUser.getRole() == 2){
                HttpSession session = request.getSession();
                session.setAttribute("sessionComp", sessionUser);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
