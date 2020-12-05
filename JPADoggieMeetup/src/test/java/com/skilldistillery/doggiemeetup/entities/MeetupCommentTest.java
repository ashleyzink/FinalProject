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

class MeetupCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private MeetupComment meetupComment;

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
		meetupComment = em.find(MeetupComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		meetupComment = null;
	}

	@Test
	@DisplayName("test meetupComment entity")
	void test() {
		assertNotNull(meetupComment);
		assertEquals("My Dog has fleas, Help?", meetupComment.getTitle());
	}

}
