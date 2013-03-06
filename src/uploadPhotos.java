import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class uploadPhotos{
	
	WebDriver myD;

@Before
public void setUp() throws Exception{
	myD = new FirefoxDriver();
	myD.navigate().to("http://www.gmail.com/");
}
@Test
public void Test1() throws Exception{
	
	myD.findElement(By.id("email")).sendKeys("payalgarg01@gmail.com");
	myD.findElement(By.id("pass")).sendKeys("laddooji");
	myD.findElement(By.id("u_0_4")).click();
	myD.findElement(By.linkText("Payal Garg")).click();
	myD.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/a/span")).click();
	myD.findElement(By.id("u_e_3")).click();
	
}
	
@After
public void TearDown(){
	System.out.println("done");
}
}
