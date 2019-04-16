package com.energyaustralia.codetest.service;

import com.energyaustralia.codetest.model.Car;
import com.energyaustralia.codetest.model.CarShow;
import com.energyaustralia.codetest.response.CarDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-16
 * Time: 07:51
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CarShowProcessorServiceTest {

    @Test
    public void testProcessWithData() {
        CarShowProcessorService carShowProcessorService = new CarShowProcessorService();

        List<CarShow> sampleCarShows = Arrays.asList(
                new CarShow("Show 1", Arrays.asList(
                        new Car("Make 1", "Model 11"),
                        new Car("Make 2", "Model 21")
                )),
                new CarShow("Show 2", Arrays.asList(
                        new Car("Make 1", "Model 11"),
                        new Car("Make 2", "Model 22")
                )),
                new CarShow("Show 3", Arrays.asList(
                        new Car("Make 3", "Model 31")
                ))
                );

        List<CarDTO> carDTOS = carShowProcessorService.process(sampleCarShows);

        Assert.assertEquals(carDTOS.size(), 3);

        Assert.assertEquals(carDTOS.get(0).getMake(), "Make 1");
        Assert.assertEquals(carDTOS.get(0).getModels().size(), 1);
        Assert.assertEquals(carDTOS.get(0).getModels().get(0).getModel(), "Model 11");
        Assert.assertEquals(carDTOS.get(0).getModels().get(0).getCarShows().size(), 2);
        Assert.assertEquals(carDTOS.get(0).getModels().get(0).getCarShows().get(0), "Show 1");
        Assert.assertEquals(carDTOS.get(0).getModels().get(0).getCarShows().get(1), "Show 2");

        Assert.assertEquals(carDTOS.get(1).getMake(), "Make 2");
        Assert.assertEquals(carDTOS.get(1).getModels().size(), 2);
        Assert.assertEquals(carDTOS.get(1).getModels().get(0).getModel(), "Model 21");
        Assert.assertEquals(carDTOS.get(1).getModels().get(0).getCarShows().size(), 1);
        Assert.assertEquals(carDTOS.get(1).getModels().get(0).getCarShows().get(0), "Show 1");
        Assert.assertEquals(carDTOS.get(1).getModels().get(1).getModel(), "Model 22");
        Assert.assertEquals(carDTOS.get(1).getModels().get(1).getCarShows().size(), 1);
        Assert.assertEquals(carDTOS.get(1).getModels().get(1).getCarShows().get(0), "Show 2");

        Assert.assertEquals(carDTOS.get(2).getMake(), "Make 3");
        Assert.assertEquals(carDTOS.get(2).getModels().size(), 1);
        Assert.assertEquals(carDTOS.get(2).getModels().get(0).getModel(), "Model 31");
        Assert.assertEquals(carDTOS.get(2).getModels().get(0).getCarShows().size(), 1);
        Assert.assertEquals(carDTOS.get(2).getModels().get(0).getCarShows().get(0), "Show 3");
    }

    @Test
    public void testProcessWithNoData() {
        CarShowProcessorService carShowProcessorService = new CarShowProcessorService();

        List<CarShow> sampleCarShows = Collections.emptyList();

        List<CarDTO> carDTOS = carShowProcessorService.process(sampleCarShows);

        Assert.assertEquals(carDTOS.size(), 0);
    }
}
