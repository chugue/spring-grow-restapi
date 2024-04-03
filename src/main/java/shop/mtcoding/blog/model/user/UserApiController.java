package shop.mtcoding.blog.model.user;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.jobs.JobsRequest;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.jobs.JobsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;
    private final JobsService jobsService;

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO, HttpSession session) {
        String jwt = userService.login(reqDTO);

        return ResponseEntity.ok().header("Authorization","Bearer " +jwt).body(new ApiUtil<>(null));
    }


    @GetMapping("/")
    public ResponseEntity<?> index() {
        List<JobsResponse.ListDTO> respList = jobsService.listDTOS();
        return ResponseEntity.ok(new ApiUtil<>(respList));
    }


    @PostMapping("/search")
    public ResponseEntity<?> indexKeyword(HttpServletRequest request, @RequestBody JobsRequest.KeywordDTO reqDTO) {

        List<JobsResponse.IndexSearchDTO> respList = jobsService.searchKeyword(reqDTO.getKeyword());
        request.setAttribute("jobsKeyword", respList);
        request.setAttribute("keyword", reqDTO.getKeyword());
        System.out.println("respList size : " + respList.size());

        return ResponseEntity.ok(new ApiUtil<>(respList));
    }


    @GetMapping("/api/users/{id}/home")
    public ResponseEntity<?> userHome (@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.UserHomeDTO respList = userService.userHome(id);

        return ResponseEntity.ok(new ApiUtil<>(respList));
    }

    @GetMapping("/api/users/username-same-check")
    public  ResponseEntity<?> usernameSameCheck(@RequestBody UserRequest.EmailDTO email) {
        User user = userService.findByEmail(email.getEmail());
        if (user == null) {
            return ResponseEntity.ok(new ApiUtil<>(true));
        } else {
            return ResponseEntity.ok(new ApiUtil<>(false));
        }
    }

    @PostMapping("/api/find-jobs-resume")
    public ResponseEntity<?> findAllJobsByResumeId(@RequestBody UserRequest.ResumeIdDTO resumeId, HttpServletRequest request){
        List<UserResponse.UrsDTO> ursDTOList = userService.ursDTOS(resumeId.getResumeId());

        request.setAttribute("ursDTOList", ursDTOList);

        return ResponseEntity.ok(new ApiUtil<>(ursDTOList));
    }
}
