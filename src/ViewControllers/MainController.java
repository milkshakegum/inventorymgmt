package ViewControllers;

import Model.Part;
import Model.Product;
import Model.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * FXML MainController class.
 * @author Krystal Lee
 * @version C482
 * @since Fall 2020
 */
public class MainController implements Initializable {

    @FXML private TextField partSearchField;
    @FXML private TextField productSearchField;
    @FXML private TableView<Part> partTable;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Part, String> partId;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, String> partPrice;
    @FXML private TableColumn<Part, String> partStock;
    @FXML private TableColumn<Product, String> prodId;
    @FXML private TableColumn<Product, String> prodName;
    @FXML private TableColumn<Product, String> prodPrice;
    @FXML private TableColumn<Product, String> prodStock;
    private ObservableList<Part> searchList = FXCollections.observableArrayList();
    private ObservableList<Product> searchList2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        partStock.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        searchList.addAll(Inventory.getAllParts());
        partTable.setItems(searchList);

        prodId.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        prodName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prodPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        prodStock.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        searchList2.addAll(Inventory.getAllProducts());
        productTable.setItems(searchList2);
    }

    public void searchPartsAction(ActionEvent event) {
        String s = partSearchField.getText();

        if(!s.equals("") && s.charAt(0) != '-') {
            try {
                searchList.removeAll(searchList);
                searchList.add(Inventory.lookupPart(Integer.parseInt(s)));
            }

            catch (Exception e) {
                System.out.println(e);
                if (e instanceof NumberFormatException)
                    try {
                        searchList.setAll(Inventory.lookupPart(s));
                    }
                    catch (NoSuchElementException nse) {
                        searchList.setAll(FXCollections.observableArrayList());
                        System.out.println(nse);
                    }
            }
        }
        else
            searchList.setAll(Inventory.getAllParts());
    }

    public  void searchProductsAction(ActionEvent event) {
        String s = productSearchField.getText();

        if(!s.equals("") && s.charAt(0) != '-') {
            try {
                searchList2.removeAll(searchList2);
                searchList2.add(Inventory.lookupProduct(Integer.parseInt(s)));
            }

            catch (Exception e) {
                System.out.println(e);
                if (e instanceof NumberFormatException)
                    try {
                        searchList2.setAll(Inventory.lookupProduct(s));
                    }
                    catch (NoSuchElementException e1) {
                        searchList2.setAll(FXCollections.observableArrayList());
                        System.out.println(e1);
                    }
            }
        }
        else
            searchList2.setAll(Inventory.getAllProducts());
    }

    public void addPartAction(ActionEvent event) throws IOException {
        loadScene("PartsController.fxml", event);
    }

    public void modifyPartAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PartsController.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Modify Part");
            stage.setScene(new Scene(loader.load()));

            PartsController controller = loader.getController();
            controller.initData(partTable.getSelectionModel().getSelectedItem());

            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deletePartAction(ActionEvent event) {
        Optional<ButtonType> result = alertMe("The selected part will be permanently deleted from the inventory. " +
                "Do you want to proceed?");

        if(result.get() == ButtonType.OK) {
            Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
            searchPartsAction(event);
        }
    }

    public void addProductAction(ActionEvent event) {
        loadScene("ProductsController.fxml", event);
    }

    public void modifyProductAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsController.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Modify Product");
            stage.setScene(new Scene(loader.load()));

            ProductsController controller = loader.getController();
            controller.initData(productTable.getSelectionModel().getSelectedItem());

            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteProductAction(ActionEvent event) {

        // fixed issue with deleted products with associated parts
        if(productTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().size() > 0) {
            Optional<ButtonType> result = alertMe("Sorry this product has an associated part and cannot be deleted!");
        }

        else {
            Optional<ButtonType> result = alertMe("The selected product will be permanently deleted from the inventory. " +
                    "Do you want to proceed?");

            if(result.get() == ButtonType.OK) {
                Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
                searchProductsAction(event);
            }
        }

    }

    public void exitApplication() {
        Optional<ButtonType> result = alertMe("You will be exited from the program. " +
                "Do you want to proceed?");

        if(result.get() == ButtonType.OK)
            System.exit(0);
    }

    private void loadScene(String destination, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(destination));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Optional<ButtonType> alertMe(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION) ;
        alert.initModality(Modality.NONE);
        alert.setContentText(message);
        return alert.showAndWait();
    }

}
