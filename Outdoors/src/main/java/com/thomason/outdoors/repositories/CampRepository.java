package com.thomason.outdoors.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thomason.outdoors.models.camp.Camp;

@Repository
public interface CampRepository extends CrudRepository<Camp, Long>{
    Camp findByNameContaining();
    List<Camp> findAll();
}
