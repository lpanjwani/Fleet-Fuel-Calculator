/*
 * ALSET FUEL CALCULATOR PROGRAM (SERVER SIDE)
 * INPUT: 
 *       1) Recieve Input through Client Side via Object
 *       2) Get Fuel Cost via input.csv
 * PROCCESSING: 
 *       1) Calculate Fuel Cost using formula
 * OUTPUT: 
 *       1) Send Response to Client Side via Object
 *       2) Save Calculation Details with Parameters in output.csv
 */
package projectclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Lavesh
 */
public class ProjectServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Define Server Port
        final int Port = 6000;

        // ServerSocket is used for purpose of waiting for a connection from a client
        ServerSocket welcomeSocket = new ServerSocket(Port);

        // Keep Program always in ready state to serve Clients
        while (true) {
            // Once a connection from client is received, the server soocket will accept it
            // which will result in creation of Socket object
            // Socket obejct will be used for sending and receiving data for and to a server
            Socket connectionSocket = welcomeSocket.accept();

            // ObjectInputStream and ObjectOutputStream used for sending and receiving data from and to a client
            ObjectInputStream in = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());

            // Read Objects from Clients
            CalculationRequest clientInput = (CalculationRequest) in.readObject();

            // Decleare Input File Location
            FileReader inputFileReader = new FileReader("input.csv");
            // Input Retrieving Process
            BufferedReader fileInput = new BufferedReader(inputFileReader);

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
            BufferedWriter fileOutput = new BufferedWriter(outputWriter);

            // Write Headers if file does not exist
            if (!fileFound) {
                // Output Headers
                fileOutput.write("Distance" + "," + "Efficiency" + "," + "Price of Each Price" + ","
                        + "Trip Cost" + "\n");
            }

            // Decleare String so it can take a new value everytime Constructor is called.
            String line;

            // While Loop to Gets Each Line
            while ((line = fileInput.readLine()) != null) {
                // Converts cell Information into an Array position when comma is detected.
                String[] rowValues = line.split(",");
                // Cross Checks to find Selected Fuel Type by User & CSV Match.
                if (rowValues[0].equals(clientInput.getLitterInfo())) {
                    // Sends Price of Litter to Client
                    clientInput.calculateCost(Double.parseDouble(rowValues[1]));

                    // Write Values in Output CSV
                    fileOutput.write(clientInput.getDistance() + "," + clientInput.getEfficiency() + "," + clientInput.getLitterPrice() + ","
                            + (String.format("%.2f", clientInput.getCost())) + "\n");

                    // Send Output with Calculation back to Client
                    out.writeObject(clientInput);
                }
            }

            // Close File Reading Process
            fileInput.close();

            // Close File Writing Process
            fileOutput.close();

            // Close Connection with Client
            connectionSocket.close();

        }

    }

}
