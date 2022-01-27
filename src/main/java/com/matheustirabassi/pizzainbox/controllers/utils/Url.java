package com.matheustirabassi.pizzainbox.controllers.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Url {

  /**
   * Converte lista de ids em formato string para lista de inteiros.
   *
   * @param ids os ids passados como parâmetros.
   * @return a lista de inteiros.
   */
  public static List<Long> decoreIntList(String ids) {
    return Arrays.stream(ids.split(",")).map(Long::parseLong)
        .collect(Collectors.toList());
  }

  public static String decoreParam(String param) {
    return URLDecoder.decode(param, StandardCharsets.UTF_8);
  }
}