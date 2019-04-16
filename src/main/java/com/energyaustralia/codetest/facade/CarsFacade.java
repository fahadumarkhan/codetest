package com.energyaustralia.codetest.facade;

import com.energyaustralia.codetest.controller.ApiController;
import com.energyaustralia.codetest.model.CarShow;
import com.energyaustralia.codetest.response.CarDTO;
import com.energyaustralia.codetest.service.CarShowProcessorService;
import com.energyaustralia.codetest.service.CarsRestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-16
 * Time: 07:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CarsFacade {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Inject
    private CarsRestClientService carsRestClientService;

    @Inject
    private CarShowProcessorService carShowService;

    public List<CarDTO> getTransformedList() {

        log.info("Accessed method getTransformedList in CarsFacade");

        List<CarShow> carShows = carsRestClientService.getCarShowList();

        List<CarDTO> carDTOS = carShowService.process(carShows);

        return carDTOS;

    }
}
