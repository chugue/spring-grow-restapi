package shop.mtcoding.blog.model.skill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class SkillJPARepositoryTest {

    @Autowired
    private SkillJPARepository skillJPARepo;

    @Test
    public void findByJobsId_test() {
        int jobsId = 1;

        List<Skill> skills = skillJPARepo.findByJobsId(jobsId);

        System.out.println(skills.size());
    }

    @Test
    public void findByResumeId_test(){
        int resumeId = 1;

        List<Skill> skills = skillJPARepo.findByResumeId(resumeId);

        System.out.println(skills.size());
    }

    @Test
    public void findAllByJoinResumeId_test(){
        int resumeId = 1;

        List<Skill> skills = skillJPARepo.findAllByJoinResumeId(resumeId);

        System.out.println(skills.get(0).getResume().getSkillList().get(0).getName());
    }

    @Test
    public void deleteByJobsId_test(){
        int jobsId = 1;

        skillJPARepo.deleteByJobsId(jobsId);
    }
}
