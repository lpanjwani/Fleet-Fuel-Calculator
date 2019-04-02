/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    ClientCalculation(double distance, double efficiency, String fuelType) throws FileNotFoundException, IOException {

        // Decleare Input File Location
        FileReader inputFile = new FileReader("input.csv");
        // Input Retrieving Process
        BufferedReader input = new BufferedReader(inputFile);

        // Decleare String so it can take a new value everytime Constructor is called.
        String line;

        // While Loop to Gets Each Line
        while ((line = input.readLine()) != null) {
            // Converts cell Information into an Array position when comma is detected.
            String[] rowValues = line.split(",");
            // Cross Checks to find Selected Fuel Type by User & CSV Match.
            if (rowValues[0].equals(fuelType)) {
                // Stores the Price of a Litter
                this.litterPrice = Double.parseDouble(rowValues[1]);
            }
        }

        // Close File Reading Function
        input.close();

        // Sets Distance, based recieved values from parameter
        this.distance = distance;

        // Sets Efficiency, based recieved values from parameter
        this.efficiency = efficiency * 3.78541;

        // Automatically Calculates Costs
        calculateCost();
    }

    // Calculates Cost of Trip
    private void calculateCost() throws IOException {
        // Mathematical Formula to Calulcate Cost of Trip
        this.tripCost = (this.distance / this.efficiency) * this.litterPrice;

        // Check for Arithmetic Exceptions such as Division by 0, etc
        if (Math.abs(this.tripCost = 1 / this.tripCost) < Double.POSITIVE_INFINITY) {
            throw new ArithmeticException();
        }

        // Decleare Output File Location
        FileWriter outputFile = new FileWriter("output.csv", true);

        // Output Writing Process
        BufferedWriter output = new BufferedWriter(outputFile);

        // Write Values in CSV
        output.write(this.distance + "," + this.efficiency + "," + this.litterPrice + ","
                + Double.toString(this.tripCost) + "\n");

        // Close Writer Process
        output.close();

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
