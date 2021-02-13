package Model;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the Inventory class.
 * @author Krystal Lee
 * @version C482
 * @since Fall 2020
 */
public class Inventory {

    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Void method addPart adds new part to inventory.
     * @param part new part to add to allParts list
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * Part lookupPart method uses an "enhanced" for each loop to iterate through Part inventory list.
     * When a match is found the method returns the part. If no matches are found NoSuchElementException
     * thrown and returns "Part not found"
     * @param partId int Part ID to find
     * @return matched part ID
     */
    public static Part lookupPart(int partId) {

        // changed to enhanced for each loop
        for (Part part : allParts) {
            if (part.getId() == partId)
                return part;
        }
        throw new NoSuchElementException("Part not found");
    }

    /**
     * This method iterates the part inventory until a match is found. Results are added to inventory.
     * @param partName String part name to lookup
     * @return searchList returns the results
     */
    public static ObservableList<Part> lookupPart(String partName) {
        Iterator<Part> partIterator = allParts.listIterator();
        ObservableList<Part> results = FXCollections.observableArrayList();
        while(partIterator.hasNext()) {
            Part part = partIterator.next();
            if(part.getName().equals(partName)) {
                results.add(part);
                return results;
            }
        }
        throw new NoSuchElementException("Part not found");
    }

    /**
     * Void updatePart method updates the part and index of part.
     * @param index int index of part
     * @param part Part part to be updated
     */
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    /**
     * Void deletePart is a method that can be called when a list item needs to be deleted..
     * @param part Part part to be deleted
     */
    public static void deletePart(Part part) {
        allParts.remove(part);
    }

    /**
     * This static method returns all parts in the inventory.
     * @return allParts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Static void addProduct is a method that adds product to the inventory.
     * @param product Product product
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * Static lookupProduct method uses an enhanced for each loop to iterate through the product inventory searching for
     * a productId match. If there is a match the result is returned.
     * @param productId int product ID
     * @return product
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId)
                return product;
        }
        throw new NoSuchElementException("Product not found");
    }

    /**
     * This method iterates the product inventory list until a match is found. Results are added to the inventory.
     * @param productName String product name that will be used to match against the results of the search.
     * @return results
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        Iterator<Product> itr = allProducts.listIterator();
        ObservableList<Product> results = FXCollections.observableArrayList();
        while(itr.hasNext()) {
            Product prod = itr.next();
            if(prod.getName().equals(productName)) {
                results.add(prod);
                return results;
            }
        }
        throw new NoSuchElementException("Product not found");
    }

    /**
     * This static updateProduct method updates the product inventory.
     * @param index int index to be updated
     * @param product Product list inventory to be updated
     */
    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }

    /**
     * This method deletes the selected product.
     * @param product Product product name to be deleted
     */
    public static void deleteProduct(Product product) {
        allProducts.remove(product);
    }

    /**
     * This static method returns the entire product inventory.
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
