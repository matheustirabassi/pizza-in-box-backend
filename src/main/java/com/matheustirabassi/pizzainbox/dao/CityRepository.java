package com.matheustirabassi.pizzainbox.dao;

import com.matheustirabassi.pizzainbox.domain.City;
import java.util.List;

public interface CityRepository extends GenericRepository<City> {

  List<City> findAllByStateId(Long id);
}