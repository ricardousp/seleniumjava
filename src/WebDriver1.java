import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class WebDriver1 {
	
	public String vIP1,vIP2;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("hello");
		WebDriver myD = new InternetExplorerDriver() ;
	//	WebDriver myD = new FirefoxDriver() ;
		myD.navigate().to("http://www.gmail.com");
		myD.findElement(By.name("Email")).sendKeys("vivekgarg01");
		myD.findElement(By.name("Passwd")).sendKeys("laddooji");
		myD.findElement(By.name("signIn")).click();
		Thread.sleep(15000);
		myD.findElement(By.xpath("//input[@name='q']")).sendKeys("OttPilot-immigration@cic.gc.ca");
		myD.findElement(By.xpath("//button[@id='gbqfb']")).click();
	}

}
