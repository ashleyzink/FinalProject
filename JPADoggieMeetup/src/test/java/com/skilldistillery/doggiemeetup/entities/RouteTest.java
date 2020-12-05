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

class RouteTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Route route;

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
		route = em.find(Route.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		route = null;
	}

	@Test
	@DisplayName("Route entity test")
	void test1() {
		assertNotNull(route);
		assertEquals(2020, route.getStartTime().getYear());
		assertEquals(10, route.getStartTime().getMonthValue());
		assertEquals(10, route.getStartTime().getDayOfMonth());
	}
	
//	mysql> SELECT route.*, user.username FROM route JOIN user ON user.id = route.user_id WHERE route.id = 1; 
//	+----+---------------------+---------------------+---------+----------+
//	| id | start_time          | end_time            | user_id | username |
//	+----+---------------------+---------------------+---------+----------+
//	|  1 | 2020-10-10 12:30:16 | 2020-10-11 12:30:40 |       1 | admin    |
//	+----+---------------------+---------------------+---------+----------+

	@Test
	@DisplayName("testing Route to User many to one relationship mapping")
	void test2() {
		assertNotNull(route);
		assertEquals(2020, route.getStartTime().getYear());
		assertEquals("admin", route.getUser().getUsername());
	}
	
//	mysql> SELECT route.*, location.lat, location.lng FROM location JOIN route ON route.id = location.route_id WHERE route.id = 1;
//	+----+---------------------+---------------------+---------+-------------------+---------------------+
//	| id | start_time          | end_time            | user_id | lat               | lng                 |
//	+----+---------------------+---------------------+---------+-------------------+---------------------+
//	|  1 | 2020-10-10 12:30:16 | 2020-10-11 12:30:40 |       1 | 33.47532343320456 | -117.10128305844954 |
//	+----+---------------------+---------------------+---------+-------------------+---------------------+
	
	@Test
	@DisplayName("testing Route to Location one to many relationship mapping")
	void test3() {
		assertNotNull(route);
		assertEquals(2020, route.getStartTime().getYear());
		assertTrue(route.getLocations().size() > 0);
		assertEquals(33.47532343320456, route.getLocations().get(0).getLat());
		assertEquals(-117.10128305844954, route.getLocations().get(0).getLng());
	}
	
}
