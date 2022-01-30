package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.City;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class CityDto {
  private Long id;
  private String name;

  public CityDto(City city) {
    id = city.getId();
    name = city.getName();
  }

  public static List<CityDto> convertList(List<City> cities) {
    return cities.stream().map(CityDto::new).collect(Collectors.toList());
  }
}