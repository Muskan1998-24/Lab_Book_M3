package com.capg.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * every time session is created for users , object of SessionData will be created
 */
@Component
@SessionScope
public class SessionData {

    private int TraineeID=-1;

    public int getTraineeID(){
        return TraineeID;
    }

    public void setTraineeID(int TraineeID){
        this.TraineeID = TraineeID;
    }

}
