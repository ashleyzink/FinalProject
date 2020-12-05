package com.skilldistillery.doggiemeetup.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DogTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Dog dog;

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
		dog = em.find(Dog.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dog = null;
	}

	@Test
	@DisplayName("test dog entity")
	void test() {
		assertNotNull(dog);
		assertEquals("Kona", dog.getName());
	}
	
	@Test
	@DisplayName("test dog mapping to user")
	void test2() {
		assertNotNull(dog);
		assertNotNull(dog.getUser());
		assertEquals("admin", dog.getUser().getUsername());
	}
	
	@Test
	@DisplayName("test dog mapping to dog reviews")
	void test3() {
		assertNotNull(dog);
		assertNotNull(dog.getDogReviews());
		assertTrue(dog.getDogReviews().size() > 0);
		assertEquals(5, dog.getDogReviews().get(0).getRating());
	}
	
	@Test
	@DisplayName("test dog mapping to meetup")
	void test4() {
		assertNotNull(dog);
		assertNotNull(dog.getMeetups());
		assertTrue(dog.getMeetups().size() > 0);
		assertEquals("dog and chill", dog.getMeetups().get(0).getTitle());
	}
	
	

}
