package com.database.springboot;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "alert")
public class AlertEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Alert_id")
    private String Alert_id;

    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "Service_name")
    private String service_name;
    @Column(name = "model")
    private String model;

    @Column(name = "alert_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date alertts;
    @Column(name = "alert_type")
    private String alert_type;
    @Column(name = "severity")
    private String severity;
    @Column(name = "team_slack")
    private String  team_slack;


    public Date getAlertts() {
        return alertts;
    }

    public void setAlertts(Date alertts) {
        this.alertts = alertts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlert_id() {
        return Alert_id;
    }

    public void setAlert_id(String alert_id) {
        Alert_id = alert_id;
    }


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTeam_slack() {
        return team_slack;
    }

    public void setTeam_slack(String team_slack) {
        this.team_slack = team_slack;
    }

    public String getAlert_type() {
        return alert_type;
    }

    public void setAlert_type(String alert_type) {
        this.alert_type = alert_type;
    }
}
