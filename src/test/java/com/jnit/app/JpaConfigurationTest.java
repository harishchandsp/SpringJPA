package com.jnit.app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.jnit.app.model.User;

public class JpaConfigurationTest extends ParentTest{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void testIntegration(){
		System.out.println("Everything good");
	}
	
	@Test
	@Transactional
	public void testHibernateSession(){
		Session session = entityManager.unwrap(Session.class);
		User user = new User("bhiapp@gmail.com", "Appi", "B", "R", "bhiapp4", LocalDate.of(1984, 9, 11), null);
		session.save(user);
		//session.flush();
		session.getTransaction().commit();
		//User user1 = session.get(User.class, 1L);
		//System.out.println(user1.getfName());
		
	}
}
