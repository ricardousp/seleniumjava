package Automation;

import com.thoughtworks.selenium.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.regex.Pattern;


public class AppTest{
	
	WebDriver myD = new FirefoxDriver();;

	@Before
	public void SetUp(){
		System.out.println("Before Test");
		
		myD.get("http://www.youtube.com");
	}
	@Test
	public void searchTest() throws Exception{
		System.out.println("During Test");
		myD.findElement(By.id("masthead-search-term")).sendKeys("Selenium");
		myD.findElement(By.id("search-btn")).click();
		Thread.sleep(3000);
		
	}
	
	@Test
	public void Test1() throws Exception{
		System.out.println("Hello");
	}
	
	
	@After
	public void TearDown(){
		myD.close();
		System.out.println("AFter Test");
		
	}
	
}

