package bran.packages.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bran.packages.restaurant.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	Restaurant findByName(String name);

	boolean existsByName(String name);
	
	List<Restaurant> findByNameIgnoreCaseContaining(String name);
}
