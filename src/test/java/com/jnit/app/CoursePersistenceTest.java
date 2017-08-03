package com.jnit.app;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jnit.app.model.Course;
import com.jnit.app.model.SkillLevel;
import com.jnit.app.model.User;
import com.jnit.app.repositories.CourseRepository;
import com.jnit.app.repositories.UserRepository;

public class CoursePersistenceTest extends ParentTest{
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testCreateCourse(){
		Course course = new Course("Java-I", null, SkillLevel.BEGINNER, 
				"Nice course check it out", null, new BigDecimal(0.0), 
				"Will start programming in java", "IT");
		User user = userRepository.findOne(1L);
		course.setAuthor(user.getUserName());
		courseRepository.save(course);
	}
	
	@Test
	public void testFindCoursesContainingName(){
		List<Course> courses = courseRepository.findByNameContaining("Java");
		assertTrue( "courses list is empty",courses.size() > 0);		
	}

	@Test
	public void testFindCoursesStartingWith(){
		List<Course> courses = courseRepository.findByNameStartingWith("Java");
		assertTrue( "courses list is empty",courses.size() > 0);		
	}
	
	@Test
	public void testFindAllCoursesNativeQuery(){
		List<Course> courses = courseRepository.findAllCoursesNativeQuery();
		assertTrue( "courses list is empty",courses.size() > 0);		
	}

}
