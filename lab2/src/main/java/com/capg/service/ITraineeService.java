package com.capg.service;

import com.capg.entities.Trainee;

public interface ITraineeService 
{
	  void addTrainee(Trainee t);
	    
	  void deleteTrainee(int id);
	  
	  Trainee modifyTrainee(Trainee t);
	
	  Trainee findTraineeById(int id);

      void save(Trainee trainee);

      boolean credentialsCorrect(int id, String password);
}
