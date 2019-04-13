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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProjectServer implements Runnable {

    Socket connectionSocket;
    ObjectInputStream in;
    ObjectOutputStream out;

    // Constructor that sets information rquired by Thread
    public ProjectServer(Socket connectionSocket, ObjectInputStream in, ObjectOutputStream out) {
        this.connectionSocket = connectionSocket;
        this.in = in;
        this.out = out;
    }

    // Runs when Server is in Booting Process
    public static void main(String[] args) {
        // Define Server Port
        final int Port = 6000;

        // Show Admin that Server is up & Running
        System.out.println("Server Started!");

        try {
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

                // Start New Thread to service this user
                new Thread(new ProjectServer(connectionSocket, in, out)).start();
            }
        } // Handle Input Output Exception
        catch (IOException ex) {
            // Print Exception Text For Admin
            System.out.println("Dear Admin, Other Process are Running in Background. Please close them." + ex);
        }
    }

    @Override
    public void run() {
        try {
            // Read Objects from Clients
            CalculationRequest clientInput = (CalculationRequest) this.in.readObject();

            // init fresh StoreList
            StoreList clientProcessing = new StoreList(this.connectionSocket.getInetAddress().toString().substring(1));

            // Get Header from Client Recieved Object
            if (clientInput.getHeader().equals("Calculation Required")) {
                // Run this section if request is to calculate
                // Decleare Input File Location
                FileReader inputFileReader = new FileReader("input.csv");
                // Input Retrieving Process
                BufferedReader fileInput = new BufferedReader(inputFileReader);

                // init Clean String
                String line;

                // While Loop to Gets Each Line
                while ((line = fileInput.readLine()) != null) {
                    // Converts cell Information into an Array position when comma is detected.
                    String[] rowValues = line.split(",");
                    // Cross Checks to find Selected Fuel Type by User & CSV Match.
                    if (rowValues[0].equals(clientInput.getLitterInfo())) {
                        // Sends Price of Litter to Client
                        clientInput.calculateCost(Double.parseDouble(rowValues[1]));
                    }
                }

                // Close File Reading Process
                fileInput.close();

                // Save Information in File Storage
                clientProcessing.saveList(clientInput);
                
                // Send Back Information to Client
                this.out.writeObject(clientInput);
            } // Check if it a Calculation Retrieval Request 
            else if (clientInput.getHeader().equals("Retreival Process")) {
                // Run this section if request is to retrieve previous calculations
                // Retrieve all previous calculations as ArrayList
                try {
                    clientProcessing.getList();
                } // Handle FileNotFound Expections
                catch (FileNotFoundException ex) {
                    // Do Nothing
                }
                // Send this information as object back to client
                this.out.writeObject(clientProcessing);
            }

            // Close Connection with Client
            this.connectionSocket.close();
        } // Handle Input Output Exception
        catch (IOException ex) {
            // Print Exception Text For Admin
            System.out.println("Dear Admin, Other Process are Running in Background. Please close them." + ex);
        } // Handle ClassNotFound Exception 
        catch (ClassNotFoundException ex) {
            // Print Exception Text For Admin
            System.out.println("Dear Admin, Class is not found" + ex);
        }
    }
}
