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

class GeneralCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GeneralComment generalComment;
	private GeneralComment generalComment2;

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
		generalComment = em.find(GeneralComment.class, 2);
		generalComment2 = em.find(GeneralComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		generalComment = null;
		generalComment2 = null;
	}

	@Test
	@DisplayName("test generalComment entity")
	void test() {
		assertNotNull(generalComment);
		assertEquals("Dog Food", generalComment.getTitle());
	}
	
	@Test
	@DisplayName("test generalComment mapping to user")
	void test2() {
		assertNotNull(generalComment);
		assertNotNull(generalComment.getUser());
		assertEquals("admin", generalComment.getUser().getUsername());
	}
	
	@Test
	@DisplayName("test generalComment mapping to reply to comment")
	void test3() {
		assertNotNull(generalComment);
		assertNotNull(generalComment.getReplyToComment());
		assertEquals("What shampoo to use?", generalComment.getReplyToComment().getTitle());
	}
	
	@Test
	@DisplayName("test generalComment mapping to replies")
	void test4() {
		assertNotNull(generalComment2);
		assertNotNull(generalComment2.getReplies());
		assertTrue(generalComment2.getReplies().size() > 0);
		assertEquals("Dog Food", generalComment2.getReplies().get(0).getTitle());
	}

}
