package com.database.springboot;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void should_create_a_alert() throws Exception{
        String alertId = UUID.randomUUID().toString();
        Alert alert = new Alert();
        alert.setAlert_id(alertId);
        alert.setAlert_ts(String.valueOf(new Date().getTime()));
        alert.setAlert_type("anomaly");
        alert.setSeverity("warning");
        alert.setService_id("my_test_service_id");
        alert.setService_name("my_test_service");
        alert.setModel("my_test_model");
        alert.setTeam_slack("slack_ch");

        OutAlert outAlert = new OutAlert();
        outAlert.setAlert_id(alertId);

        mockMvc.perform(post("/saveAlert").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(alert)))
                .andExpect(status().isOk())
                .equals(outAlert);
    }

    @Test
    public void should_return_alerts() throws Exception{
        MvcResult outServiceResult = mockMvc.perform(get("/alerts?serviceId=my_test_service_id&startts=1696896846&endts=1696896946"))
                .andExpect(status().isOk()).andReturn();
        OutService outService = objectMapper.readValue(outServiceResult.getResponse().getContentAsString(), OutService.class);
        assertEquals("my_test_service_id", outService.getService_id());
        assertEquals("my_test_service", outService.getService_Name());
        assertNotEquals(0,outService.getAlerts().size());
    }
}
