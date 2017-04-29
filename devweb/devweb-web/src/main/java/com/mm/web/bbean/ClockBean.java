package com.mm.web.bbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Named;


import org.springframework.context.annotation.Scope;

@Named("clockBean")
@Scope("session")
public class ClockBean implements Serializable {
	 
	 
    private static final long serialVersionUID = 1L;
 
 
    private Date romeTime;
    private Date londonTime;
    private Date newYorkTime;
    private int number;
 
    public ClockBean(){
    	loadTimes();
    }
    
 
    public void increment() {
        number++;
    }
    
    public void loadTimes(){
        romeTime = new Date();
 
 
        Calendar c = Calendar.getInstance();
 
 
        c.setTime(romeTime);
        c.add(Calendar.HOUR, -1);
        londonTime = c.getTime();
 
 
        c.setTime(romeTime);
        c.add(Calendar.HOUR, -5);
        newYorkTime = c.getTime();
    }
 
 
    public Date getRomeTime() {
        return romeTime;
    }
 
 
    public Date getLondonTime(){
        return londonTime;
    }
 
 
    public Date getNewYorkTime(){
        return newYorkTime;
    }


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}
 
 
}
