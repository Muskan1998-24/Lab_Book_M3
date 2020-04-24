package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.capg.entities.Trainee;
import com.capg.exception.TraineeNotFoundException;
import com.capg.exception.TraineeNotFoundException;
import com.capg.service.ITraineeService;
import com.capg.util.SessionData;

@Controller
public class TraineeController {

    @Autowired
    private ITraineeService service;
  
    public ITraineeService getService(){
        return service;
    }

    @Autowired
    private SessionData sessionData;

    @GetMapping("/")
    public ModelAndView home(){
       return new ModelAndView("homepage");
    }


    @GetMapping("/register")
    //@RequestMapping(path = "/register",method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView("registertrainee");
        return mv;
    }

    @GetMapping("/processregister")
    public ModelAndView processRegister(@RequestParam("traineeid") int id,
                                        @RequestParam("traineename") String traineename,
                                        @RequestParam("password") String password) {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(id);
        trainee.setTraineeName(traineename);
        trainee.setPassword(password);
        service.save(trainee);
        ModelAndView mv = new ModelAndView("details", "trainee", trainee);
        return mv;
    }

    @GetMapping("/details")
    public ModelAndView traineeDetails() {
        int id = sessionData.getTraineeID();
        if (id == -1) {
            return new ModelAndView("login");
        }
        Trainee trainee = service.findTraineeById(id);
        ModelAndView mv = new ModelAndView("details", "trainee", trainee);
        return mv;
    }
    

    @GetMapping("/processfindtrainee")
    public ModelAndView traineeDetails(@RequestParam("traineeid")int traineeId) {
        Trainee trainee= service.findTraineeById(traineeId);
        return new ModelAndView("details", "trainee", trainee);
    }
    
    
    @GetMapping("/update")
    public ModelAndView updatePage() {
    	int id=sessionData.getTraineeID();
        if(id==-1){
         return new ModelAndView("/login");
        }
        return new ModelAndView("updatetrainee");
    }



    @GetMapping("/processupdate") //modifying trainee.
    public ModelAndView updateTrainee(@RequestParam("traineeid") int traineeId,
                                         @RequestParam("traineename") String traineeName, @RequestParam("traineelocation")String location)
    {
    	Trainee trainee=new Trainee();
        trainee.setTraineeId(traineeId);
        trainee.setTraineeName(traineeName);
        trainee.setTraineeLocation(location);
        service.modifyTrainee(trainee);
    	return new ModelAndView("traineedetails",  "trainee", trainee);
    }


    @GetMapping("/delete")
    public ModelAndView deletePage() {
    	 int id=sessionData.getTraineeID();
         if(id==-1){
          return new ModelAndView("/login");
         }
        return new ModelAndView("deletetrainee");
    }



    @GetMapping("/processdelete") //removing trainee.
    public ModelAndView deleteTrainee(@RequestParam("traineeid")int traineeId) {
    	getService().deleteTrainee(traineeId);
    	return new ModelAndView("traineeHome");
    }
    
     
  

    @GetMapping("/logout")
    public ModelAndView logout(){
        sessionData.setTraineeID(-1);
        return new ModelAndView("/login");
    }



    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/processlogin")
    public ModelAndView processLogin(@RequestParam("traineeid") int id, @RequestParam("password") String password) {
        boolean correct = service.credentialsCorrect(id, password);
        if (!correct) {
            return new ModelAndView("login");
        }
        sessionData.setTraineeID(id);
        Trainee trainee = service.findTraineeById(id);
        ModelAndView mv = new ModelAndView("details", "trainee", trainee);
        return mv;
    }

    @ExceptionHandler(TraineeNotFoundException.class)
	 public ResponseEntity<String> handleTraineeNotFound(TraineeNotFoundException exception){
   	ResponseEntity<String> response = new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
   	return response;
	}



}
