package com.jnit.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jnit.app.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	public List<Course>findByNameContaining(String name);
	
	public List<Course>findByNameStartingWith(String name);
	
	@Query(value="select * from course", nativeQuery=true)
	public List<Course>findAllCoursesNativeQuery();
	
}
