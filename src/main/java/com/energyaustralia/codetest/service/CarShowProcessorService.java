package com.energyaustralia.codetest.service;

import com.energyaustralia.codetest.controller.ApiController;
import com.energyaustralia.codetest.model.CarShow;
import com.energyaustralia.codetest.response.CarDTO;
import com.energyaustralia.codetest.response.CarModelDTO;
import com.energyaustralia.codetest.response.SimpleCarShowDTO;
import com.energyaustralia.codetest.util.PreConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-15
 * Time: 07:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CarShowProcessorService {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Inject
    private CarsRestClientService carsRestClientService;

    public List<CarDTO> process(List<CarShow> carShows) {
        log.info("Accessed method process in CarShowProcessorService");
        List<SimpleCarShowDTO> simpleCarShowDTOS = carShows.stream().distinct().flatMap(convertToSimpleCarShows()).filter(x -> x!=null).collect(Collectors.toList());
        log.info("List<CarShow> Transformed to List<SimpleCarShowDTO>");

        // Map of Car Make with Map of Car Models and List of Shows
        Map<String, Map<String, List<String>>> plainOrderedContent = simpleCarShowDTOS
                .stream()
                .collect(
                        Collectors.groupingBy(
                                SimpleCarShowDTO::getCarMake,
                                Collectors.groupingBy(
                                        SimpleCarShowDTO::getCarModel,
                                        Collectors.mapping(
                                                SimpleCarShowDTO::getCarShowName,
                                                Collectors.toList()
                                        )
                                )
                        )
                );
        log.info("List<SimpleCarShowDTO> tranformed to Map<String, Map<String, List<String>>>");


        List<CarDTO> carDTOS = plainOrderedContent.entrySet()
                .stream()
                .map(convertToCarDTO())
                .sorted(Comparator.comparing(CarDTO::getMake))
                .collect(Collectors.toList());
        log.info("Map<String, Map<String, List<String>>> transformed into List<CarDTO>");

        return carDTOS;
    }

    /**
     * Accepts CarShow, transforms CarShow to Stream<SimpleCarShowDTO>
     *
     * @return Stream<SimpleCarShowDTO>
     */
    private Function<CarShow, Stream<SimpleCarShowDTO>> convertToSimpleCarShows() {
        return carShow ->
                carShow.getCars().stream().distinct().map(
                        car -> (PreConditions.isNotEmpty(carShow.getName()) && PreConditions.isNotEmpty(car.getModel()) && PreConditions.isNotEmpty(car.getMake())) ? new SimpleCarShowDTO(carShow.getName(), car.getMake(), car.getModel()): null
                );
    }

    /**
     * Accepts Map.Entry<String, Map<String, List<String>> and transforms into CarDTO
     *
     * @return CarDTO
     */
    private Function<Map.Entry<String, Map<String, List<String>>>, CarDTO> convertToCarDTO() {
        return carsByMake -> {

            List<CarModelDTO> carShowModelsResources = carsByMake.getValue()
                    .entrySet()
                    .stream()
                    .map(convertToCarModelDTO())
                    .sorted(
                            Comparator.comparing(
                                    CarModelDTO::getModel
                            )
                    )
                    .collect(Collectors.toList());

            return new CarDTO(carsByMake.getKey(), carShowModelsResources);
        };
    }

    /**
     * Accepts Map.Entry<String, List<String>> and transforms into CarModelDTO
     *
     * @return CarModelDTO
     */
    private Function<Map.Entry<String, List<String>>, CarModelDTO> convertToCarModelDTO() {
        return carsByModel -> {
            List<String> carShows = carsByModel.getValue()
                    .stream()
                    .distinct()
                    .collect(
                            Collectors.toList()
                    );
            Collections.sort(carShows);

            return new CarModelDTO(carsByModel.getKey(), carShows);
        };
    }
}
