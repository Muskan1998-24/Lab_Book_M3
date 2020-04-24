package com.capg.dao;

import org.springframework.stereotype.Repository;

import com.capg.entities.Trainee;
import com.capg.exception.TraineeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TraineeDaoImpl implements ITraineeDao {
    private Map<Integer, Trainee> store = new HashMap<>();
    public TraineeDaoImpl() {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);
        trainee.setTraineeName("Aman");
        trainee.setPassword("aman");
        trainee.setTraineeLocation("pune");
       
        }
    
    @Override
	public void addTrainee(Trainee t) {
		store.put(t.getTraineeId(),t);
		
	}

	@Override
	public void deleteTrainee(int id) {
		 Trainee t = store.get(id);
		 if(t == null) {
		 throw new TraineeNotFoundException("Trainee not found for id ="+id);
		 }
    	 	 store.remove(t.getTraineeId());
    	}

	@Override
	public Trainee modifyTrainee(Trainee t) {
    	if(store.containsKey(t.getTraineeId())) {
    		store.put(t.getTraineeId(),t);
    	}
    	return t;
	}

    @Override
    public Trainee findTraineeById(int id) {
        Trainee trainee = store.get(id);
        return trainee;
    }

    @Override
    public void save(Trainee trainee) {
        store.put(trainee.getTraineeId(), trainee);
    }

    @Override
    public boolean credentialsCorrect(int id, String password){
        if(password==null|| password.isEmpty()){
            return false;
        }
        Trainee trainee=store.get(id);
        if(trainee==null){
          return false;
        }
        boolean passwordEquals=trainee.getPassword().equals(password);
        return passwordEquals;
    }
}
