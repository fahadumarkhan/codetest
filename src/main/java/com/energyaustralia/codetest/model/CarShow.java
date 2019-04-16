package com.energyaustralia.codetest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-14
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarShow {
    @JsonProperty
    private String name;
    @JsonProperty
    private List<Car> cars;

    public CarShow(String name, List<Car> cars) {
        this.name = name;
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
