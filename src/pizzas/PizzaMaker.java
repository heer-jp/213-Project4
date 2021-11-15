package pizzas;

import java.util.ArrayList;

/**
 * Creates an instance of pizza based on user's pizza choices.
 * @author Sai Maduri, Heer Patel
 *
 */
public class PizzaMaker {

	/**
	 * Creates an instance of pizza based on specificities chosen by user.
	 * @param pizzaType Deluxe, Hawaiin, or Pepperoni chosen by user.
	 * @param size Small, Medium, or Large chosen by user.
	 * @param toppings variety chosen by user.
	 * @return pizza instance with user's chosen specificities.
	 */
    public static Pizza createPizza(String pizzaType, Size size, ArrayList<Topping> toppings) {
        Pizza pizza = null;
        if (pizzaType.equals(Pizza.DELUXE)) {
            pizza = new Deluxe(size, toppings);
        } else if (pizzaType.equals(Pizza.HAWAIIAN)) {
            pizza = new Hawaiian(size, toppings);
        } else if (pizzaType.equals(Pizza.PEPPERONI)) {
            pizza = new Pepperoni(size, toppings);
        }
        return pizza;
    }

}