package testCases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;
import utils.ConfigReader;

public class BaseClass {

	ConfigReader configReader;
	
	//For logging
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;


	@BeforeClass
	public void setup() throws FileNotFoundException {

		RestAssured.baseURI = Routes.BASE_URL;
		configReader = new ConfigReader();
		
		// Setup filters for logging
		 FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.txt");
		 PrintStream log = new PrintStream(fos, true);
		 requestLoggingFilter = new RequestLoggingFilter(log);
		 responseLoggingFilter = new ResponseLoggingFilter(log);

		 RestAssured.filters(requestLoggingFilter, responseLoggingFilter);

	}

	boolean isSortedDescending(List<Integer> lst) {

		for (int i = 0; i < lst.size() - 1; i++) {
			if (lst.get(i) < lst.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

	boolean isSortedAscending(List<Integer> lst) {

		for (int i = 0; i < lst.size() - 1; i++) {
			if (lst.get(i) > lst.get(i + 1)) {
				return false;
			}
		}
		return true;
	}
}
