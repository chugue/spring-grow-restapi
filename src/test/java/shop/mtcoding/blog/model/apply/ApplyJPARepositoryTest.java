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
    public void qq_test(){
        //given
        Integer userId  = 1;
        //when
        List<Apply> applies = applyJPARepo.findAllUserByApply(userId);
        //then
        System.out.println(applies);
        System.out.println(applies.size());
    }

}
