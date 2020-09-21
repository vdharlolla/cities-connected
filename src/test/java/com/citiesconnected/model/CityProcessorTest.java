package com.citiesconnected.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CityProcessorTest {

	@Autowired
	CityProcessor cityProcessor;

	@Test
	public void testfileLoadCityProcessor() {
		Assert.assertTrue(cityProcessor.getCityMap().containsKey("BOSTON"));
		City city = cityProcessor.getCity("BOSTON");
		Assert.assertEquals("BOSTON", city.getName());
	}
}
