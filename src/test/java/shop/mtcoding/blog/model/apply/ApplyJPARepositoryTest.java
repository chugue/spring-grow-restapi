package shop.mtcoding.blog.model.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.plaf.SpinnerUI;
import java.util.List;

@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepo;

    @Test
    public void q_test(){
        // given
        int resumeId = 1;
        // when
        List<Apply> applyList = applyJPARepo.findAppliesByNot1ByResumeId(resumeId);
        // then

        System.out.println(applyList.size());

    }

    @Test
    public void ww_test(){
        //given
        Integer resumeId = 1;
        Integer jobsId = 2;
        //when
        Apply apply = applyJPARepo.findApplyByResumeId(resumeId,jobsId);
        //then
        System.out.println("결과값*-***/**/*/*/*/*/*/*/*/*/*/*/"+apply);
    }
}
