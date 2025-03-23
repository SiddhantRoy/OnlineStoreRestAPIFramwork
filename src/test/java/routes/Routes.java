package routes;

public class Routes {

	// Here we don't create any method. I create a varibale for endpoints later i wll access on the test case
	public static final String BASE_URL = "https://fakestoreapi.com";
	
	//1. Product Module endpoint
	public static final String GET_ALL_PRODUCTS = "/products";
	public static final String GET_PRODUCT_BY_ID = "/products/{id}";
	public static final String GET_PRODUCTS_WITH_LIMIT = "/products?limit={limit}";
	public static final String GET_PRODUCTS_SORTED = "/products?sort={order}";
	public static final String GET_ALL_CATEGORIES = " /products/categories";
	public static final String GET_PRODUCTS_BY_CATEGORY = "/products/category/{category}";
	public static final String CREATE_PRODUCTS = "/products";
	public static final String UPDATE_PRODUCTS = "/products/{id}";
	public static final String DELETE_PRODUCTS = "/products/{id}";
	
	// 2. Carts Module EndPoint
	// 3. Users Mobule Endpoint
	// 4. Auth 
}
