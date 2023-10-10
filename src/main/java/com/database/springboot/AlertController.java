package com.database.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AlertController {


    @Autowired
    private AlertService alertService;



    @PostMapping("/saveAlert")
    public Object saveAlert(
            @RequestBody Alert alert) {
        if (alert != null) {
            boolean isValidReq = !alert.getAlert_id().isEmpty() &&
                    !alert.getAlert_ts().isEmpty() &&
                    !alert.getAlert_type().isEmpty() &&
                    !alert.getModel().isEmpty() &&
                    !alert.getService_id().isEmpty() &&
                    !alert.getSeverity().isEmpty() &&
                    !alert.getService_name().isEmpty() &&
                    !alert.getTeam_slack().isEmpty();
            if (isValidReq) {
                AlertEntity alert1;
                try{  alert1 = alertService.saveAlert(alert);}
               catch(Exception e){
                   Map<String, Object> object = new HashMap<>();
                   object.put("alert_id", alert.getAlert_id());
                   object.put("error", e.getMessage());
                   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(object);
               }
                Map<String, Object> object = new HashMap<>();
                object.put("alert_id", alert.getAlert_id());
                object.put("error", "");
                return ResponseEntity.ok(object);
            } else {
                Map<String, Object> object = new HashMap<>();
                object.put("alert_id", alert.getAlert_id());
                object.put("error", "<error details>");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(object);
            }

        }
        return null;
    }

    @GetMapping(value = "alerts")
    public OutService getAlertsOfService(@RequestParam String serviceId, @RequestParam String startts, @RequestParam String endts){
        return alertService.fetchAlerts(serviceId,startts,endts);

    }

}

