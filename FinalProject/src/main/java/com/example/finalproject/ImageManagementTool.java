package com.example.finalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManagementTool extends Application {

    // UI Components
    private VBox root;
    private VBox uploadVBox;
    private VBox formatVBox;
    private VBox convertVBox;
    private Button uploadButton;
    private Button convertButton;
    private ComboBox<String> formatComboBox;
    private ImageView selectedImageView;
    private ListView<String> imageListView;
    private HBox thumbnailContainer;
    // File Handling
    private File selectedFile;  // Added to store the uploaded file
    private static ImageManagementTool instance;
    // Constructor
    public static synchronized ImageManagementTool getInstance() {
        if (instance == null) {
            instance = new ImageManagementTool();
        }
        return instance;
    }
    // Entry point of the application
    public static void main(String[] args) {
        launch(args);
    }

    // Method called when the application starts
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Management Tool");
        initializeUI();

        // Set actions for buttons
        uploadButton.setOnAction(event -> handleImageUpload());
        convertButton.setOnAction(event -> handleConvertImage());

        // Set up the main scene
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);

        // Center the buttons
        VBox centeredLayout = new VBox(root);
        centeredLayout.setAlignment(Pos.CENTER);

        // Add a heading
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(new javafx.scene.control.Label("Image Conversion Tool"), centeredLayout);

        // Set the main scene
        primaryStage.setScene(new Scene(mainLayout, 600, 400));
        primaryStage.show();
    }

    // Initialize the user interface
    private void initializeUI() {
        root = new VBox(10);
        uploadVBox = new VBox(10);
        formatVBox = new VBox(10);
        convertVBox = new VBox(10);
        uploadButton = new Button("Upload Image");
        convertButton = new Button("Convert Image");
        formatComboBox = new ComboBox<>();
        selectedImageView = new ImageView();
        imageListView = new ListView<>();
        thumbnailContainer = new HBox(10);
        formatComboBox.setPromptText("Select an Image Format");

        // Add image formats to the combo box
        formatComboBox.getItems().addAll("PNG", "JPG", "GIF", "BMP");

        // Set up the layout
        uploadVBox.getChildren().add(uploadButton);
        formatVBox.getChildren().addAll(formatComboBox);
        convertVBox.getChildren().add(convertButton);

        // Add the VBox to the root VBox and center the buttons
        root.getChildren().addAll(uploadVBox, formatVBox, convertVBox, selectedImageView, imageListView, thumbnailContainer);
        root.setAlignment(Pos.CENTER); // Center the buttons

        // Center the buttons in their respective VBoxes
        uploadVBox.setAlignment(Pos.CENTER);
        formatVBox.setAlignment(Pos.CENTER);
        convertVBox.setAlignment(Pos.CENTER);
    }

    // Handle image upload button click
    private void handleImageUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // Show file chooser dialog and get the selected file
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Display thumbnail and image properties
            displayThumbnail(selectedFile);
            displayImageProperties(selectedFile);
            // Add the selected file names to the imageListView
            imageListView.getItems().add(selectedFile.getName());
        }
    }

    // Display thumbnail of the selected image
    private void displayThumbnail(File file) {
        try {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);  // Set thumbnail width
            imageView.setFitHeight(100); // Set thumbnail height

            thumbnailContainer.getChildren().add(imageView);
        } catch (Exception e) {
            System.err.println("Error displaying thumbnail: " + e.getMessage());
            // Handle the exception appropriately (show an error message, log, etc.)
        }
    }

    // Display image properties (width and height)
    private void displayImageProperties(File selectedFile) {
        try {
            BufferedImage image = ImageIO.read(selectedFile);
            int width = image.getWidth();
            int height = image.getHeight();

            // Show an information dialog with image properties
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Image Properties");
            alert.setHeaderText(null);
            alert.setContentText("Width: " + width + "\nHeight: " + height);
            alert.showAndWait();
        } catch (IOException e) {
            System.err.println("Error reading image properties: " + e.getMessage());
            // Handle the exception appropriately (show an error message, log, etc.)
        }
    }

    // Handle convert image button click
    private void handleConvertImage() {
        if (selectedFile != null) {
            // Display the selected image in the ImageView
            selectedImageView.setImage(new Image(selectedFile.toURI().toString()));

            // Convert the image to the selected format
            String selectedFormat = formatComboBox.getValue();
            if (selectedFormat != null && !selectedFormat.isEmpty()) {
                convertImage(selectedFile, selectedFormat);
            } else {
                displayConversionError("Invalid format selection.");
            }
        } else {
            displayConversionError("Please upload an image first.");
        }
    }

    // Convert the image to the specified format
    private void convertImage(File file, String targetFormat) {
        try {
            BufferedImage originalImage = ImageIO.read(file);

            // Create a new file for the converted image
            String outputFileName = file.getName().replaceFirst("[.][^.]+$", "") + "_converted." + targetFormat.toLowerCase();
            File outputFile = new File(file.getParent(), outputFileName);

            // Check if the output file already exists
            if (outputFile.exists()) {
                displayConversionError("Output file already exists. Choose a different format or filename.");
                return;
            }

            // Write the converted image to the new file
            ImageIO.write(originalImage, targetFormat, outputFile);

            // Display a success message
            displayConversionSuccess(outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error converting image: " + e.getMessage());
            // Display an error message
            displayConversionError("Error converting image: " + e.getMessage());
        }
    }

    // Display a success message after image conversion
    private void displayConversionSuccess(String outputPath) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Image Conversion");
        alert.setHeaderText(null);
        alert.setContentText("Image converted successfully. Saved at: " + outputPath);
        alert.showAndWait();
    }

    // Display an error message for image conversion
    private void displayConversionError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Image Conversion Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public void stop() throws Exception {
        // Perform cleanup or save resources here
        System.out.println("Application is closing. Performing cleanup...");

        // Example: Closing open files, releasing resources, etc.
        closeOpenFiles();

        // Call the superclass stop() method to ensure a clean shutdown
        super.stop();
    }

    private void closeOpenFiles() {
        System.out.println("Closing open files...");
    }
}
