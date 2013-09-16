package be.kuleuven.cs.gridlock.util.geo;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import be.kuleuven.cs.gridlock.geo.coordinates.calculations.DistanceCalculator;

/**
 *
 * @author Kristof Coninx <kristof.coninx at student.kuleuven.be>
 */
public class SimpleDistanceCalculator implements DistanceCalculator{

    @Override
    public double calculateDistance(Coordinates origin, Coordinates destination) {
        return Math.sqrt(Math.pow(destination.getLatitude()-origin.getLatitude(),2) +
                Math.pow(destination.getLongitude()-origin.getLongitude(),2));
    }
    
}
