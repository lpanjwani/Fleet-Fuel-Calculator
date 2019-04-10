/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectclient;

import java.util.ArrayList;

/**
 *
 * @author Lavesh
 */
public class StoreList extends CalculationRequest {

    ArrayList<CalculationRequest> storeArray;

    public StoreList(double distance, double efficiency, String fuelType) {
        super(distance, efficiency, fuelType);
        this.storeArray = new ArrayList<>();
    }

    public ArrayList getList() {
        return this.storeArray;
    }

    public void saveList(CalculationRequest calculation) {
        this.storeArray.add(calculation);
    }

}
