/*
 * Contains Information Required by Server for Calculation Requests 
 * Common Variables:
 * 1) Distance (Double)
 * 2) Car's Fuel Effiency (Double)
 * 3) Price of a Litter (Double)
 * 4) Trip Cost (Calculated || Double)
 */
package projectclient;

import java.io.Serializable;

/**
 *
 * @author Lavesh
 */
public class CalculationRequest implements Serializable {

    private double distance;
    private double efficiency;
    private double litterPrice;
    private double tripCost;
    private String litterInfo;

    // Constructor with Distance (Double), Fuel Efficiency (Double) & Price of Litter (String)
    CalculationRequest(double distance, double efficiency, String fuelType) {

        // Sets Distance, based recieved values from parameter
        this.distance = distance;

        // Sets Efficiency, based recieved values from parameter
        this.efficiency = efficiency;

        // Sets Fuel Type Information for Price Calculation
        this.litterInfo = fuelType;

    }

    // Calculates Cost of Trip
    public void calculateCost(double fuelCost) {

        this.litterPrice = fuelCost;

        // Mathematical Formula to Calulcate Cost of Trip
        this.tripCost = (this.distance / (this.efficiency * * 3.78541)) * this.litterPrice;

        // Check for Arithmetic Exceptions such as Division by 0, etc
        if (Double.isInfinite(this.tripCost) || Double.isNaN(this.tripCost) || this.tripCost < 0) {
            throw new ArithmeticException();
        }

    }

    // Sends Back Distance
    public double getDistance() {
        return this.distance;
    }

    // Sends Back Efficiency
    public double getEfficiency() {
        return this.efficiency;
    }

    // Sends Back Price of Litter
    public double getLitterPrice() {
        return this.litterPrice;
    }

    // Sends Back Cost of Trip
    public double getCost() {
        return this.tripCost;
    }

// Sends Litter Information (String)
    public String getLitterInfo() {
        return this.litterInfo;
    }

}
