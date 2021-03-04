package com.thomason.outdoors.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.CampMessage;

@Repository
public interface CampMessageRepository extends CrudRepository<CampMessageRepository, Long> {

	CampMessage saveAll(CampMessage comments);

	CampMessage save(CampMessage comments);
	
}
