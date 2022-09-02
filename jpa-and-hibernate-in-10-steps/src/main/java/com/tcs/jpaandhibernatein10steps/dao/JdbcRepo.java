package com.tcs.jpaandhibernatein10steps.dao;

import com.tcs.jpaandhibernatein10steps.pojo.Course;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRepo {

    JdbcTemplate template;
    public JdbcRepo (JdbcTemplate template){
        System.out.println("executing constructor****,injected JDBC template");
        this.template=template;
    }

    String INSERT_QUERY= "       insert into course (id, course_Name, author) values(?,?,?)   ";
    String DELETE_QUERY= "       DELETE FROM course where id= ?";
    String SELECT_QUERY="select * FROM course where author =? ";
    String SELECT_QUERY_WITHID="select * FROM course where id = ? ";

    public void save(Course c){
        int update = template.update(INSERT_QUERY, c.getId(), c.getCourseName(), c.getAuthor());
        System.out.println(update+" rows updated");
    }
    public void deleteById(Long id){
        int update = template.update(DELETE_QUERY, id);
        System.out.println(update+" rows deleted");
    }


    public void findByAuthor(String cname){


        List<Course> list = template.query(SELECT_QUERY, new StudentExtractor(), cname);
        list.forEach(System.out::println);
        //        System.out.println("retrieved the data list size is "+stream.size());
    }
    public void findById(Long id){
        Object o = template.queryForObject(SELECT_QUERY_WITHID, new BeanPropertyRowMapper(Course.class), id);
        System.out.println("retrieved the data list size is "+o);
    }
}
class StudentExtractor implements ResultSetExtractor<List<Course>>{

    @Override
    public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Course> list=new ArrayList<>();
        while(rs.next()){
            list.add(new Course(rs.getLong(1),rs.getString(2),rs.getString(3)));
        }

        return list;
    }
}