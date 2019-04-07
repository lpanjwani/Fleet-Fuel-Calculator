/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Lavesh
 */
public class ProjectServer {

    public static void main(String[] args) throws IOException, FileNotFoundException, IOException {

        // Define Server Port
        int Port = 6000;

        // ServerSocket is used for purpose of waiting for a connection from a client
        ServerSocket welcomeSocket = new ServerSocket(Port);

        // Keep Program always in ready state to serve Clients
        while (true) {
            // Once a connection from client is received, the server soocket will accept it
            // which will result in creation of Socket object
            // Socket obejct will be used for sending and receiving data for and to a server
            Socket connectionSocket = welcomeSocket.accept();

            // ObjectInputStream and ObjectOutputStream used for sending and receiving data from and to a client
            DataInputStream in = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(connectionSocket.getOutputStream());

            // Read String from Clients
            String clientInput = in.readUTF();

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
                if (rowValues[0].equals(clientInput)) {
                    // Sends Price of Litter to Client
                    out.writeUTF(rowValues[1]);
                }
            }

            // Close File Reading Function
            input.close();

            // Close Connection with Client
            connectionSocket.close();

        }

    }
}
