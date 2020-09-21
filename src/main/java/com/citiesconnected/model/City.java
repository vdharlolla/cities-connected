package com.citiesconnected.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class City {

	private String name;

	private Set<City> connectedCities = new HashSet<>();

	public City(String name) {
		Objects.requireNonNull(name);
		this.name = name.trim().toUpperCase();
	}

	public static City build(String name) {
		return new City(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City connectedCities(City city) {
		connectedCities.add(city);
		return this;
	}

	public Set<City> connectedCities() {
		return connectedCities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof City))
			return false;
		City city = (City) o;
		return Objects.equals(name, city.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
