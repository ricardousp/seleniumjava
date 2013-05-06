                                        
package automation;

                                                                     
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import com.selenium.library.*;

public class CustomerOrder{

	static Driver driver = new Driver();;

	
@BeforeClass
public static void setUp() throws Exception{
	driver.openURL();
	driver.maximise();
	
}
	
@Test
public void customerOrderTest() throws Exception{
	
	// searching the product on Home page
		driver.search();
	
	// browsing through the products view pages
		driver.browseProduct();
	
	// view the product details
		driver.viewProduct();
	
	//adding the product to cart
//		driver.addCart();
	
	//checkout
//		driver.checkOut();
}

@After
public void teardown() throws Exception{
	driver.sync();
	driver.baseState();
	
}
	
@AfterClass

public static void classdown() throws Exception{
	
	driver.closeOpenedDriver();
}
	

}

