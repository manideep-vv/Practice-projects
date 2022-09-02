package com.tcs.jpaandhibernatein10steps.dao;

import com.tcs.jpaandhibernatein10steps.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprDataJpaRepo extends JpaRepository<Course,Long> {
}
