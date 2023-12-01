package com.example.new_orvba;

public class Mechanic {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String address;
    public boolean tyreServices;
    public boolean engineServices;
    public boolean oilLeakage;
    public boolean carTowing;
    public boolean manualRepairing;
    public boolean other;

    public Mechanic() {
        // Default constructor required for calls to DataSnapshot.getValue(Mechanic.class)
    }

    public Mechanic(String id, String firstName, String lastName, String email, String phone, String address,
                    boolean tyreServices, boolean engineServices, boolean oilLeakage,
                    boolean carTowing, boolean manualRepairing, boolean other) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.tyreServices = tyreServices;
        this.engineServices = engineServices;
        this.oilLeakage = oilLeakage;
        this.carTowing = carTowing;
        this.manualRepairing = manualRepairing;
        this.other = other;
    }
}
