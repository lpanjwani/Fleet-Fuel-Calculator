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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
        distanceInput.getStyleClass().add("InputFields");
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
        GridPane.setConstraints(Octane, 2, 5);

        RadioButton Diesel = new RadioButton("Diesel");
        Diesel.setToggleGroup(fuelType);
        GridPane.setConstraints(Diesel, 2, 6);

        Button calculate = new Button("Calculate");
        calculate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//                ClientCalculation values = ClientCalculation(distanceInput.getText(),fuelEffieciencyInput.getText());
            }
        });
        GridPane.setConstraints(calculate, 2, 7);

        root.getChildren().addAll(companyName, programDescription, distanceLabel, distanceInput, distanceUnitLabel, fuelEffieciencyLabel, fuelEffieciencyInput, fuelUnitLabel, fuelTypeLabel, Octane, Diesel,calculate);

        Scene scene = new Scene(root, 600, 550);

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
