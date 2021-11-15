package pizzas;

import java.util.ArrayList;

/**
 * Abstract class that defines pizza characteristics.
 * Characteristics include pizza size, toppings, and price.
 * @author Sai Maduri, Heer Patel
 *
 */
public abstract class Pizza {

    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;
    
    public static final String DELUXE = "DELUXE";
    public static final String HAWAIIAN = "HAWAIIAN";
    public static final String PEPPERONI = "PEPPERONI";
    
    public static final double MEDIUM_PRICE_INCREASE = 2.00;
    public static final double LARGE_PRICE_INCREASE = 4.00;
    public static final double DELUXE_PRICE = 12.99;
    public static final double HAWAIIAN_PRICE = 10.99;
    public static final double PEPPERONI_PRICE = 8.99;
    public static final double TOPPING_PRICE = 1.49;
    
    public static final int PEPPERONI_TOPPINGS_COUNT = 1;
    public static final int HAWAIIAN_TOPPINGS_COUNT = 2;
    public static final int DELUXE_TOPPINGS_COUNT = 5;
    
    public static final int MAX_TOPPINGS_COUNT = 7;
    public static final double SALES_TAX = 0.06625;
    
    public static final String SMALL = "SMALL";
    public static final String MEDIUM = "MEDIUM";
    public static final String LARGE = "LARGE";

    /**
     * Generates pizza price.
     * @return pizza price.
     */
    public abstract double price();

    /**
     * Get pizza toppings.
     * @return Array list of pizza toppings.
     */
    public abstract ArrayList<Topping> getToppings();

    /**
     * Get pizza size.
     * @return Enum size of pizza.
     */
    public abstract Size getSize();

    /**
     * Get pizza type.
     * @return pizza type.
     */
    public abstract String getType();

    /**
     * Set pizza toppings.
     * @param toppings of pizza in array list format.
     */
    public abstract void setToppings(ArrayList<Topping> toppings);

    /**
     * Set pizza size.
     * @param size type of pizza.
     */
    public abstract void setSize(Size size);

    /**
     * Converts pizza information into a string.
     */
    @Override
    public abstract String toString();

}