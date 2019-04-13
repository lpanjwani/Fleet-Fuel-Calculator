/*
 * This Class Extends Calcution Request
 * Stores Calculation in ArrayList which is stored in a file
 */
package projectclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// StoreList Class Inherits Properties from CalculationRequest
public class StoreList extends CalculationRequest {

    // Defines ArrayList Type & Name
    ArrayList<CalculationRequest> storeArray;
    // Data Store File Location
    private static String DATA_STORE;
//    private static final long serialVersionUID = 6529685098267757690L;

    // init new Store ArrayList
    public StoreList(String storeFileName) throws IOException, FileNotFoundException, ClassNotFoundException {
        // clear all exisiting Information to avoid any problems
        this.storeArray = new ArrayList<CalculationRequest>();
        this.DATA_STORE = storeFileName + "_DB.dat";
    }

    // Retrives ArrayList Containing All Calculations from Data Store
    public void getList() throws FileNotFoundException, IOException, ClassNotFoundException {
        // Defines dataStore location & accessing Method
        FileInputStream fileInput = new FileInputStream(this.DATA_STORE);
        // init Object to file proccesor
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        // Gets all Information in File & Casts them to ArrayList of CalculationRequests
        this.storeArray = (ArrayList<CalculationRequest>) objectInput.readObject();
        // Close File Input Accessors
        objectInput.close();
    }

    // Saves ArrayList Containing All Old Calculations & New Calculations to Data Store
    public void saveList(CalculationRequest calculation) throws FileNotFoundException, IOException {
        // Try Catch for Multiple Errors Handling
        try {
            // Retrieve Existing List from Data Store
            getList();

        } // Handle All Exception Occuring
        catch (Exception ex) {
            // No Action Required here
        }
        // Starting Output Accessors & Output Location
        FileOutputStream fileOutput = new FileOutputStream(this.DATA_STORE);
        // Start Object to File Conversion Accessors
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

        // Add new Calculation with Properties to Temporary Local Data Store
        this.storeArray.add(calculation);

        // Update File Data to include Latest Calculation
        objectOutput.writeObject(this.storeArray);
        // Close File Output Accessors
        objectOutput.close();
    }

    // Display CalculationRequests Information to User via String
    public String showList() {
        // init Blank String for temporary Display
        String stringContent = "";
        // Loop runs Dynamically based on how many Requests are stored in file
        for (CalculationRequest cal : this.storeArray) {
            // Add Calculation Information for Display
            stringContent += cal.toString();
        }
        // Respond with all Calculation Information
        return stringContent;
    }
}
