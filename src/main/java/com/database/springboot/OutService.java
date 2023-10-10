package com.database.springboot;

import java.util.List;

public class OutService {
    private String service_id;
    private String service_Name;

    private List<OutAlert> alerts;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_Name() {
        return service_Name;
    }

    public void setService_Name(String service_Name) {
        this.service_Name = service_Name;
    }

    public List<OutAlert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<OutAlert> alerts) {
        this.alerts = alerts;
    }
}
