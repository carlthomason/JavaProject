package com.thomason.outdoors.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thomason.outdoors.models.camp.Camp;
import com.thomason.outdoors.repositories.CampRepository;

@Service
public class CampService {
<<<<<<< HEAD
    private final CampRepository campRepository;

    public CampService(CampRepository campRepository) {
        this.campRepository = campRepository;
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
}
=======
	private final CampRepository campRepository;
	
	public CampService(CampRepository campRepository) {
		this.campRepository = campRepository;
	}
	
	// get all tasks 
		public List<Camp> getAll() {
			return (List<Camp>) campRepository.findAll();
		}
		
		// find a task by id
		public Camp findCamp(Long id) {
			Optional<Camp> mycamp = campRepository.findById(id);
			if (mycamp.isPresent()) {
				return mycamp.get();
			}else {
				return null;
			}
		}
		// create a task
		public Camp createCamp(Camp myCamp) {
			return campRepository.save(myCamp);
		}
		
		// Update task
		public void updateCamp(Camp myCamp) {
			campRepository.save(myCamp);
		}
			
		// Delete task
		public void deleteCamp(Long myId) {
			campRepository.deleteById(myId);
		}
}
>>>>>>> branch 'main' of https://github.com/carlthomason/JavaProject
