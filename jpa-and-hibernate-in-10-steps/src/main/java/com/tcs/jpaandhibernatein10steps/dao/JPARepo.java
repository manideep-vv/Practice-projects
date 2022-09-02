package com.tcs.jpaandhibernatein10steps.dao;

import com.tcs.jpaandhibernatein10steps.pojo.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class JPARepo {
    @PersistenceContext
    EntityManager mgr;

    public void save(Course c){
      Course result= mgr.merge(c);
        System.out.println("1 rows updated");
    }
    public void deleteById(Long id){
        Course course = mgr.find(Course.class, 1L);
        mgr.remove(course);
        System.out.println("1 rows updated");
    }

}
