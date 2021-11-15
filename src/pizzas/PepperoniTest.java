package pizzas;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class PepperoniTest {

	
	//Test valid price values of one topping pizzas of each size 
	@Test
	public void testOneToppingPrice() {

		ArrayList<Topping> oneTopping = new ArrayList<Topping>();
		oneTopping.add(0, Topping.BACON);

		Pizza smallPizza = new Pepperoni(Size.SMALL, oneTopping);
		assertEquals(smallPizza.price(), Pizza.PEPPERONI_PRICE, 0.001);	
		
		Pizza mediumPizza = new Pepperoni(Size.MEDIUM, oneTopping);
		assertEquals(mediumPizza.price(), Pizza.PEPPERONI_PRICE + Pizza.MEDIUM_PRICE_INCREASE, 0.001);	
		
		Pizza largePizza = new Pepperoni(Size.LARGE, oneTopping);
		assertEquals(largePizza.price(), Pizza.PEPPERONI_PRICE + Pizza.LARGE_PRICE_INCREASE, 0.001);
		
	}
	
	//Test valid price values of five topping pizzas of each size 
	@Test
	public void testFiveToppingPrice() {

		ArrayList<Topping> fiveTopping = new ArrayList<Topping>();
		fiveTopping.add(0, Topping.BACON);
		fiveTopping.add(1, Topping.CHICKEN);
		fiveTopping.add(2, Topping.OLIVES);
		fiveTopping.add(3, Topping.ONIONS);
		fiveTopping.add(4, Topping.JALAPENOS);


		Pizza smallPizza = new Pepperoni(Size.SMALL, fiveTopping);
		assertEquals(smallPizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*4), 0.001);	
		
		Pizza mediumPizza = new Pepperoni(Size.MEDIUM, fiveTopping);
		assertEquals(mediumPizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*4) + Pizza.MEDIUM_PRICE_INCREASE, 0.001);	
		
		Pizza largePizza = new Pepperoni(Size.LARGE, fiveTopping);
		assertEquals(largePizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*4) + Pizza.LARGE_PRICE_INCREASE, 0.001);
	}
	
	//Test valid price values of seven topping pizzas of each size 
		@Test
		public void testSevenToppingPrice() {

			ArrayList<Topping> sevenTopping = new ArrayList<Topping>();
			sevenTopping.add(0, Topping.BACON);
			sevenTopping.add(1, Topping.CHICKEN);
			sevenTopping.add(2, Topping.OLIVES);
			sevenTopping.add(3, Topping.ONIONS);
			sevenTopping.add(4, Topping.JALAPENOS);
			sevenTopping.add(5, Topping.EXTRACHEESE);
			sevenTopping.add(6, Topping.PINEAPPLE);


			Pizza smallPizza = new Pepperoni(Size.SMALL, sevenTopping);
			assertEquals(smallPizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*6), 0.001);	
			
			Pizza mediumPizza = new Pepperoni(Size.MEDIUM, sevenTopping);
			assertEquals(mediumPizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*6) + Pizza.MEDIUM_PRICE_INCREASE, 0.001);	
			
			Pizza largePizza = new Pepperoni(Size.LARGE, sevenTopping);
			assertEquals(largePizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*6) + Pizza.LARGE_PRICE_INCREASE, 0.001);
		}
		
		//Test invalid price values of nine topping pizzas of each size 
		@Test
		public void testNineToppingPrice() {

			ArrayList<Topping> nineTopping = new ArrayList<Topping>();
			nineTopping.add(0, Topping.BACON);
			nineTopping.add(1, Topping.CHICKEN);
			nineTopping.add(2, Topping.OLIVES);
			nineTopping.add(3, Topping.ONIONS);
			nineTopping.add(4, Topping.JALAPENOS);
			nineTopping.add(5, Topping.EXTRACHEESE);
			nineTopping.add(6, Topping.PINEAPPLE);
			nineTopping.add(7, Topping.SAUSAGE);
			nineTopping.add(8, Topping.MUSHROOMS);


			Pizza smallPizza = new Pepperoni(Size.SMALL, nineTopping);
			assertNotEquals(smallPizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*8), 0.001);	
			
			Pizza mediumPizza = new Pepperoni(Size.MEDIUM, nineTopping);
			assertNotEquals(mediumPizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*8) + Pizza.MEDIUM_PRICE_INCREASE, 0.001);	
			
			Pizza largePizza = new Pepperoni(Size.LARGE, nineTopping);
			assertNotEquals(largePizza.price(), Pizza.PEPPERONI_PRICE + (Pizza.TOPPING_PRICE*8) + Pizza.LARGE_PRICE_INCREASE, 0.001);
		}
		
		//Test valid  price values of a zero topping pizzas of each size
		@Test
		public void testNoToppingPrice() {
			ArrayList<Topping> noToppings = new ArrayList<Topping>();
			
			Pizza smallPizza = new Pepperoni(Size.SMALL, noToppings);
			assertEquals(smallPizza.price(), Pizza.PEPPERONI_PRICE, 0.001);	
			
			Pizza mediumPizza = new Pepperoni(Size.MEDIUM, noToppings);
			assertEquals(mediumPizza.price(), Pizza.PEPPERONI_PRICE + Pizza.MEDIUM_PRICE_INCREASE, 0.001);	
			
			Pizza largePizza = new Pepperoni(Size.LARGE, noToppings);
			assertEquals(largePizza.price(), Pizza.PEPPERONI_PRICE + Pizza.LARGE_PRICE_INCREASE, 0.001);		
		}

		
		
}
