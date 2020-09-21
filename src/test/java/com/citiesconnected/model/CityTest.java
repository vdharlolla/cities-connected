package com.citiesconnected.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.citiesconnected.model.City;

public class CityTest {

	City city;

	@Before
	public void setup() {

		city = new City("Boston");
	}

	@Test
	public void testCity() {

		city.setName("Newark");
		assertEquals("Newark", city.getName());
	}
	
	@Test
	public void testCityBuild() {

		City.build("Boston");
		assertEquals("BOSTON", city.getName());
	}
	
	@Test
	public void testCityEquals() {

		City c= new City("newark");
		City c1= new City("newark");
		assertTrue(c.equals(c1));
	}


}
