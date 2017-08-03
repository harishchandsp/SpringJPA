package com.jnit.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnit.app.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long>{

	public Optional<Student> findByUserName(String userName);
	
	public List<Student>findByLName(String lastName);

	public Long countByLName(String lastName);

	public Optional<Student>findByFNameAndLName(String FirstName, String lastName);

	public List<Student>findByFNameOrLName(String FirstName, String lastName);
	
	public List<Student>findDistinctByMName(String middleName);

	public List<Student> findFirst3ByLNameOrderByUserNameAsc(String lastName);
}
