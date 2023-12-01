package com.example.new_orvba;

public class MechanicLocationData {
    public double latitude;
    public double longitude;
    public String address;
    public String city;
    public String country;

    // Add an empty constructor (required by Firebase)
    public MechanicLocationData() {
    }

    // Constructor with parameters
    public MechanicLocationData(double latitude, double longitude, String address, String city, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.country = country;
    }
}

