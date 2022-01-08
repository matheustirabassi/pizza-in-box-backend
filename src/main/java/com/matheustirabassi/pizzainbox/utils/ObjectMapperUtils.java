package com.matheustirabassi.pizzainbox.utils;

import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.Login;
import com.matheustirabassi.pizzainbox.dto.AddressDto;
import com.matheustirabassi.pizzainbox.dto.NewLoginDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Faz o mapeamento para DTO`s e classes do domínio.
 */
public class ObjectMapperUtils {

  private static final ModelMapper modelMapper;

  /**
   * Model mapper property setting are specified in the following block. Default property matching
   * strategy is set to Strict see {@link MatchingStrategies} Custom mappings are added using
   * {@link ModelMapper#addMappings(PropertyMap)}
   */
  static {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.createTypeMap(AddressDto.class, Address.class)
        .addMappings(new PropertyMap<>() {
          @Override
          protected void configure() {
            map().getCity().setId(source.getCity());
          }
        });
    modelMapper.createTypeMap(NewLoginDto.class, Login.class).addMapping(NewLoginDto::getUser,
        Login::setUsername);
  }

  /**
   * Hide from public usage.
   */
  private ObjectMapperUtils() {
  }

  /**
   * <p>
   * Note: outClass object must have default constructor with no arguments
   * </p>
   *
   * @param <D>      type of result object.
   * @param <T>      type of source object to map from.
   * @param entity   entity that needs to be mapped.
   * @param outClass class of result object.
   * @return new object of <code>outClass</code> type.
   */
  public static <D, T> D map(final T entity, Class<D> outClass) {
    return modelMapper.map(entity, outClass);
  }

  /**
   * <p>
   * Note: outClass object must have default constructor with no arguments
   * </p>
   *
   * @param entityList list of entities that needs to be mapped
   * @param outCLass   class of result list element
   * @param <D>        type of objects in result list
   * @param <T>        type of entity in <code>entityList</code>
   * @return list of mapped object with <code><D></code> type.
   */
  public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
    return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
  }

  /**
   * Maps {@code source} to {@code destination}.
   *
   * @param source      object to map from
   * @param destination object to map to
   */
  public static <S, D> D map(final S source, D destination) {
    modelMapper.map(source, destination);
    return destination;
  }
}