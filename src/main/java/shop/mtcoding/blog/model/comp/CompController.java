package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.user.SessionUser;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompController {
    private final CompService compService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm(HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        request.setAttribute("imgFileName", user.getImgFileName());
        return "/comp/profile-update-form";
    }
}