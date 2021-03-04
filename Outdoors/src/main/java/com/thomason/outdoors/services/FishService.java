package com.thomason.outdoors.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thomason.outdoors.models.Fish;
import com.thomason.outdoors.repositories.FishRepository;

@Service
public class FishService {
    private final FishRepository fishRepository;

    public FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    // get all Fish 
        public List<Fish> getAll() {
            return (List<Fish>) fishRepository.findAll();
        }

        // find a fish by id
        public Fish findFish(Long id) {
            Optional<Fish> myFish = fishRepository.findById(id);
            if (myFish.isPresent()) {
                return myFish.get();
            }else {
                return null;
            }
        }
        // create a fish
        public Fish createFish(Fish myFish) {
            return fishRepository.save(myFish);
        }

        // Update fish
        public void updateFish(Fish myFish) {
            fishRepository.save(myFish);
        }

        // Delete fish
        public void deleteFish(Long myId) {
            fishRepository.deleteById(myId);
        }
}