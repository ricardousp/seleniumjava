package Automation;

import com.thoughtworks.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.regex.Pattern;


public class AppTest{
	
	WebDriver myD = new FirefoxDriver();;

	@BeforeTest
	public void SetUp(){
		System.out.println("Before Test");
	
		myD.get("http://www.youtube.com");
	}
	@Test
	public void Test1() throws Exception{
		System.out.println("During Test");
		myD.findElement(By.id("masthead-search-term")).sendKeys("Selenium");
		myD.findElement(By.id("search-btn")).click();
		Thread.sleep(3000);
		
	}
	@AfterTest
	public void TearDown(){
		myD.close();
		System.out.println("AFter Test");
		
	}
	
}

