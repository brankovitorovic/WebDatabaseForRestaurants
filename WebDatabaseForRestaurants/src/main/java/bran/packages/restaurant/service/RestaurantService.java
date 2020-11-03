package bran.packages.restaurant.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bran.packages.restaurant.dto.RestaurantDTO;
import bran.packages.restaurant.entity.Restaurant;
import bran.packages.restaurant.exception.InvalidRestaurantInfo;
import bran.packages.restaurant.mapper.RestaurantMapper;
import bran.packages.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;

	private final RestaurantMapper restaurantMapper;

	@Transactional
	public RestaurantDTO save(RestaurantDTO restaurantDTO) {

		if (existName(restaurantDTO.getId(), restaurantDTO.getName())) {
			throw new InvalidRestaurantInfo(
					String.format("Restaurant with name: %s already exist! ", restaurantDTO.getName()));
		}

		Restaurant r = restaurantMapper.fromDTO(restaurantDTO);

		r.setLastChange(Date.valueOf(LocalDate.now()));

		restaurantRepository.save(r);

		return restaurantMapper.toDTO(r);
	}

	private boolean existId(Long id) {
		return restaurantRepository.existsById(id);
	}

	private boolean existName(Long id, String name) { // this is for update, if we change thing in entity other than
														// name, then nameExist method would not work
		return findAll().stream().filter(r -> r.getName().equals(name) && r.getId() != id).count() > 0;
	}

	@Transactional
	public void delete(Long id) {

		if (!existId(id)) {
			throw new InvalidRestaurantInfo(String.format("Restaurant with id: %d does not exist!", id));
		}
		restaurantRepository.deleteById(id);

	}

	@Transactional
	public RestaurantDTO update(Long id, RestaurantDTO restaurant) {

		if (!existName(id, restaurant.getName())) {
			throw new InvalidRestaurantInfo(
					(String.format("Restaurant with name: %s already exist! ", restaurant.getName())));
		}
		restaurant.setLastChange(Date.valueOf(LocalDate.now()));
		Restaurant r = restaurantRepository.save(restaurantMapper.fromDTO(restaurant));

		return restaurantMapper.toDTO(r);

	}

	@Transactional
	public List<RestaurantDTO> findAll() {

		List<RestaurantDTO> res = restaurantMapper.toDTOList(restaurantRepository.findAll());

		if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			res.forEach(r -> r.setLastChange(null));
		}
		return res;
	}

	public List<RestaurantDTO> search(String name){
		
		List<RestaurantDTO> res = restaurantMapper.toDTOList(restaurantRepository.findByNameIgnoreCaseContaining(name));
		
		return res;
	}
	
	
}
