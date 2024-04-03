package shop.mtcoding.blog.model.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;

@Import(ApplyService.class)
@DataJpaTest
public class ApplyServiceTest {
    @Autowired
    private ApplyService applyService;

    @Test
    public void cancel_test(){
        // given
        Integer jobsId = 1;
        Integer resumeId=1;
        // when
        applyService.cancel(jobsId, resumeId);
        // then
    }

//    @Test
//    public void newApply_test(){
//        // given
//        int jobsId = 1;
//        int resumeId = 1;
//        // when
//        applyService.newApply(jobsId, resumeId);
//        // then
//
//    }
}
