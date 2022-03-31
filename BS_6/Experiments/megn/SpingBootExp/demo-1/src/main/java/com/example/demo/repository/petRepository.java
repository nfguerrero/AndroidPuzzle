package com.example.demo.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pet;
	@Repository
	public interface petRepository extends CrudRepository<Pet, Long>{
	    List<Pet> findByName(String name);

	}
