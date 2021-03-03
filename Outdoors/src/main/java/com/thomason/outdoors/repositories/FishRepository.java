package com.thomason.outdoors.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomason.outdoors.models.fish.Fish;

@Repository
public interface FishRepository  extends CrudRepository<Fish, Long>{
<<<<<<< HEAD
	Fish findByNameContaining();
	List<Fish> findAll();
}
=======
    Fish findByNameContaining();
    List<Fish> findAll();
}
>>>>>>> branch 'main' of https://github.com/carlthomason/JavaProject
