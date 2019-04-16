package com.energyaustralia.codetest.response;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-15
 * Time: 07:27
 * To change this template use File | Settings | File Templates.
 */
public class SimpleCarShowDTO {
    private String carShowName;
    private String carMake;
    private String carModel;

    public SimpleCarShowDTO(String carShowName, String carMake, String carModel) {
        this.carShowName = carShowName;
        this.carMake = carMake;
        this.carModel = carModel;
    }

    public String getCarShowName() {
        return carShowName;
    }

    public void setCarShowName(String carShowName) {
        this.carShowName = carShowName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
