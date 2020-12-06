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
		assertNotNull(user.getAddress());
		assertEquals(2, user.getAddress().getId());
		assertEquals("123 happy st", user.getAddress().getStreet());
		assertEquals("dogwood", user.getAddress().getCity());
		assertEquals("CA", user.getAddress().getStateAbbrv());
		assertEquals("12345", user.getAddress().getZipcode());
	}
	
	@Test
	@DisplayName("One to Many User to Route Test")
	void test3() {
//		SELECT user.username, route.* FROM route JOIN user ON route.user_id = user.id where user.id = 1;
//		+----------+----+---------------------+---------------------+---------+
//		| username | id | start_time          | end_time            | user_id |
//		+----------+----+---------------------+---------------------+---------+
//		| admin    |  1 | 2020-10-10 12:30:16 | 2020-10-11 12:30:40 |       1 |
//		+----------+----+---------------------+---------------------+---------+
		
		assertNotNull(user);
		assertNotNull(user.getRoutes());
		assertTrue(user.getRoutes().size()>0);
		assertEquals(2020, user.getRoutes().get(0).getStartTime().getYear());
		
	}
	
	@Test
	@DisplayName("One to Many User to Meetup Comment")
	void test4() {
////		SELECT user.username, meetup_comment.* FROM meetup_comment JOIN user ON meetup_comment.user_id = user.id where user.id = 1;
////		+----------+----+---------------------+-------------------+-------------------------+---------------------+-----------+---------+
////		| username | id | comment_date        | comment_text      | title                   | reply_to_comment_id | meetup_id | user_id |
////		+----------+----+---------------------+-------------------+-------------------------+---------------------+-----------+---------+
////		| admin    |  1 | 2020-10-10 12:30:16 | Lorem ipsum dolor | My Dog has fleas, Help? |                NULL |         1 |       1 |
////		+----------+----+---------------------+-------------------+-------------------------+---------------------+-----------+---------+
//		
		assertNotNull(user);
		assertNotNull(user.getMeetupComments());
		assertTrue(user.getMeetupComments().size()>0);
		assertEquals(2020, user.getMeetupComments().get(0).getCommentDate().getYear());
		assertEquals("My Dog has fleas, Help?", user.getMeetupComments().get(0).getTitle());
		
	}

	@Test
	@DisplayName("One to Many User to Meetup")
	void test5() {
		
//		SELECT user.username, meetup.title FROM meetup JOIN user ON meetup.user_id_creator = user.id where user.id = 1;
//		+----------+---------------+
//		| username | title         |
//		+----------+---------------+
//		| admin    | dog and chill |
//		+----------+---------------+
		assertNotNull(user);
		assertNotNull(user.getMeetups());
		assertTrue(user.getMeetups().size()>0);
		assertEquals("dog and chill", user.getMeetups().get(0).getTitle());
	}
	@Test
	@DisplayName("One to Many User to Dog")
	void test6() {
//		SELECT user.username, dog.* FROM dog JOIN user ON dog.user_id = user.id where user.id = 1;
//		+----------+----+------+-------------------+-------------+--------------------------------------------------------------------------------------------------+----------------+------+------+----------+----------------+---------------------+---------+--------+
//		| username | id | name | breed             | temperament | dog_profile_pic_url                                                                              | activity_level | size | bio  | birthday | rainbow_bridge | create_date         | user_id | gender |
//		+----------+----+------+-------------------+-------------+--------------------------------------------------------------------------------------------------+----------------+------+------+----------+----------------+---------------------+---------+--------+
//		| admin    |  1 | Kona | Australian Kelpie | NULL        | https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/14153642/Kelpie.4.jpg | NULL           | NULL | NULL | NULL     | NULL           | 2020-10-10 00:00:00 |       1 | NULL   |
//		+----------+----+------+-------------------+-------------+--------------------------------------------------------------------------------------------------+----------------+------+------+----------+----------------+---------------------+---------+--------+
		assertNotNull(user);
		assertNotNull(user.getDogs());
		assertTrue(user.getDogs().size()>0);
		assertEquals("Australian Kelpie", user.getDogs().get(0).getBreed());
	}
	@Test
	@DisplayName("One to Many User to Dog Review")
	void test7() {
//		SELECT user.username, dog_review.rating, dog_review.review FROM dog_review JOIN user ON dog_review.user_id = user.id where user.id = 1;
//		+----------+--------+-------------------------------------------------------------+
//		| username | rating | review                                                      |
//		+----------+--------+-------------------------------------------------------------+
//		| admin    |      5 | Nam semper maximus elit id porta. Phasellus eu velit purus. |
//		+----------+--------+-------------------------------------------------------------+
		assertNotNull(user);
		assertNotNull(user.getDogReviews());
		assertTrue(user.getDogReviews().size()>0);
		assertEquals("Nam semper maximus elit id porta. Phasellus eu velit purus.", user.getDogReviews().get(0).getReview());
	}
	@Test
	@DisplayName("One to Many User to Dog Park Review")
	void test8() {
/// SELECT user.username, dog_park_review.rating, dog_park_review.review FROM dog_park_review JOIN user ON dog_park_review.user_id = user.id where user.id = 1;
//+----------+--------+-------------------------------------------------------------+
//| username | rating | review                                                      |
//+----------+--------+-------------------------------------------------------------+
//| admin    |      5 | Nam semper maximus elit id porta. Phasellus eu velit purus. |
//+----------+--------+-------------------------------------------------------------+
		assertNotNull(user);
		assertNotNull(user.getDogParkReviews());
		assertTrue(user.getDogParkReviews().size()>0);
		assertEquals("Nam semper maximus elit id porta. Phasellus eu velit purus.", user.getDogParkReviews().get(0).getReview());
	}
	@Test
	@DisplayName("One to Many User to General Comment")
	void test9() {
//		SELECT user.username, general_comment.* FROM general_comment JOIN user ON general_comment.user_id = user.id where user.id = 1;
//		+----------+----+---------------------+-------------------------------------------------------------+----------------------+---------------------+---------+
//		| username | id | comment_date        | comment_text                                                | title                | reply_to_comment_id | user_id |
//		+----------+----+---------------------+-------------------------------------------------------------+----------------------+---------------------+---------+
//		| admin    |  1 | 2020-10-10 12:30:16 | Nam semper maximus elit id porta. Phasellus eu velit purus. | What shampoo to use? |                NULL |       1 |
//		| admin    |  2 | 2020-10-10 12:50:16 | Nam semper maximus elit id porta. Phasellus eu velit purus. | Dog Food             |                   1 |       1 |
//		+----------+----+---------------------+-------------------------------------------------------------+----------------------+---------------------+---------+
//		
		assertNotNull(user);
		assertNotNull(user.getGeneralComments());
		assertTrue(user.getGeneralComments().size()>0);
		assertEquals(2020, user.getGeneralComments().get(0).getCommentDate().getYear());
		assertEquals("What shampoo to use?", user.getGeneralComments().get(0).getTitle());
		
	}
	
	
	
	@Test
	@DisplayName("test user mapping to user (MTM friend list)")
	void test10() {
		// TODO currently user 1 is only friends with self, may want to add another user in db and add to user_friend_request
		assertNotNull(user);
		assertNotNull(user.getFriendList());
		assertTrue(user.getFriendList().size() > 0);
		assertEquals("admin", user.getFriendList().get(0).getUsername());
	}
	
}
