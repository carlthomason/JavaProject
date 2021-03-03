package com.thomason.outdoors.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.fish.Fish;

@Repository
public interface FishRepository  extends CrudRepository<Fish, Long>{
	Fish findByNameContaining();
}
