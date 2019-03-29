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

    private final double distance;
    private final double efficiency;
    private final double litterPrice;
    private double tripCost;

    ClientCalculation(double distance, double efficiency, double litterPrice) {
        this.distance = distance;
        this.efficiency = efficiency;
        this.litterPrice = litterPrice;
    }

    public void calculateCost() {
        this.tripCost = (this.distance / this.efficiency) * this.litterPrice;
    }

    public double getDistance() {
        return this.distance;
    }

    public double getEfficiency() {
        return this.efficiency;
    }

    public double getLitterPrice() {
        return this.litterPrice;
    }

    public double getCost() {
        return this.tripCost;
    }

}
