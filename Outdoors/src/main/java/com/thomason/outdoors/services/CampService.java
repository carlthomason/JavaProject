package com.thomason.outdoors.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thomason.outdoors.models.Camp;
import com.thomason.outdoors.models.CampMessage;
import com.thomason.outdoors.repositories.CampMessageRepository;
import com.thomason.outdoors.repositories.CampRepository;

@Service
public class CampService {

    private final CampRepository campRepository;
    private final CampMessageRepository campMessageRepository;

    public CampService(CampRepository campRepository, CampMessageRepository campMessageRepository){
        this.campRepository = campRepository;
        this.campMessageRepository= campMessageRepository;
    }

    // get all camp 
        public List<Camp> getAll() {
            return (List<Camp>) campRepository.findAll();
        }

        // find a camp by id
        public Camp findCamp(Long id) {
            Optional<Camp> mycamp = campRepository.findById(id);
            if (mycamp.isPresent()) {
                return mycamp.get();
            }else {
                return null;
            }
        }
        // create a camp
        public Camp createCamp(Camp myCamp) {
            return campRepository.save(myCamp);
        }

        // Update camp
        public void updateCamp(Camp myCamp) {
            campRepository.save(myCamp);
        }

        // Delete camp
        public void deleteCamp(Long myId) {
            campRepository.deleteById(myId);
        }
        
        
        // show messages on main camp page
        
        public CampMessage showMessage(CampMessage comments) {
        	return(CampMessage) campMessageRepository.findAll();
        }
        //add message to camp message board
        
        public CampMessage addMessage(CampMessage comments) {
        	return campMessageRepository.save(comments);
        }
}
	

