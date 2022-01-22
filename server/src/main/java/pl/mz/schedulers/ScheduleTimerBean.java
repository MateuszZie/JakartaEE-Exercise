/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.schedulers;

import javax.ejb.Schedule;
import javax.ejb.Singleton;



/**
 *
 * @author Mateusz
 */

@Singleton
public class ScheduleTimerBean {
    
  
//        @Schedule(second = "*/5",minute = "*",hour = "*",persistent = false, info = "Every 5 seconds timer")
 public void automaticallyScheduled() {
            System.err.println("test");     
    }


    
}
