package com.skilldistillery.doggiemeetup.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DogParkReviewTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DogParkReview dogParkReview;

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
		dogParkReview = em.find(DogParkReview.class, new DogParkReviewId(1,1));
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dogParkReview = null;
	}

	@Test
	@DisplayName("test dogParkReview entity")
	void test() {
		assertNotNull(dogParkReview);
		assertEquals(5, dogParkReview.getRating());
	}
	
	@Test
	@DisplayName("test dog park review map to user")
	void test2() {
		assertNotNull(dogParkReview);
		assertNotNull(dogParkReview.getUser());
		assertEquals("admin", dogParkReview.getUser().getUsername());
	}

}
