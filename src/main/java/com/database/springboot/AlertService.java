package com.database.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional

public class AlertService {
    @Autowired
    private AlertRepo repo;

    public AlertEntity saveAlert(Alert alert){
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setAlert_id(alert.getAlert_id());
        alertEntity.setService_name(alert.getService_name());
        alertEntity.setServiceId(alert.getService_id());
        alertEntity.setAlert_type(alert.getAlert_type());
        alertEntity.setModel(alert.getModel());
        alertEntity.setSeverity(alert.getSeverity());
        alertEntity.setTeam_slack(alert.getTeam_slack());
        alertEntity.setAlertts(new Date(Long.parseLong(alert.getAlert_ts())));
        return repo.save(alertEntity);

    }
    public OutService fetchAlerts(String service_id, String start_ts, String end_ts){
        OutService outService= new OutService();
        List<OutAlert> alerts=new ArrayList<>();
        List<AlertEntity> results = repo.findByAlerttsBetweenAndServiceId(new Date(Long.parseLong(start_ts)*1000), new Date(Long.parseLong(end_ts)*1000),service_id);
        if(null != results && results.size()>0) {
            for(AlertEntity alertEntity : results){
                OutAlert alert= new OutAlert();
                alert.setAlert_id(alertEntity.getAlert_id());
                alert.setAlert_ts(String.valueOf(alertEntity.getAlertts().getTime()));
                alert.setAlert_type(alertEntity.getAlert_type());
                alert.setModel(alertEntity.getModel());
                alert.setSeverity(alertEntity.getSeverity());
                alert.setTeam_slack(alertEntity.getTeam_slack());
                alerts.add(alert);
             }
            outService.setAlerts(alerts);
            outService.setService_id(service_id);
            outService.setService_Name(results.get(0).getService_name());


        }

        return outService;
    }


    public Iterable<AlertEntity> getAllAlerts() {
        // This returns a JSON or XML with the users
        return repo.findAll();
    }
}
