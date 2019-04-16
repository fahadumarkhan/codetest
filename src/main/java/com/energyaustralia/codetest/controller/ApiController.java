package com.energyaustralia.codetest.controller;

import com.energyaustralia.codetest.CodetestApplication;
import com.energyaustralia.codetest.facade.CarsFacade;
import com.energyaustralia.codetest.response.CarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-14
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Inject
    private CarsFacade carsFacade;

    @RequestMapping("/api/cars")
    @ResponseBody
    public List<CarDTO> test() throws Exception{

        log.info("Cars API Accessed");
        return carsFacade.getTransformedList();
    }


}
