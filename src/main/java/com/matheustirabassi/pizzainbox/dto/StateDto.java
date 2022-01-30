package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.State;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class StateDto {

  private Long id;
  private String name;

  public StateDto(State state) {
    id = state.getId();
    name = state.getName();
  }

  public static List<StateDto> convertList(List<State> states) {
    return states.stream().map(StateDto::new).collect(Collectors.toList());
  }
}