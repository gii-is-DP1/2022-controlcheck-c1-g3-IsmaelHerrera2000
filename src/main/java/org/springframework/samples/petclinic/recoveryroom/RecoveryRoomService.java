package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class RecoveryRoomService {
	
	 RecoveryRoomRepository repo;
	    @Autowired
	    public RecoveryRoomService(RecoveryRoomRepository repo){
	        this.repo=repo;
	    }
	
    public List<RecoveryRoom> getAll(){
        return repo.findAll();
    }

    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return null;
    }

    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return repo.getRecoveryRoomType(typeName);
    }

    public RecoveryRoom save(RecoveryRoom p) {   
    	return repo.save(p);
    	
    	/*RecoveryRoom copia = repo.findAll().stream().filter(x -> x.name.equals(p.getName())).distinct().findFirst().get();
        RecoveryRoomType recoveryRoomTypeOriginal = getRecoveryRoomType(p.roomType.name);
        if (copia.getRoomType() == recoveryRoomTypeOriginal) {
            throws DuplicatedRoomNameException();
        }else {
        	return repo.save(p);    
        }*/
        
    }
    

    
}
