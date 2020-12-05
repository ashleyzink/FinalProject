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

class DogParkCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DogParkComment dogParkComment;
	private DogParkComment dogParkComment2;

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
		dogParkComment2 = em.find(DogParkComment.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dogParkComment = null;
		dogParkComment2 = null;
	}

	@Test
	@DisplayName("test dogParkComment entity")
	void test() {
		assertNotNull(dogParkComment);
		assertEquals("Big Field", dogParkComment.getTitle());
	}
	
	@Test
	@DisplayName("test dogParkComment mapping to user")
	void test2() {
		assertNotNull(dogParkComment);
		assertNotNull(dogParkComment.getUser());
		assertEquals("admin", dogParkComment.getUser().getUsername());
	}
	
	@Test
	@DisplayName("test dogParkComment mapping to parent comment")
	void test3() {
		assertNotNull(dogParkComment2);
		assertNotNull(dogParkComment2.getReplyToComment());
		assertEquals("Big Field", dogParkComment2.getReplyToComment().getTitle());
	}
	
	@Test
	@DisplayName("test dogParkComment mapping to replies")
	void test4() {
		assertNotNull(dogParkComment);
		assertNotNull(dogParkComment.getReplies());
		assertTrue(dogParkComment.getReplies().size() > 0);
		assertEquals("this is a great coversation", dogParkComment.getReplyToComment().getTitle());
	}

}
