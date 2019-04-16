package com.energyaustralia.codetest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-15
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */
public class CarDTO {

    @JsonProperty
    private String make;
    @JsonProperty
    private List<CarModelDTO> models;

    public CarDTO(String make, List<CarModelDTO> models) {
        this.make = make;
        this.models = models;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public List<CarModelDTO> getModels() {
        return models;
    }

    public void setModels(List<CarModelDTO> models) {
        this.models = models;
    }
}
