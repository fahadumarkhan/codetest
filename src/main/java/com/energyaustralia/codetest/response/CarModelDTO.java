package com.energyaustralia.codetest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-15
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
public class CarModelDTO {
    @JsonProperty
    private String model;
    @JsonProperty
    private List<String> carShows;

    public CarModelDTO(String model, List<String> carShows) {
        this.model = model;
        this.carShows = carShows;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<String> getCarShows() {
        return carShows;
    }

    public void setCarShows(List<String> carShows) {
        this.carShows = carShows;
    }
}
