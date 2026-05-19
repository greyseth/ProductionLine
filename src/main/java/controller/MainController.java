package controller;

import data.ProductionManager;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.AudioPlayer;
import model.Product;
import model.Screen;
import model.UniqueItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.function.Consumer;

public class MainController {
    @FXML
    private Button button_AddProduct;

    @FXML
    private Button button_AddProduct_Save;

    @FXML
    private Button button_ProductionCatalog;

    @FXML
    private Button button_Statistics;

    @FXML
    private VBox container_AddProduct;

    @FXML
    private VBox container_ProductCatalog;

    @FXML
    private VBox container_AudioPlayerForm;

    @FXML
    private VBox container_ScreenForm;

    @FXML
    private FlowPane container_Welcome;

    @FXML
    private Label display_AddProduct_Log;

    @FXML
    private TextField input_AudioPlayerName;

    @FXML
    private TextField input_AudioSpecification;

    @FXML
    private ComboBox<String> input_ProductType;

    @FXML
    private TextField input_RefreshRate;

    @FXML
    private TextField input_ResponseTime;

    @FXML
    private TextField input_ScreenResolution;

    @FXML
    private HBox container_ProductQuantity;

    @FXML
    private TextField input_ProductQuantity;

    @FXML
    private TableView<Product> table_CatalogTable;

    @FXML
    private TableColumn<Product, Integer> table_CatalogTable_No;

    @FXML
    private TableColumn<Product, String> table_CatalogTable_ProductType;

    @FXML
    private TableColumn<Product, String> table_CatalogTable_Name;

    @FXML
    private TableColumn<Product, String> table_CatalogTable_Classifications;

    @FXML
    private VBox container_Statistics;

    @FXML
    private Label display_ProductsAdded;

    @FXML
    private Label display_UniqueItems;

    @FXML
    private TableView<UniqueItems> table_UniqueItems;

    @FXML
     private TableColumn<UniqueItems, String> table_UniqueItems_Name;

    @FXML
    private TableColumn<UniqueItems, Integer> table_UniqueItems_Amount;

    @FXML
    void initialize() {
        // Navigation
        button_AddProduct.setOnAction(v -> {
            disableContainers();
            container_AddProduct.setVisible(true);
            display_AddProduct_Log.setVisible(false);
        });

        button_ProductionCatalog.setOnAction(v -> {
            disableContainers();
            updateCatalogTable();
            container_ProductCatalog.setVisible(true);
        });

        button_Statistics.setOnAction(v -> {
            disableContainers();
            updateUniqueItemsTable();
            container_Statistics.setVisible(true);
        });

        // AddProduct actions
        input_ProductType.setItems(FXCollections.observableArrayList("Screen", "Audio Player"));
        input_ProductType.setOnAction(v -> {
            button_AddProduct_Save.setVisible(true);
            container_ProductQuantity.setVisible(true);

            if (input_ProductType.getValue().equals("Audio Player")) {
                container_AudioPlayerForm.setVisible(true);
                container_ScreenForm.setVisible(false);

                button_AddProduct_Save.setOnAction(x -> {
                    String name = input_AudioPlayerName.getText();
                    if (name.isEmpty()) {
                        productAddLog("Name cannot be empty!", false);
                        return;
                    }

                    String specification = input_AudioSpecification.getText();
                    if (specification.isEmpty()) {
                        productAddLog("Specification cannot be empty!", false);
                        return;
                    }

                    int quantity = 0;
                    try {quantity = Integer.parseInt(input_ProductQuantity.getText());}
                    catch (NumberFormatException e) {productAddLog("Invalid amount input!", false); return;}

                    for (int i = 0; i < quantity; i++) {
                        ProductionManager.productionLog.add(new AudioPlayer(name, specification));
                    }

                    productAddLog("Product added successfully!", true);
                });
            }else if (input_ProductType.getValue().equals("Screen")) {
                container_AudioPlayerForm.setVisible(false);
                container_ScreenForm.setVisible(true);

                button_AddProduct_Save.setOnAction(x -> {
                    String resolution = input_ScreenResolution.getText();
                    if (resolution.isEmpty()) {
                        productAddLog("Resolution!", false);
                        return;
                    }

                    int refreshRate = 0;
                    try { refreshRate = Integer.parseInt(input_RefreshRate.getText()); }
                    catch(NumberFormatException e) {productAddLog("Invalid refresh rate input!", false); return;}

                    int responseTime = 0;
                    try { responseTime = Integer.parseInt(input_ResponseTime.getText()); }
                    catch(NumberFormatException e) {productAddLog("Invalid response time input!", false); return;}

                    int quantity = 0;
                    try {quantity = Integer.parseInt(input_ProductQuantity.getText());}
                    catch (NumberFormatException e) {productAddLog("Invalid amount input!", false); return;}

                    for (int i = 0; i < quantity; i++) {
                        ProductionManager.productionLog.add(new Screen(resolution, refreshRate, responseTime));
                    }

                    productAddLog("Product added successfully!", true);
                });
            }
        });

        // Initialize catalog table
        table_CatalogTable_No.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        table_CatalogTable_ProductType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        table_CatalogTable_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_CatalogTable_Classifications.setCellValueFactory(new PropertyValueFactory<>("classification"));

        // Initialize unique items table
        table_UniqueItems_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_UniqueItems_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    void updateCatalogTable() {
        ObservableList<Product> products = FXCollections.observableArrayList(ProductionManager.productionLog);
        table_CatalogTable.setItems(products);
    }

    void updateUniqueItemsTable() {
        display_ProductsAdded.setText("Total Products Added: "+ProductionManager.productionLog.size());

        ArrayList<UniqueItems> ui = new ArrayList<>();
        ProductionManager.productionLog.forEach(product -> {
            if (ui.stream().anyMatch(p -> p.getName().equals(product.getName()))) {
                for (int i = 0; i < ui.size(); i++) {
                    UniqueItems ui2 = ui.get(i);
                    ui2.setAmount(ui.get(i).getAmount()+1);
                    ui.set(i, ui2);
                }
                return;
            }
            ui.add(new UniqueItems(product.getName(), 1));
        });

        ObservableList<UniqueItems> oui = FXCollections.observableArrayList(ui);
        table_UniqueItems.setItems(oui);

        display_UniqueItems.setText("Total Unique Items: "+ui.size());
    }

    void disableContainers() {
        container_Welcome.setVisible(false);
        container_AddProduct.setVisible(false);
        container_ProductCatalog.setVisible(false);
        container_Statistics.setVisible(false);
    }

    void productAddLog(String message, boolean success) {
        display_AddProduct_Log.setVisible(true);
        display_AddProduct_Log.setTextFill(success? Color.GREEN :Color.RED);
        display_AddProduct_Log.setText(message);
    }
}
