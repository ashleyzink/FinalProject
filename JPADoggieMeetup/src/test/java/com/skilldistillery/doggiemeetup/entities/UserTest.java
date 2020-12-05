package com.skilldistillery.doggiemeetup.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("DogMeetUpPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("User entity test")
	void test1() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	@Test
	@DisplayName("One to One User to Address Test")
	void test2() {
		assertNotNull(user);
		Address address = user.getAddress();
		assertNotNull(address);
		assertEquals(1, address.getId());
		assertEquals("1234 dog st.", address.getStreet());
		assertEquals("dogwood", address.getCity());
		assertEquals("CA", address.getStateAbbrv());
		assertEquals(12345, address.getZipcode());
	}

}
