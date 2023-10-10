package com.database.springboot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AlertRepo extends CrudRepository<AlertEntity, Integer> {

    List<AlertEntity> findByAlerttsBetweenAndServiceId(Date startDate, Date endDate, String serviceId);




    //List<Test> findAll();


}