package testCases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.apache.http.impl.conn.tsccm.RouteSpecificPool;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payLoads.Payload;
import pojo.Product;
import routes.Routes;

import static org.hamcrest.MatcherAssert.assertThat;


public class ProductTest extends BaseClass{

	int productId;
	
	//1. Test to retrive all product
	@Test(priority = 1)
	public void testGetAllProducts() {
		
		given()
			
		.when()
			.get(Routes.GET_ALL_PRODUCTS)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.log().body();
	}
	
	//2. Retive the single product by Id
	@Test(dependsOnMethods = {"testGetAllProducts"})
	public void testGetProductById() {
		
		int productId = configReader.getIntProperty("productId");
		
		given()
			.pathParam("id", productId)
		.when()
			.get(Routes.GET_PRODUCT_BY_ID)
		.then()
			.statusCode(200)
			.log().body();
	}
	
	//3 . Test to retreive the limited number of product
	@Test(dependsOnMethods = {"testGetProductById"})
	public void testGetLimitedProduct() {
		
		given()
			.pathParam("limit", 5)
		.when()
			.get(Routes.GET_PRODUCTS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.log().body()
			.body("size()", equalTo(5));
		
		
	}
	
	//4. Test to Retrieve the product sorted in descending order
	@Test(dependsOnMethods = {"testGetLimitedProduct"})
	public void testGetSortedProductsDesc() {
		
		Response response = given()
				.pathParam("order", "desc")
		.when()
			.get(Routes.GET_PRODUCTS_SORTED)
		.then() 
			.statusCode(200)
			.extract().response();
		
		List<Integer> productId = response.jsonPath().getList("id", Integer.class);
		assertThat(isSortedDescending(productId), is(true));
	}
	
	//5. Test to Retrieve the product sorted in Ascending order
		@Test(dependsOnMethods = {"testGetSortedProductsDesc"})
		public void testGetSortedProductAsc() {
			
			Response response = given()
					.pathParam("order", "Asc")
			.when()
				.get(Routes.GET_PRODUCTS_SORTED)
			.then() 
				.statusCode(200)
				.extract().response();
			
			List<Integer> productId = response.jsonPath().getList("id", Integer.class);
			assertThat(isSortedAscending(productId), is(true));
		}
	
	 //6. Test to get All product categories
		@Test(dependsOnMethods = {"testGetSortedProductAsc"})
		public void testGetAllCategories() {
			
			given()
			.when()
				.get(Routes.GET_ALL_CATEGORIES)
			.then()
				.statusCode(200)
				.body("size()", greaterThan(0));
		}
		
	//7. Test to get products by categories
		@Test(dependsOnMethods = {"testGetAllCategories"})
		public void testGetProductByCategories() {
			
			given()
				.pathParam("category", "electronics")
			.when()
				.get(Routes.GET_PRODUCTS_BY_CATEGORY)
			.then()
				.statusCode(200)
				.body("size()", greaterThan(0))
				.body("category", everyItem(notNullValue()))
				.body("category", everyItem(equalTo("electronics")))
				.contentType("application/json")
				.log().body();	
		}
		
	//8. Create the product 
		@Test(dependsOnMethods = {"testGetAllCategories"})
		public void testAddProduct() {
			
			Product newProduct = Payload.productPayload();
			
			 productId = given()
				.contentType(ContentType.JSON)
				.body(newProduct)
			.when()
				.post(Routes.CREATE_PRODUCTS)
			.then()
				.log().body()
				.statusCode(200)//it should be 201
				.body("id", notNullValue())
				//.body("titel", equalTo(newProduct.getTitle()))
				.extract().jsonPath().getInt("id");//Extracting Id from response body
			System.out.println("Product Id : "+productId);
			
		}
		
	//9. Update the product 
		@Test(dependsOnMethods = {"testAddProduct"})
		public void testUpdateProduct() {
			
			int productId = configReader.getIntProperty("productId");
			Product updatedPayload = Payload.productPayload();
			
			given()
				.contentType(ContentType.JSON)
				.body(updatedPayload)
				.pathParam("id", productId)
			.when()
				.put(Routes.UPDATE_PRODUCTS)
			.then()
				.log().body()
				.statusCode(200)
				.body("title", equalTo(updatedPayload.getTitle()));
		}
		
		
	//10. Test to delete a product
		@Test(dependsOnMethods = {"testUpdateProduct"})
		public void testDeleteProduct() {
			int productId = configReader.getIntProperty("productId");
			given()
				.pathParam("id", productId)
			.when()
				.put(Routes.DELETE_PRODUCTS)
			.then()
				.statusCode(200);	
		}

	
}
