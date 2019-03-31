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

    ClientCalculation(double distance, double efficiency, String litterPrice) {
        this.distance = distance;
        this.efficiency = efficiency;
        if (litterPrice.equals("98 Octane")) {
            this.litterPrice = 1.03;
        } else {
            this.litterPrice = 1.05;
        }
        calculateCost();
    }

    private void calculateCost() {
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
