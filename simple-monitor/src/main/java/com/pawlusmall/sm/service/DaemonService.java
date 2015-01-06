package com.pawlusmall.sm.service;

import com.pawlusmall.sm.model.Application;
import com.pawlusmall.sm.model.Method;
import com.pawlusmall.sm.model.PingMethod;
import com.pawlusmall.sm.model.ResponseRegexMethod;
import com.pawlusmall.sm.model.Status;
import com.pawlusmall.sm.model.StatusCheck;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.joda.time.Period;

/**
 *
 * @author pawlusmall
 */
@Singleton
public class DaemonService implements Serializable {
    
    @Inject
    private ApplicationService applicationService;
    @Inject
    private StatusCheckService statusCheckService;
    
    @Schedule(second="*/5", minute="*",hour="*", persistent=false)
    public void daemon() {
        List<Application> applications = applicationService.findAll();
        for(Application a : applications) {
            StatusCheck lastStatus = statusCheckService.getLastStatusCheck(a);
            if(lastStatus == null || isPeriodExpired(a, lastStatus)) {
                StatusCheck status = new StatusCheck();
                status.setApplication(a);
                status.setDate(new Date());
                status.setStatus(isApplicationUp(a) ? Status.UP : Status.DOWN);
                statusCheckService.addStatusCheck(status);
                System.out.println("Creando Status para "+a.getName());
            }
        }
    }
    
    private boolean isPeriodExpired(Application a, StatusCheck lastStatus) {
        Period p = new Period(lastStatus.getDate().getTime(), new Date().getTime());
        System.out.println("minutes: " + p.getMinutes());
        return p.getMinutes() >= a.getPeriod();
    }
    
    private boolean isApplicationUp(Application a) {
        if(a.getMethod().getMethod().equals(Method.PING)) {
            PingMethod pm = (PingMethod) a.getMethod();
            return isHostReachable(pm.getIp());
        } else if(a.getMethod().getMethod().equals(Method.RESPONSE_REGEX)) {
            ResponseRegexMethod rm = (ResponseRegexMethod) a.getMethod();
            return isRegexMatched(rm.getUrl(), rm.getRegex());
        }
        return false;
    }
    
    private boolean isHostReachable(String host) {
        try {
            String[] ipArray = host.split("\\.");
            if(ipArray.length != 4) {
                return false;
            }
            byte [] ip = new byte[] {Byte.parseByte(ipArray[0]), Byte.parseByte(ipArray[1]), Byte.parseByte(ipArray[2]), Byte.parseByte(ipArray[3])};
            InetAddress inet = InetAddress.getByAddress(ip);
            return inet.isReachable(10000); 
        } catch (IOException | NumberFormatException ex) {
            return false;
        }
    }
    
    private boolean isRegexMatched(String url, String regex) {
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)u.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
               sb = sb.append(line);
            }
            br.close();
            return sb.toString().matches(regex);
        } catch (IOException ex) {
            return false;
        }
    }
    
}
