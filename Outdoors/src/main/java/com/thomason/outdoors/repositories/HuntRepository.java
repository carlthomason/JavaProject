package com.thomason.outdoors.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.Hunt;

@Repository
public interface HuntRepository extends CrudRepository<Hunt, Long> {

	List<Hunt> findAll();	
	List<Hunt> findByNameContaining();
}