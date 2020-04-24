package com.capg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dao.ITraineeDao;
import com.capg.entities.Trainee;

@Service
public class TraineeServiceImpl implements ITraineeService 
{
    private ITraineeDao traineeDao;

    public ITraineeDao getTraineeDao()
    {
        return traineeDao;
    }

    @Autowired
    public void setTraineeDao(ITraineeDao dao) 
    {
        this.traineeDao = dao;
    }
    @Override
	public void addTrainee(Trainee t) {
		traineeDao.addTrainee(t);
		
	}

	@Override
	public void deleteTrainee(int id) {
		traineeDao.deleteTrainee(id);
		
	}

	@Override
	public Trainee modifyTrainee(Trainee t) {
		Trainee trainee = traineeDao.modifyTrainee(t);
		return trainee;
	}


    @Override
    public Trainee findTraineeById(int id) {
        Trainee trainee = traineeDao.findTraineeById(id);
        return trainee;
    }

    @Override
    public void save(Trainee trainee) {
        traineeDao.save(trainee);
    }

    @Override
    public boolean credentialsCorrect(int id, String password) {
        boolean correct = traineeDao.credentialsCorrect(id, password);
        return correct;
    }
}
