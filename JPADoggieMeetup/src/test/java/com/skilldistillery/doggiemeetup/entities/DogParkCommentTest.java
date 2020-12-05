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

class DogParkCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DogParkComment dogParkComment;

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
		dogParkComment = em.find(DogParkComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dogParkComment = null;
	}

	@Test
	@DisplayName("test dogParkComment entity")
	void test() {
		assertNotNull(dogParkComment);
		assertEquals("Big Field", dogParkComment.getTitle());
	}

}
