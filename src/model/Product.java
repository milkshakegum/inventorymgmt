package Model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * This is the Product class.
 * @author Krystal Lee
 * @version C482
 * @since Fall 2020
 */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * This mutator method gets and returns id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * This accessor method sets id.
     * @param id int ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This mutator method gets and returns the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This accessor method sets the name.
     * @param name String name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This mutator method gets and returns the price.
     * @return price
     */

    public double getPrice() {
        return price;
    }

    /**
     * This accessor method sets the price.
     * @param price double price of the inventory item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * This mutator method gets and returns the number of items in stock.
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * This accessor method sets the number of items in stock.
      * @param stock int the number of items currently in stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This mutator method gets and returns the minimum.
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * This accessor method sets the minimum number of items that can be in stock.
     * @param min int minimum number
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This mutator method gets and returns the maximum
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * This accessor method sets the maximum number of items that can be in stock.
     * @param max int maximum number
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This method adds associated parts to part inventory.
     * @param part Part to be added
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method deletes associated parts from inventory.
     * @param part Part to be deleted
     */
    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }

    /**
     * This method returns all associated parts.
     * @return associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public void setPrice(double parseDouble) {
    }
}