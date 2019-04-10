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
import java.util.ArrayList;

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

            String selectedOption = in.readUTF();
            
            if (selectedOption.equals("Calculation Required")) {
                // Read Objects from Clients
                CalculationRequest clientInput = (CalculationRequest) in.readObject();
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
                out.writeObject(clientInput);
            } else if (selectedOption.equals("Retreival Process")) {
                StoreList clientProcessing = new StoreList();
                ArrayList<CalculationRequest> arrayList = (ArrayList<CalculationRequest>) clientProcessing.getList();
//                for (CalculationRequest c : arrayList) {
//                    clientProcessing.saveList(c);
//                }
                out.writeObject(clientProcessing);
            }

            // Decleare Input File Location
            FileReader inputFileReader = new FileReader("input.csv");
            // Input Retrieving Process
            BufferedReader fileInput = new BufferedReader(inputFileReader);

            // Close File Reading Process
            fileInput.close();

            // Close Connection with Client
            connectionSocket.close();

        }

    }

}
