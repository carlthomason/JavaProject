package com.thomason.outdoors.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.camp.Camp;

@Repository
public interface CampRepository extends CrudRepository<Camp, Long>{
	Camp findByNameContaining();
}
