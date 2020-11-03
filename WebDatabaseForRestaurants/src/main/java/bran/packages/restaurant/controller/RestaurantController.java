package bran.packages.restaurant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bran.packages.restaurant.dto.RestaurantDTO;
import bran.packages.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

	private final RestaurantService restaurantService;
	
	@PostMapping("/save")
	public ResponseEntity<RestaurantDTO> save(@Valid @RequestBody RestaurantDTO request){
		return new ResponseEntity<>(restaurantService.save(request),HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<RestaurantDTO>> findAll(){
		return new ResponseEntity<>(restaurantService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/edit/{id}") // I know it is suppose to be Put instead of Post but google chrome than makes problem with CORS
	public ResponseEntity<RestaurantDTO> update(@Valid @PathVariable Long id,@RequestBody RestaurantDTO request){ 
		return new ResponseEntity<>(restaurantService.update(id, request),HttpStatus.OK);
	}
	
	@PostMapping("/delete/{id}") // again problem with google chrome, it is better option to be DeleteMapping
	public ResponseEntity<?> delete(@PathVariable Long id){ 
		restaurantService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/search = {name}")
	public ResponseEntity<List<RestaurantDTO>> search(@Valid @PathVariable String name){
		
		return new ResponseEntity<>(restaurantService.search(name),HttpStatus.OK);
	}
	
}
