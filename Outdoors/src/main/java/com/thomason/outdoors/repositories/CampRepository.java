package com.thomason.outdoors.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.camp.Camp;

@Repository
public interface CampRepository extends CrudRepository<Camp, Long>{
	Camp findByNameContaining();
	List<Camp> findAll();
=======

import com.thomason.outdoors.models.camp.Camp;

@Repository
public interface CampRepository extends CrudRepository<Camp, Long>{
    Camp findByNameContaining();
    List<Camp> findAll();
>>>>>>> branch 'main' of https://github.com/carlthomason/JavaProject
}
