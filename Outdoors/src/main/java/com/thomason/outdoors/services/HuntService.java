package com.thomason.outdoors.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thomason.outdoors.models.hunt.Hunt;
import com.thomason.outdoors.repositories.HuntRepository;

@Service
public class HuntService {
    private final HuntRepository huntRepository;

    public HuntService(HuntRepository huntRepository) {
        this.huntRepository = huntRepository;
    }

    // get all tasks 
        public List<Hunt> getAll() {
            return (List<Hunt>) huntRepository.findAll();
        }

        // find a task by id
        public Hunt findHunt(Long id) {
            Optional<Hunt> myhunt = huntRepository.findById(id);
            if (myhunt.isPresent()) {
                return myhunt.get();
            }else {
                return null;
            }
        }
        // create a task
        public Hunt createHunt(Hunt myHunt) {
            return huntRepository.save(myHunt);
        }

        // Update task
        public void updateHunt(Hunt myHunt) {
            huntRepository.save(myHunt);
        }

        // Delete task
        public void deleteHunt(Long myId) {
            huntRepository.deleteById(myId);
        }
}