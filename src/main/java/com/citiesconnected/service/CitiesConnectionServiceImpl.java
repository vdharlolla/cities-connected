package com.citiesconnected.service;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.citiesconnected.model.City;

@Component
public class CitiesConnectionServiceImpl implements CitiesConnectionService {

	private static final String NO = "no";
	private static final String YES = "yes";
	private static final Log logger = LogFactory.getLog(CitiesConnectionServiceImpl.class);

	@Override
	public String computeRoute(City origin, City destination) {
		logger.info("Origin: " + origin.getName() + ", destination: " + destination.getName());

		if (origin.equals(destination)) {
			return YES;
		}

		if (origin.connectedCities().contains(destination)) {
			return YES;
		}
		if (destination.connectedCities().contains(origin)) {
			return YES;
		}
		
		Set<City> originCity = new HashSet<>(Collections.singleton(origin));

		Deque<City> citiesList = new ArrayDeque<>(origin.connectedCities());

		while (!citiesList.isEmpty()) {
			City city = citiesList.getLast();
			if (city.equals(destination))
				return YES;
			if (!originCity.contains(city)) {
				originCity.add(city);
				citiesList.addAll(city.connectedCities());
				citiesList.removeAll(originCity);
			} else {
				citiesList.removeAll(Collections.singleton(city));
			}
		}

		return NO;
	}
}
