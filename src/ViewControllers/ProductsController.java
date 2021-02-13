package ViewControllers;

import Model.Inventory;
import Model.Part;
import Model.Product;
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
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML ProductsController class.
 * @author Krystal Lee
 * @version C482
 * @since Fall 2020
 */
public class ProductsController implements Initializable {

    @FXML private TextField partSearchField;
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField stock;
    @FXML private TextField price;
    @FXML private TextField max;
    @FXML private TextField min;
    @FXML private TableView<Part> foundParts;
    @FXML private TableView<Part> includedParts;
    @FXML private TableColumn<Part, String> foundId;
    @FXML private TableColumn<Part, String> foundName;
    @FXML private TableColumn<Part, String> foundStock;
    @FXML private TableColumn<Part, String> foundPrice;
    @FXML private TableColumn<Part, String> includedId;
    @FXML private TableColumn<Part, String> includedName;
    @FXML private TableColumn<Part, String> includedStock;
    @FXML private TableColumn<Part, String> includedPrice;
    @FXML private Label label;
    private Product product;
    ObservableList<Part> tempList = FXCollections.observableArrayList();
    ObservableList<Part> searchList = FXCollections.observableArrayList();
    private static int idCount = 0;

    public void initialize(URL url, ResourceBundle rb) {
        foundId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        foundName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        foundStock.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        foundPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        searchList.setAll(Inventory.getAllParts());
        foundParts.setItems(searchList);

        includedId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        includedName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        includedStock.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        includedPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        includedParts.setItems(tempList);

        id.setText("Auto Generated");
        id.setDisable(true);
    }

    public void initData(Product product) {
        this.product = product;
        id.setText(Integer.toString(product.getId()));
        name.setText(product.getName());
        price.setText(Double.toString(product.getPrice()));
        stock.setText(Integer.toString(product.getStock()));
        min.setText(Integer.toString(product.getMin()));
        max.setText(Integer.toString(product.getMax()));
        id.setDisable(true);
        label.setText("Modify Product");
        includedParts.setItems(product.getAllAssociatedParts());
    }

    public void searchPartAction(ActionEvent event) {
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

    public void addPartAction(ActionEvent event) {
        if(product != null)
            product.addAssociatedPart(foundParts.getSelectionModel().getSelectedItem());
        else
            tempList.add(foundParts.getSelectionModel().getSelectedItem());
    }

    public void deletePartAction(ActionEvent event) {
        Optional<ButtonType> result = alertMe("Are you sure you want to remove the selected part?");


        if(result.get() == ButtonType.OK) {
            if (product != null)
                product.deleteAssociatedPart(includedParts.getSelectionModel().getSelectedItem());
            else
                tempList.remove(includedParts.getSelectionModel().getSelectedItem());
        }
    }

    public void saveAction(ActionEvent event) {
        if(validNumbers()) {
            if (product == null) {
                product = new Product(
                        idCount++,
                        name.getText(),
                        Double.parseDouble(price.getText()),
                        Integer.parseInt(stock.getText()),
                        Integer.parseInt(min.getText()),
                        Integer.parseInt(max.getText()));

                for (Part part : tempList)
                    product.addAssociatedPart(part);

                Inventory.addProduct(product);
            } else {
                product.setName(name.getText());
                product.setPrice(Double.parseDouble(price.getText()));
                product.setStock(Integer.parseInt(stock.getText()));
                product.setMin(Integer.parseInt(min.getText()));
                product.setMax(Integer.parseInt(max.getText()));
            }

            loadScene("MainController.fxml", event);
        }
        else {
            alertMe("Max cannot be less than inventory. Please enter the appropriate numerical values");
        }
    }

    public void cancelAction(ActionEvent event) {
        Optional<ButtonType> result = alertMe("Are you sure you want to cancel?");

        if(result.get() == ButtonType.OK) {
            loadScene("MainController.fxml", event);
        }
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

    private boolean validNumbers() {
        return Integer.parseInt(min.getText()) <= Integer.parseInt(max.getText())
                && Integer.parseInt(stock.getText()) >= Integer.parseInt(min.getText())
                && Integer.parseInt(stock.getText()) <= Integer.parseInt(max.getText());
    }

    private Optional<ButtonType> alertMe(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION) ;
        alert.initModality(Modality.NONE);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
