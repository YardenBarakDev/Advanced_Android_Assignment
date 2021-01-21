package com.bawp.common;


import java.util.List;

public class GarageInfo {

    private List<String> Cars;
    private boolean open = true;
    private String address = "";
    private String name = "";


    public GarageInfo(List<String> cars, boolean open, String address, String name) {
        Cars = cars;
        this.open = open;
        this.address = address;
        this.name = name;
    }

    public GarageInfo() {

    }

    public List<String> getCars() {
        return Cars;
    }

    public GarageInfo setCars(List<String> cars) {
        Cars = cars;
        return this;
    }

    public boolean isOpen() {
        return open;
    }

    public GarageInfo setOpen(boolean open) {
        this.open = open;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public GarageInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public GarageInfo setName(String name) {
        this.name = name;
        return this;
    }
}
