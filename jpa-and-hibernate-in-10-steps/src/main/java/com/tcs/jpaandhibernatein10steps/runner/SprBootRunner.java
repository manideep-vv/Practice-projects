package com.tcs.jpaandhibernatein10steps.runner;

import com.tcs.jpaandhibernatein10steps.dao.JPARepo;
import com.tcs.jpaandhibernatein10steps.dao.JdbcRepo;
import com.tcs.jpaandhibernatein10steps.dao.SprDataJpaRepo;
import com.tcs.jpaandhibernatein10steps.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SprBootRunner implements CommandLineRunner {

    @Autowired
    JdbcRepo repo;

    @Autowired
    JPARepo jpaRepo;

    @Autowired
    SprDataJpaRepo sprRepo;

    @Override
    public void run(String... args) throws Exception {
        sprRepo.save(new Course(1L,"1.AWS","in28Minutes"));
        sprRepo.save(new Course(2L,"2.Spr Boot","in28Minutes"));
        sprRepo.save(new Course(3L,"Git","Jason taylor"));

        repo.deleteById(3L);
        repo.findById(1L);
        repo.findByAuthor("in28Minutes");
    }
}
