package shop.mtcoding.blog.model.user;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;

import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.comp.CompResponse;
import shop.mtcoding.blog.model.jobs.Jobs;

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


    // Update 사용자 정보 수정 완료
    @PutMapping("/api/users")
    public ResponseEntity<?> update(@RequestBody UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.UserUpdateDTO updatedUser = userService.updateById(sessionUser, reqDTO);
        session.setAttribute("sessionUser", updatedUser);
        return ResponseEntity.ok(new ApiUtil<>(updatedUser));
    }

    // Update Form에 필요한 사용자 정보 조회 완료
    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> updateForm(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.UserUpdateFormDTO respDTO = userService.updateForm(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //user 회원가입 api
    @PostMapping("/users/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.UserJoinDTO respDTO = userService.join(reqDTO, reqDTO.getRole());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PostMapping("/user/login")
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

    //user의 지원 내역
    @GetMapping("/api/user/{id}/resume-home")
    public ResponseEntity<?> resumeHome(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<UserResponse.UserResumeSkillDTO> respDTO = userService.userResumeSkillDTO(sessionUser.getId());
        //No 카운트 뽑으려고 for문 돌림
        for (int i = 0; i < respDTO.size(); i++) {
            respDTO.get(i).setId(i + 1);
        }

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
