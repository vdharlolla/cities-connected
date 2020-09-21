package com.citiesconnected.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.citiesconnected.model.City;
import com.citiesconnected.service.CitiesConnectionServiceImpl;

public class CitiesConnectionServiceImplTest {

	@InjectMocks
	CitiesConnectionServiceImpl citiesConnectionServiceImpl;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testComputeRouteWhenBothDestinationAndSourceSame() {
		City c1 = new City("Boston");
		City c2 = new City("Boston");
		assertEquals("yes", citiesConnectionServiceImpl.computeRoute(c1, c2));
	}

	@Test
	public void testComputeRoute() {
		City c1 = new City("Boston");
		c1.connectedCities(c1);
		City c2 = new City("Boston");
		assertEquals("yes", citiesConnectionServiceImpl.computeRoute(c1, c2));
	}

	@Test
	public void testWhenBothCitiesNotComputeRoute() {
		City c1 = new City("Boston");
		c1.connectedCities(c1);
		City c2 = new City("Albany");
		assertEquals("no", citiesConnectionServiceImpl.computeRoute(c1, c2));
	}
}
