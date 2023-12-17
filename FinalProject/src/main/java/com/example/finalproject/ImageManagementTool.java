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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageManagementTool extends Application {

    // UI Components
    private VBox root;
    private Button uploadButton;
    private Button convertButton;
    private Button downloadButton;  // New button for downloading
    private ComboBox<String> formatComboBox;
    private ListView<String> imageListView;
    private HBox thumbnailContainer;
    // File Handling
    private List<File> selectedFiles = new ArrayList<>();  // Changed to support multiple files
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
        downloadButton.setOnAction(event -> handleDownloadImages());  // Set action for download button

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
        VBox uploadVBox = new VBox(10);
        VBox formatVBox = new VBox(10);
        VBox convertVBox = new VBox(10);
        uploadButton = new Button("Upload Image");
        convertButton = new Button("Convert the Image Format");
        downloadButton = new Button("Download Converted Images");  // New button for downloading
        formatComboBox = new ComboBox<>();
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
        root.getChildren().addAll(uploadVBox, formatVBox, convertVBox, downloadButton, thumbnailContainer, imageListView);
        root.setAlignment(Pos.CENTER); // Center the buttons

        // Center the buttons in their respective VBoxes
        uploadVBox.setAlignment(Pos.CENTER);
        formatVBox.setAlignment(Pos.CENTER);
        convertVBox.setAlignment(Pos.CENTER);
        downloadButton.setAlignment(Pos.CENTER);  // Center the download button
    }

    // Handle image upload button click
    private void handleImageUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // Show file chooser dialog and get the selected files
        List<File> selectedFilesList = fileChooser.showOpenMultipleDialog(null);
        if (selectedFilesList != null && !selectedFilesList.isEmpty()) {
            selectedFiles.addAll(selectedFilesList);

            // Display thumbnails and image properties for each uploaded image
            for (File file : selectedFilesList) {
                displayThumbnail(file);
                displayImageProperties(file);
                // Add the selected file names to the imageListView
                imageListView.getItems().add(file.getName());
            }
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
            alert.setContentText("File: " + selectedFile.getName() +
                    "\nWidth: " + width +
                    "\nHeight: " + height);
            alert.showAndWait();
        } catch (IOException e) {
            System.err.println("Error reading image properties: " + e.getMessage());
            // Handle the exception appropriately (show an error message, log, etc.)
        }
    }

    // Handle convert image button click
    private void handleConvertImage() {
        // Handle image conversion for each selected file
        for (File selectedFile : selectedFiles) {
            if (selectedFile != null) {
                // Convert the image to the selected format
                String selectedFormat = formatComboBox.getValue();
                if (selectedFormat != null && !selectedFormat.isEmpty()) {
                    // No need to download immediately; handled during download
                } else {
                    displayConversionError("Invalid format selection.");
                }
            } else {
                displayConversionError("Please upload an image first.");
            }
        }
    }

    // Handle Download Method
    private void handleDownloadImages() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Destination Folder");

        // Show the directory chooser dialog and get the selected folder
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            // Download the converted images to the selected folder
            for (File selectedFile : selectedFiles) {
                if (selectedFile != null) {
                    // Convert the image to the selected format
                    String selectedFormat = formatComboBox.getValue();
                    if (selectedFormat != null && !selectedFormat.isEmpty()) {
                        downloadConvertedImage(selectedFile, selectedFormat, selectedDirectory);
                    } else {
                        displayConversionError("Invalid format selection.");
                    }
                } else {
                    displayConversionError("Please upload an image first.");
                }
            }

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Download Complete");
            alert.setHeaderText(null);
            alert.setContentText("Images downloaded successfully to: " + selectedDirectory.getAbsolutePath());
            alert.showAndWait();
        }
    }

    // Convert the image to the specified format and download
    private void downloadConvertedImage(File file, String targetFormat, File destinationFolder) {
        try {
            BufferedImage originalImage = ImageIO.read(file);

            // Create a new file for the converted image in the destination folder
            String outputFileName = file.getName().replaceFirst("[.][^.]+$", "") + "_converted." + targetFormat.toLowerCase();
            File outputFile = new File(destinationFolder, outputFileName);

            // Check if the output file already exists
            if (outputFile.exists()) {
                displayConversionError("Output file already exists. Choose a different format or filename.");
                return;
            }

            // Write the converted image to the new file
            ImageIO.write(originalImage, targetFormat, outputFile);
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
