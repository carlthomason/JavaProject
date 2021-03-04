package com.thomason.outdoors.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.Fish;

@Repository
public interface FishRepository  extends CrudRepository<Fish, Long>{

	Fish findByNameContaining();
	List<Fish> findAll();

}

