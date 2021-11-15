package pizzas;

import java.util.ArrayList;

/**
 * Keeps track of pizza instances created by a specific phone number given by the user within an array list.
 * @author Sai Maduri, Heer Patel
 *
 */
public class Order {
    private String phoneNumber;
    private ArrayList<Pizza> order;

    /**
     * Set phone number to the number given by user. 
     * @param phoneNumber given by user.
     */
    public Order(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        order = new ArrayList<>();
    }

    /**
     * Add pizza instance to order array list.
     * @param pizza order created by user.
     */
    public void addPizza(Pizza pizza) {
        order.add(pizza);
    }

    /**
     * Remove user specified pizza instance from array list.
     * @param pizza chosen created by user.
     */
    public void removePizza(Pizza pizza) {
        order.remove(pizza);
    }

    /**
     * Gets prices for each pizza instance in the users orders.
     * @return price total
     */
    public double getPrice() {
        double price = 0;
        for (Pizza p : order) {
            price += p.price();
        }
        price += price * Pizza.SALES_TAX;
        return price;
    }

    /**
     * Get pizza size for pizza instance.
     * @return pizza size.
     */
    public int getSize() {
        return order.size();
    }

    /**
     * Get phone number for the orders.
     * @return phone number of order
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets list of pizza orders.
     * @return pizza orders
     */
    public ArrayList<Pizza> getOrder() {
        return order;
    }

    /**
     * Converts pizza orders into a string printout.
     * @return string representation of the order
     */
    @Override
    public String toString() {
        String s = "Phone Number: " + phoneNumber + ":\n";
        for (Pizza p : order) {
            s += p.toString() + "\n";
        }
        return s + "Price: $" + String.format("%,.2f", getPrice());
    }
}
