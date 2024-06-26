package com.example.new_orvba;
import android.location.Location;

        import java.util.ArrayList;
        import java.util.List;

public class NearbyMechanics {

    // Example list of mechanics
    private List<MechanicLocationData> mechanics;

    public NearbyMechanics() {
        // Initialize the list of mechanics (replace this with your actual list)
        mechanics = new ArrayList<>();
        mechanics.add(new MechanicLocationData(37.7749, -122.4194, "Mechanic 1", "City 1", "Country 1"));
        mechanics.add(new MechanicLocationData(34.0522, -118.2437, "Mechanic 2", "City 2", "Country 2"));
        // Add more mechanics as needed
    }

    public List<MechanicLocationData> getNearbyMechanics(double userLatitude, double userLongitude, double maxDistance) {
        List<MechanicLocationData> nearbyMechanics = new ArrayList<>();

        // User location
        Location userLocation = new Location("User");
        userLocation.setLatitude(userLatitude);
        userLocation.setLongitude(userLongitude);

        for (MechanicLocationData mechanic : mechanics) {
            // Mechanic location
            Location mechanicLocation = new Location("Mechanic");
            mechanicLocation.setLatitude(mechanic.latitude);
            mechanicLocation.setLongitude(mechanic.longitude);

            // Calculate distance using Haversine formula
            float distance = userLocation.distanceTo(mechanicLocation);

            // Check if the mechanic is within the specified distance
            if (distance <= maxDistance) {
                nearbyMechanics.add(mechanic);
            }
        }

        return nearbyMechanics;
    }
}
