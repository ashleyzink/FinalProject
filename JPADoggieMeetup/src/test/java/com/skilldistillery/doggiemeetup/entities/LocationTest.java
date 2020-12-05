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

class LocationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Location location;

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
		location = em.find(Location.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		location = null;
	}

	@DisplayName("testing entity mapping for location")
	@Test
	void location() {
		assertNotNull(location);
		assertEquals(33.47532343320456, location.getLat());
		assertEquals(-117.10128305844954, location.getLng());
	}

	@DisplayName("testing entity mapping for one to one location to user")
	@Test
	void locationToUser() {
		assertNotNull(location);
		assertNotNull(location.getLat());
		assertEquals("admin", location.getUser().getUsername());
	}

	@DisplayName("testing entity mapping for one to many location to route")
	@Test
	void locationToRoute() {
		assertNotNull(location);
		assertNotNull(location.getRoute());
//		assertEquals("2020-10-10 12:30:16", location.getRoute().getStartTime()

//		assertEquals("2020-10-10 12:30:16", location.getRouteId().getStartTime());
	}

}
