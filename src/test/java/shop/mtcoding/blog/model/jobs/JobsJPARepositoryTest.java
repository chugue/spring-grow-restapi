package shop.mtcoding.blog.model.jobs;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class JobsJPARepositoryTest {

    @Autowired
    private JobsJPARepository jobsJPARepo;

    @Test
    public void findAllByUserId_test() {
        int userId = 15;

        List<Jobs> jobsList = jobsJPARepo.findAllByUserId(userId);

        System.out.println(jobsList.size());
    }
}
