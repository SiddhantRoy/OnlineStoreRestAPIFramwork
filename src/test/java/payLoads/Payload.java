package payLoads;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Product;

public class Payload {

	private static final Faker faker = new Faker();
	private static final String[] categories = {"electronic", "furniture", "clothing", "bookes", "beauty"};
	static Random random = new Random();
	
	//Maintain the payload of Product 
	public static Product productPayload(){
		String title = faker.commerce().productName();
		double price = Double.parseDouble(faker.commerce().price());
		String description = faker.lorem().sentence();
		String image="http://example.com";  
		String category = categories[random.nextInt(categories.length)];
		
		
		return new Product(title, price, description, image, category);
	}
	//Maintain the payload of  Cart
	
	//Maintain the payload of User
	
	//Maintain the payload of Auth
	
	
}
