package com.citiesconnected.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.citiesconnected.controller.CitiesConnectionController;
import com.citiesconnected.exception.CitiesConnectionValidationException;
import com.citiesconnected.model.City;
import com.citiesconnected.model.CityProcessor;
import com.citiesconnected.service.CitiesConnectionService;

public class CitiesConnectionControllerJunitTest {

	@InjectMocks
	CitiesConnectionController citiesConnectionController;

	@Mock
	CityProcessor cityProcessor;

	@Mock
	CitiesConnectionService citiesConnectionService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCitiesConnectionControllerGetInfo() {
		String response = citiesConnectionController.info();
		assertNotNull(response);
	}

	@Test
	public void testTwoCitiesConnectedWhenGivenValidSourceAndDestination() {
		City city = new City("Boston");
		when(cityProcessor.getCity(Mockito.anyString())).thenReturn(city);
		when(citiesConnectionService.computeRoute(Mockito.any(), Mockito.any())).thenReturn("yes");
		String response = citiesConnectionController.isConnected("Boston", "Newark");
		assertEquals("yes", response);
	}

	@Test(expected = CitiesConnectionValidationException.class)
	public void testTwoCitiesConnectedWhenGivenInValidSourceAndDestination() {
		City city = null;
		when(cityProcessor.getCity(Mockito.anyString())).thenReturn(city);
		when(citiesConnectionService.computeRoute(Mockito.any(), Mockito.any())).thenReturn("yes");
		citiesConnectionController.isConnected("Boton", "Neark");
	}

}
