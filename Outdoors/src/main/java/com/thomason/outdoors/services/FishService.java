package com.thomason.outdoors.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thomason.outdoors.models.fish.Fish;
import com.thomason.outdoors.repositories.FishRepository;

@Service
public class FishService {
    private final FishRepository fishRepository;

    public FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    // get all tasks 
        public List<Fish> getAll() {
            return (List<Fish>) fishRepository.findAll();
        }

        // find a task by id
        public Fish findFish(Long id) {
            Optional<Fish> myfish = fishRepository.findById(id);
            if (myfish.isPresent()) {
                return myfish.get();
            }else {
                return null;
            }
        }
        // create a task
        public Fish createFish(Fish myFish) {
            return fishRepository.save(myFish);
        }

        // Update task
        public void updateFish(Fish myFish) {
            fishRepository.save(myFish);
        }

        // Delete task
        public void deleteFish(Long myId) {
            fishRepository.deleteById(myId);
        }
}