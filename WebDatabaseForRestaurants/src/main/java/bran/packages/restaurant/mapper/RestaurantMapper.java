package bran.packages.restaurant.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import bran.packages.restaurant.dto.RestaurantDTO;
import bran.packages.restaurant.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDTO toDTO(Restaurant restaurant);

    Restaurant fromDTO(RestaurantDTO restaurantDTO);

    List<RestaurantDTO> toDTOList(List<Restaurant> restaurants);
	
}
