/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectclient;

/**
 *
 * @author Lavesh
 */
public class ClientCalculation {

    private double distance;
    private double efficiency;
    private double litterPrice;
    private double tripCost;

    // Constructor with Distance (Double), Fuel Efficiency (Double) & Price of Litter (String)
    ClientCalculation(double distance, double efficiency, String litterPrice) {

        // Sets Distance, based recieved values from parameter
        this.distance = distance;

        // Sets Efficiency, based recieved values from parameter
        this.efficiency = efficiency;

        // Sets litterPrice, based recieved String Value from parameter
        if (litterPrice.equals("98 Octane")) {
            this.litterPrice = 1.03;
        } else {
            this.litterPrice = 1.05;
        }
        // Automatically Calculates Costs
        calculateCost();
    }

    // Calculates Cost of Trip
    private void calculateCost() {
        // Calculation Formula for Trip Cosr
        this.tripCost = (this.distance / (this.efficiency * 3.78541)) * this.litterPrice;

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

}
