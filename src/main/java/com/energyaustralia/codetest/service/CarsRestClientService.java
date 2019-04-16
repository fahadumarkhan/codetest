package com.energyaustralia.codetest.service;

import com.energyaustralia.codetest.CodetestApplication;
import com.energyaustralia.codetest.model.CarShow;
import com.energyaustralia.codetest.util.PreConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-15
 * Time: 07:58
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CarsRestClientService {

    private static final Logger log = LoggerFactory.getLogger(CodetestApplication.class);

    @Value("${api.cars.path}")
    private String apiPath;

    @Inject
    private RestTemplate restTemplate;

    public List<CarShow> getCarShowList() throws IllegalStateException, RestClientException {

        CarShow[] carShows = restTemplate.getForObject(apiPath, CarShow[].class);
        log.info("Retrieved Content from API Successfully");

        if(PreConditions.isEmpty(carShows))
            carShows = new CarShow[0];

        return Arrays.asList(carShows);
    }
}
