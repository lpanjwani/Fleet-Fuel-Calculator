/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectclient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Lavesh
 */
public class ProjectClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        Label companyName = new Label("ALSET");
        companyName.getStyleClass().add("companyName");
        GridPane.setConstraints(companyName, 2, 0);
        GridPane.setHalignment(companyName, HPos.CENTER);

        Label programDescription = new Label("Fuel Cost Calculator");
        programDescription.getStyleClass().add("programDescription");
        GridPane.setConstraints(programDescription, 2, 1);
        GridPane.setHalignment(programDescription, HPos.LEFT);

        Label distanceLabel = new Label("Distance: ");
        distanceLabel.getStyleClass().add("InputLabels");
        GridPane.setConstraints(distanceLabel, 1, 3);
        GridPane.setHalignment(distanceLabel, HPos.RIGHT);

        TextField distanceInput = new TextField();
        distanceInput.getStyleClass().add("InputFields");
        GridPane.setConstraints(distanceInput, 2, 3);
        GridPane.setHalignment(distanceInput, HPos.CENTER);

        Label distanceUnitLabel = new Label(" Miles");
        distanceUnitLabel.getStyleClass().add("InputLabels");
        GridPane.setConstraints(distanceUnitLabel, 3, 3);
        GridPane.setHalignment(distanceUnitLabel, HPos.LEFT);

        Label fuelEffieciencyLabel = new Label("Fuel Efficiency: ");
        fuelEffieciencyLabel.getStyleClass().add("InputLabels");
        GridPane.setConstraints(fuelEffieciencyLabel, 1, 4);
        GridPane.setHalignment(fuelEffieciencyLabel, HPos.RIGHT);

        TextField fuelEffieciencyInput = new TextField();
        fuelEffieciencyInput.getStyleClass().add("InputFields");
        GridPane.setConstraints(fuelEffieciencyInput, 2, 4);
        GridPane.setHalignment(fuelEffieciencyInput, HPos.CENTER);

        Label fuelUnitLabel = new Label(" Miles per Gallon (MPG)");
        fuelUnitLabel.getStyleClass().add("InputLabels");
        GridPane.setConstraints(fuelUnitLabel, 3, 4);
        GridPane.setHalignment(fuelUnitLabel, HPos.LEFT);

        Label fuelTypeLabel = new Label("Fuel Type: ");
        fuelTypeLabel.getStyleClass().add("InputLabels");
        GridPane.setConstraints(fuelTypeLabel, 1, 5);
        GridPane.setHalignment(fuelTypeLabel, HPos.RIGHT);

        final ToggleGroup fuelType = new ToggleGroup();

        RadioButton Octane = new RadioButton("98 Octane");
        Octane.setToggleGroup(fuelType);
        Octane.setSelected(true);
        GridPane.setMargin(Octane, new Insets(0,0,0,10));
        GridPane.setConstraints(Octane, 2, 5);

        RadioButton Diesel = new RadioButton("Diesel");
        Diesel.setToggleGroup(fuelType);
        GridPane.setMargin(Diesel, new Insets(0,0,0,-450));
        GridPane.setConstraints(Diesel, 3, 5);

        TextArea Results = new TextArea();
        Results.setEditable(false);

        GridPane.setConstraints(Results, 2, 8);
        

        Button calculate = new Button("Calculate");
        calculate.getStyleClass().add("CalculateButton");
        GridPane.setMargin(calculate, new Insets(10,0,10,0));
        

        
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ClientCalculation values = new ClientCalculation(Double.parseDouble(distanceInput.getText()), Double.parseDouble(fuelEffieciencyInput.getText()), fuelType.getSelectedToggle().toString().split("'")[1]);
                    Results.setText(Results.getText() + "£" + (String.format("%.2f",values.getCost())) + "\n");
                } catch (NumberFormatException ex) {
//                    Results.setText(Results.getText() + "Please Enter Only Numbers! " + "\n");
                    Alert expectionAlert = new Alert(AlertType.ERROR,"Please Enter Only Numbers!");
                    expectionAlert.showAndWait();
                }
            }
        });
        GridPane.setConstraints(calculate, 2, 7);
        root.getChildren().addAll(companyName, programDescription, distanceLabel, distanceInput, distanceUnitLabel, fuelEffieciencyLabel, fuelEffieciencyInput, fuelUnitLabel, fuelTypeLabel, Octane, Diesel, calculate, Results);

        Scene scene = new Scene(root, 1000, 600);
        
        root.getStylesheets().add(getClass().getResource("ui.css").toString());
        
        primaryStage.setTitle("ALSET Fuel Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
