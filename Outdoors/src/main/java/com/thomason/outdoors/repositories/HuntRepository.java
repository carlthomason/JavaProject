package com.thomason.outdoors.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.hunt.Hunt;

@Repository
public interface HuntRepository extends CrudRepository<Hunt, Long> {
<<<<<<< HEAD
	List<Hunt> findAll();	
	List<Hunt> findByNameContaining();
=======
    
    List<Hunt> findByNameContaining();
>>>>>>> branch 'main' of https://github.com/carlthomason/JavaProject
}
