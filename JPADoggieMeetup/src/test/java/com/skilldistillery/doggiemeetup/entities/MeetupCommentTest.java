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

class MeetupCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private MeetupComment meetupComment;
	private MeetupComment mc2;

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
		mc2 = em.find(MeetupComment.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		meetupComment = null;
		mc2 = null;
	}

	@Test
	@DisplayName("test meetupComment entity")
	void meetUpComment() {
		assertNotNull(meetupComment);
		assertEquals("My Dog has fleas, Help?", meetupComment.getTitle());
	}

	@Test
	@DisplayName("test meetupComment to replies")
	void meetUpCommentToReplies() {
		assertNotNull(mc2);
		assertNotNull(mc2.getReplies());
		assertTrue(mc2.getReplies().size() > 0);
		assertEquals("Trails near by?", mc2.getReplies().get(0).getTitle());
	}

	@Test
	@DisplayName("test meetupComment mapping for replyto")
	void meetUpCommentReplyTo() {
		assertNotNull(mc2);
		assertNotNull(mc2.getReplyToComment());
		assertEquals(1, mc2.getReplyToComment().getId());
	}

}
