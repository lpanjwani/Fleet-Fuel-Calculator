/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Lavesh
 */
public class StoreList extends CalculationRequest {

    ArrayList<CalculationRequest> storeArray;
    
    public StoreList() {
        this.storeArray = new ArrayList<>();
    }

    public StoreList(double distance, double efficiency, String fuelType) {
        super(distance, efficiency, fuelType);
        this.storeArray = new ArrayList<>();
    }

    public ArrayList getList() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("storeData.dat");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        System.out.println(objectInput.readObject());
        this.storeArray = (ArrayList<CalculationRequest>) objectInput.readObject();
        System.out.println(this.storeArray);
        objectInput.close();
        return this.storeArray;
    }

    public void saveList(CalculationRequest calculation) throws FileNotFoundException, IOException {
        this.storeArray.add(calculation);
        FileOutputStream fileOutput = new FileOutputStream("storeData.dat");
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(this.storeArray);
        objectOutput.close();
    }

    // Sends Back Distance
    @Override
    public double getDistance() {
        return super.getDistance();
    }

    // Sends Back Efficiency
    @Override
    public double getEfficiency() {
        return super.getEfficiency();
    }

    // Sends Back Price of Litter
    @Override
    public double getLitterPrice() {
        return super.getLitterPrice();
    }

    // Sends Back Cost of Trip
    @Override
    public double getCost() {
        return super.getCost();
    }

    // Sends Litter Information (String)
    @Override
    public String getLitterInfo() {
        return super.getLitterInfo();
    }

}
