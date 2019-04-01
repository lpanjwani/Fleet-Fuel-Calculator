/*
 * ALSET FUEL CALCULATOR PROGRAM
 * INPUT: 
 *       1) Trip distance in miles (TextField)
 *       2) Car’s Fuel Efficiency 
 *       3) Cost of Fuel Per Litter (RadioButton)
 * OUTPUT: 
 *       1) Fuel Cost for Trip (Non-Editable TextArea)
 * COMPONENTS:
 *       JAVAFX - Scene, Stage, GridPane ,HPos, Insets, Alert, Button, Label, TextArea, ToggleGroup, RadioButton, EventHandler, ActionEvent
 */
package projectclient;

// Import Java Libaries
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Lavesh
 */
// ProjectClient Class handles JAVAFX Client Side. ProjectClient 'extends' Application Class for JAVAFX Library.
public class ProjectClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Declare GridPane 'root'
        GridPane root = new GridPane();

        // Declare new label for Company Name & Give Text 'ALSET'
        Label companyName = new Label("ALSET");
        // Give CSS Properties with Class .companyName to Company Name Label
        companyName.getStyleClass().add("companyName");
        // Give Row 0 & Column 2 to Company Name Label via GridPane  
        GridPane.setConstraints(companyName, 2, 0);
        // Instruct GridPanel to give Center Horizontal Alignment with HPos
        GridPane.setHalignment(companyName, HPos.CENTER);

        // Declare new label for Program Description & Give Text 'Fuel Cost Calculator'
        Label programDescription = new Label("Fuel Cost Calculator");
        // Give CSS Properties with Class .programDescription to Program Description Label
        programDescription.getStyleClass().add("programDescription");
        // Give Row 1 & Column 2 to programDescription Label via GridPane
        GridPane.setConstraints(programDescription, 2, 1);
        // Instruct GridPanel to give Left Horizontal Alignment with HPos
        GridPane.setHalignment(programDescription, HPos.LEFT);

        // Declare new label for Distance & Give Text 'Distance'
        Label distanceLabel = new Label("Distance: ");
        // Give CSS Properties with Class .InputLabels to InputLabels Label    
        distanceLabel.getStyleClass().add("InputLabels");
        // Give Row 3 & Column 1 to programDescription Label via GridPane
        GridPane.setConstraints(distanceLabel, 1, 3);
        // Instruct GridPanel to give Right Horizontal Alignment with HPos
        GridPane.setHalignment(distanceLabel, HPos.RIGHT);

        // Declare new TextField for distanceInput & Give Text
        TextField distanceInput = new TextField();
        // Give CSS Properties with Class .InputFields to InputFields TextField 
        distanceInput.getStyleClass().add("InputFields");
        // Give Row 3 & Column 2 to distanceInput TextField via GridPane
        GridPane.setConstraints(distanceInput, 2, 3);
        // Instruct GridPanel to give Center Horizontal Alignment with HPos
        GridPane.setHalignment(distanceInput, HPos.CENTER);

        // Declare new label for distanceUnitLabel & Give Text 'Miles'
        Label distanceUnitLabel = new Label("Miles");
        // Give CSS Properties with Class .InputLabels to distanceUnitLabel Label 
        distanceUnitLabel.getStyleClass().add("InputLabels");
        // Give Row 3 & Column 3 to distanceUnitLabel Label via GridPane
        GridPane.setConstraints(distanceUnitLabel, 3, 3);
        // Instruct GridPanel to give Left Horizontal Alignment with HPos
        GridPane.setHalignment(distanceUnitLabel, HPos.LEFT);

        // Declare new label for fuelEffieciencyLabel & Give Text 'Fuel Efficiency'
        Label fuelEffieciencyLabel = new Label("Fuel Efficiency: ");
        // Give CSS Properties with Class .InputLabels to fuelEffieciencyLabel Label 
        fuelEffieciencyLabel.getStyleClass().add("InputLabels");
        // Give Row 4 & Column 1 to distanceUnitLabel Label via GridPane
        GridPane.setConstraints(fuelEffieciencyLabel, 1, 4);
        // Instruct GridPanel to give Left Right Alignment with HPos
        GridPane.setHalignment(fuelEffieciencyLabel, HPos.RIGHT);

        // Declare new TextField for fuelEffieciencyInput & Give Text
        TextField fuelEffieciencyInput = new TextField();
        // Give CSS Properties with Class .InputFields to fuelEffieciencyInput TextField 
        fuelEffieciencyInput.getStyleClass().add("InputFields");
        // Give Row 4 & Column 2 to distanceInput fuelEffieciencyInput via GridPane
        GridPane.setConstraints(fuelEffieciencyInput, 2, 4);
        // Instruct GridPanel to give Center Horizontal Alignment with HPo
        GridPane.setHalignment(fuelEffieciencyInput, HPos.CENTER);

        // Declare new label for fuelUnitLabel & Give Text 'Miles per Gallon (MPG)'
        Label fuelUnitLabel = new Label("Miles per Gallon (MPG)");
        // Give CSS Properties with Class .InputLabels to fuelUnitLabel Label 
        fuelUnitLabel.getStyleClass().add("InputLabels");
        // Give Row 4 & Column 3 to distanceUnitLabel Label via GridPane
        GridPane.setConstraints(fuelUnitLabel, 3, 4);
        // Instruct GridPanel to give Left Horizontal Alignment with HPos
        GridPane.setHalignment(fuelUnitLabel, HPos.LEFT);

        // Declare new label for fuelTypeLabel & Give Text 'Fuel Type'        
        Label fuelTypeLabel = new Label("Fuel Type: ");
        // Give CSS Properties with Class .InputLabels to fuelTypeLabel Label 
        fuelTypeLabel.getStyleClass().add("InputLabels");
        // Give Row 5 & Column 1 to fuelTypeLabel Label via GridPane
        GridPane.setConstraints(fuelTypeLabel, 1, 5);
        // Instruct GridPanel to give Right Right Alignment with HPos
        GridPane.setHalignment(fuelTypeLabel, HPos.RIGHT);

        // Create ToggleGroup 'fuelType' so only one RadioButton can be checked at a time
        final ToggleGroup fuelType = new ToggleGroup();

        // Create RadioButton Octane with Text '98 Octance'
        RadioButton Octane = new RadioButton("98 Octane");
        // Assign RadioButton Octane to ToggleGroup fuelType so only one RadioButton can be selected
        Octane.setToggleGroup(fuelType);
        // Set Octance as Default Selections
        // Octane.setSelected(true);
        // Instruct GridPane to Give Margins to Octance
        GridPane.setMargin(Octane, new Insets(0, 0, 0, 10));
        // Give Row 5 & Column 2 to Octane RadioButton via GridPane
        GridPane.setConstraints(Octane, 2, 5);

        // Create RadioButton Diesel with Text 'Diesel'
        RadioButton Diesel = new RadioButton("Diesel");
        // Assign RadioButton Diesel to ToggleGroup fuelType so only one RadioButton can be selected
        Diesel.setToggleGroup(fuelType);
        // Instruct GridPane to Give Margins to Diesel
        GridPane.setMargin(Diesel, new Insets(0, 0, 0, -100));
        // Give Row 5 & Column 3 to Octane RadioButton via GridPane
        GridPane.setConstraints(Diesel, 3, 5);

        // TextArea 'Results' to display Results 
        TextArea Results = new TextArea();
        // Settings TextArea 'Results' as Non-Editable to Prevent Tampering
        Results.setEditable(false);
        // Give Row 8 & Column 2 to TextArea 'Results' via GridPane
        GridPane.setConstraints(Results, 2, 8);
        // Set Results Height & Width
        Results.setPrefSize(75, 150);

        // Decleare Button 'Calculate'
        Button calculate = new Button("Calculate");
        // Give CSS Properties with Class .CalculateButton to Calculate Button
        calculate.getStyleClass().add("CalculateButton");
        // Instruct GridPane to Give Margins to Calculate
        GridPane.setMargin(calculate, new Insets(10, 0, 10, 0));
        // Give Row 8 & Column 2 to TextArea 'Calculate' via GridPane
        GridPane.setConstraints(calculate, 2, 7);
        // Calculate onClick EventHandler
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            // Override Default Handle ActionEvent
            @Override
            public void handle(ActionEvent event) {
                // Try Catch Statement for ErrorHandling of NumberFormatExpection
                try {
                    /* Sends Values to ClientCalculation Class for Calculation as per Step 1. 
                     * Input 1 - Distance: (Double) Parses Double from String from TextField
                     * Input 2 - Fuel Effiency: (Double) Parses Double from String from TextField
                     * Input 3 - Fuel Choice: (String) Retrieves Value from RadioButton
                     */
                    ClientCalculation values = new ClientCalculation(Double.parseDouble(distanceInput.getText()), Double.parseDouble(fuelEffieciencyInput.getText()), fuelType.getSelectedToggle().toString().split("'")[1]);
                    
                    String display = "Trip Distance: " + distanceInput.getText() + " Miles \n"
                            + "Car’s fuel efficiency: " + fuelEffieciencyInput.getText() + " MPG \n"
                            + "Cost of fuel per litter: £" + values.getLitterPrice() + "\n"
                            + "Fuel Cost: £" + (String.format("%.2f", values.getCost()));

                    // Adds New Result in 'Results' TextField with 2 Decimal Points Rounding.
                    Results.setText(display);

                    // Optional AlertBox for Results
                    Alert calulcatedResponse = new Alert(AlertType.CONFIRMATION,display);
                    
                    // Show Response to User & Await Dismissal
                    calulcatedResponse.showAndWait();
                } catch (NumberFormatException ex) {
                    // This section is excuted when there is a NumberFormatException

                    // Optional: Set Error Information in Results TextField
                    // Results.setText(Results.getText() + "Please Enter Only Numbers! " + "\n");
                    // Define Error Alert Box
                    Alert expectionAlert = new Alert(AlertType.ERROR, "Dear User, Please Enter Numbers!");
                    // Shows Error Alert Box & Waits for User Dismissal
                    expectionAlert.showAndWait();
                } catch (NullPointerException ex) {
                    // This section is excuted when there is a NullPointerException

                    // Define Error Alert Box
                    Alert nullPointerAlert = new Alert(AlertType.ERROR, "Dear User, Please select the fuel type!");

                    // Shows Error Alert Box & Waits for User Dismissal
                    nullPointerAlert.showAndWait();

                } catch (FileNotFoundException ex) {
                    // This section is excuted when there is a FileNotFoundException

                    // Define Error Alert Box
                    Alert fileNotFoundAlert = new Alert(AlertType.ERROR, "Dear User, Input File was not found! Please make sure it exists.");

                    // Shows Error Alert Box & Waits for User Dismissal
                    fileNotFoundAlert.showAndWait();
                } catch (IOException ex) {
                    // This section is excuted when there is a Input Output Exception

                    // Define Error Alert Box
                    Alert ioErrorAlert = new Alert(AlertType.ERROR, "Dear User, Please Close Other Programs.");

                    // Shows Error Alert Box & Waits for User Dismissal
                    ioErrorAlert.showAndWait();
                }
            }
        });

        // Add Elements / Objects for Display in GridPane
        root.getChildren().addAll(companyName, programDescription, distanceLabel,
                distanceInput, distanceUnitLabel, fuelEffieciencyLabel, fuelEffieciencyInput,
                fuelUnitLabel, fuelTypeLabel, Octane, Diesel, calculate, Results);

        // Define Scene & Screen Dimensions
        // Scene scene = new Scene(root, 1000, 600);
        Scene scene = new Scene(root, 570, 500);

        // Get CSS File & Parse
        root.getStylesheets().add(getClass().getResource("ui.css").toString());

        // Set Application Title
        primaryStage.setTitle("ALSET Fuel Calculator");
        // Set Current Display Scene
        primaryStage.setScene(scene);
        // Show Stage
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
