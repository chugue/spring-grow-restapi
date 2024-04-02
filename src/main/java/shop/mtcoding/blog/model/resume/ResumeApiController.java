package shop.mtcoding.blog.model.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;

    @PostMapping("/resume/save")
    public ResponseEntity<?> save(@RequestBody ResumeRequest.SaveDTO reqDTO){
        resumeService.save(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    @GetMapping("/resume/{resumeId}/update-form")
    public ResponseEntity<?> updateFrom(@PathVariable Integer resumeId){

        ResumeResponse.UpdateDTO respDTO = resumeService.updateForm(resumeId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/resume/{resumeId}/update")
    public ResponseEntity<?> update(@PathVariable Integer resumeId,@RequestBody ResumeResponse.UpdateDTO reqDTO) {

        return null;
    }
}
