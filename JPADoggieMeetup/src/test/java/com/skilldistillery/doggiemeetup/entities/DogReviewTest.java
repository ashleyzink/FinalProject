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

class DogReviewTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DogReview dogReview;

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
		dogReview = em.find(DogReview.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dogReview = null;
	}

	@DisplayName("testing entity mapping for dog review")
	@Test
	void location() {
//		assertNotNull(dogReview);
//		assertEquals("5", dogReview.getRating());
//		assertEquals("Nam semper maximus elit id porta. Phasellus eu velit purus.", dogReview.getReview());

	}

}
