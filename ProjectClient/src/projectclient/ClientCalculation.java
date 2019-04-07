/*
 * ClientCalculation as the name suggests handles all Client-Based Calculations
 * Common Variables:
 * 1) Distance (Double)
 * 2) Car's Fuel Effiency (Double)
 * 3) Price of a Litter (Double)
 * 4) Trip Cost (Calculated || Double)
 */
package projectclient;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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
        // Server Name & Port for Socket Connection / Could be changed easily for dynamic environments
        String serverName = "localhost";
        int port = 6000;

        // Start Socket Connection at serverName & Port specified above
        Socket client = new Socket(serverName, port);

        // Send Data through OutputStream & DataOutStream (String Purposes)
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        // Send Fuel Selection to Server
        out.writeUTF(fuelType);

        // Retrieve data through InputStream & DataInputStream (String Purposes)
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);

        // Recieve Fuel Price on Server & Store in local variable
        this.litterPrice = Double.parseDouble(in.readUTF());

        // Close Connection with Server
        client.close();

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
        if (Double.isInfinite(this.tripCost) || Double.isNaN(this.tripCost) || this.tripCost < 0) {
            throw new ArithmeticException();
        }
        // Starts process of output
        File outputFile = new File("output.csv");

        // File Exists Indicator
        boolean fileFound = false;

        // Check if Output File Exists to confirm Headers Exists
        if (outputFile.exists()) {
            // Set File Found Indicator
            fileFound = true;
        }

        // Decleare Output File Location
        FileWriter outputWriter = new FileWriter(outputFile, true);

        // Output Writing Process
        BufferedWriter output = new BufferedWriter(outputWriter);

        // Write Headers if file does not exist
        if (!fileFound) {
            // Output Headers
            output.write("Distance" + "," + "Efficiency" + "," + "Price of Each Price" + ","
                    + "Trip Cost" + "\n");
        }

        // Write Values in CSV
        output.write(this.distance + "," + this.efficiency + "," + this.litterPrice + ","
                + (String.format("%.2f", this.tripCost)) + "\n");

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
