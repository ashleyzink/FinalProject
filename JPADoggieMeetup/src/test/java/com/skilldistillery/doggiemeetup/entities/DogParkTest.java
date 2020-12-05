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

class DogParkTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DogPark dogPark;

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
		dogPark = em.find(DogPark.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dogPark = null;
	}

	@Test
	@DisplayName("DogPark entity test")
	void test1() {
		assertNotNull(dogPark);
		assertEquals("Redwood", dogPark.getName());
	}

	@Test
	@DisplayName("testing DogPark to Address mapping")
	void test2() {
		assertNotNull(dogPark);
		assertEquals("1234 dog st.", dogPark.getAddress().getStreet());
		assertEquals("dogwood", dogPark.getAddress().getCity());
	}
	
}
