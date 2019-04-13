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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectServer implements Runnable {

    Socket connectionSocket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ProjectServer(Socket connectionSocket, ObjectInputStream in, ObjectOutputStream out) {
        this.connectionSocket = connectionSocket;
        this.in = in;
        this.out = out;
    }

    public static void main(String[] args) {
        // Define Server Port
        final int Port = 6000;

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

                new Thread(new ProjectServer(connectionSocket, in, out)).start();
            }
        } // Handle Input Output Exception
        catch (IOException ex) {
            // Print Exception Text For Admin
            System.out.println("Dear Admin, Other Process are Running in Background. Please close them." + ex);
        } // Handle ClassNotFound Exception
    }

    @Override
    public void run() {
        try {
            // Read Objects from Clients
            CalculationRequest clientInput = (CalculationRequest) this.in.readObject();

            // Get Header from Client Recieved Object
            if (clientInput.getHeader().equals("Calculation Required")) {
                // Run this section if request is to calculate
                // Decleare Input File Location
                FileReader inputFileReader = new FileReader("input.csv");
                // Input Retrieving Process
                BufferedReader fileInput = new BufferedReader(inputFileReader);

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

                StoreList clientProcessing = new StoreList();
                clientProcessing.saveList(clientInput);
                this.out.writeObject(clientInput);
            } // Check if it a Calculation Retrieval Request 
            else if (clientInput.getHeader().equals("Retreival Process")) {
                // Run this section if request is to retrieve previous calculations
                // init fresh StoreList
                StoreList clientProcessing = new StoreList();
                // Retrieve all previous calculations as ArrayList
                clientProcessing.getList();
                // Send this information as object back to client
                this.out.writeObject(clientProcessing);
            }

            // Decleare Input File Location
            FileReader inputFileReader = new FileReader("input.csv");
            // Input Retrieving Process
            BufferedReader fileInput = new BufferedReader(inputFileReader);

            // Close File Reading Process
            fileInput.close();

            // Close Connection with Client
            this.connectionSocket.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
