/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_interfaces;

import javax.ejb.Remote;

/**
 *
 * @author viter
 */
@Remote
public interface HelloBeanRemote {
    
    public String getHour();
    public String getMinutes();
    public String getShift();
    public String getDay();
    public String getMonth();
    public String getYear();
    public String getWeekDay();
    
}
