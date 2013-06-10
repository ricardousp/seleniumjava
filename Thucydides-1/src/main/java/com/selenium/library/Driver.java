                                                                    
                                             
package com.selenium.library;

import java.net.URL;
import java.util.List;
import java.util.Properties;
import static junit.framework.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/* Author - Payal Garg
TestName - Main Library file
Date created - 29-04-2013
Date last modified - 29-05-2013 (by Payal Garg)
Reason for modification - added new functions*/

public class Driver {

 	static WebDriver wd;
 	String browser;
 	static Properties properties = new Properties();
 	static Properties props = new Properties();

	public Driver() throws Exception{
		
		properties.load(Driver.class.getClassLoader().getResourceAsStream("resources/config.properties"));		
		browser = System.getProperty("Browser");
		//browser = "chrome";
		int browe = 0;
		if (browser.equalsIgnoreCase("firefox")){
			 browe = 1;
		}
		 if (browser.equalsIgnoreCase("internet explorer")){
			browe = 2;
		 }
		if (browser.equalsIgnoreCase("safari")){
			 browe = 3;
		}
		if (browser.equalsIgnoreCase("chrome")){
			 browe = 4;
		}

		switch(browe){
		 case 1: 
			props.load(Driver.class.getClassLoader().getResourceAsStream("resources/FFGUI.properties"));
			break;
		 case 2:
 			 props.load(Driver.class.getClassLoader().getResourceAsStream("resources/IEGUI.properties"));
			break;
		 case 3:
 			 props.load(Driver.class.getClassLoader().getResourceAsStream("resources/SafariGUI.properties"));
			break;
		 case 4:
 			 props.load(Driver.class.getClassLoader().getResourceAsStream("resources/SafariGUI.properties"));
			break;
		 default:
			throw new RuntimeException("Doesn't support the selected browser for automation");
		}
	}
 
	public void maximise()throws Exception{ 
		try{
		 wd.manage().window().maximize();
		}catch(Exception err){
			 err.printStackTrace();
		}
	 }
 
	
	public void homePage(String homePage){
		try{
			if(homePage.equalsIgnoreCase("homepage")){
				
				wd.findElement(By.linkText("Home")).click();
				
			}
		}catch(Exception err){

			err.printStackTrace();
			System.out.println("Not on homepage, so going to HOMEPAGE");
			wd.get("http://www.appliancesonline.com.au");
		}
	}
	
	public void openURL(String URL) throws Exception{
 
		try{
			try{
				wd.quit();
			 	}catch(Exception err){
			 }
			URL url = new URL(properties.getProperty("serverURL"));
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setJavascriptEnabled(true);
			capabilities.setBrowserName(browser);
			capabilities.setPlatform(Platform.ANY);
			if(browser.equalsIgnoreCase("chrome")){
				
				//java -Dwebdriver.chrome.driver=/Applications/chromedriver -jar selenium-server-standalone-2.33.0.jar -port 1234
				wd = new RemoteWebDriver(url,DesiredCapabilities.chrome());
			}else if(browser.equalsIgnoreCase("safari")){
			
				wd = new RemoteWebDriver(new URL("http://127.0.0.1:1234/wd/hub"),capabilities);
			}else if(browser.equalsIgnoreCase("firefox")){
			
				wd = new RemoteWebDriver(new URL("http://127.0.0.1:1235/wd/hub"),capabilities);
			}
			
			
			wd.navigate().to(URL);
			sync();
 
		}catch(Exception err){

			err.printStackTrace();
			fail("Couldn't invoke the driver");

		 }
 
	}

	public void sync() throws Exception{

		 try{
			List<WebElement> myDynamicElement = (new WebDriverWait(wd,30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));
			if(myDynamicElement == null){
				wait(5000);
			}
		}catch(NoSuchElementException err){
			err.printStackTrace();
			fail("Element does not exists on AUT");
		 }catch(TimeoutException err){
			err.printStackTrace();
			fail("Element does not exists with in 30s");
		 }
	}
 
	public void type(String element, String etext) throws Exception{
		try{
			wd.findElement(By.xpath(props.getProperty(element))).clear();
 			wd.findElement(By.xpath(props.getProperty(element))).sendKeys(etext);
		 }catch(NoSuchElementException err){
			err.printStackTrace();
		 }
 	}
 
	public void click(String element) throws Exception{
		try{
			sync();
			wd.findElement(By.xpath(props.getProperty(element))).click();
			sync();
 		}catch(NoSuchElementException err){

			 err.printStackTrace();
 
		}

	 }
 
	public String getText(String element)throws Exception{
		String text = null;
 
		try{

			 text = wd.findElement(By.xpath(props.getProperty(element))).getText();
 
			}catch(NoSuchElementException err){

				 err.printStackTrace();
 
			}

		 return text;
 	}
 
	public String getAttribute(String element) throws Exception{
 
		String getAttribute = null;
		try{
 
			getAttribute = wd.findElement(By.xpath(props.getProperty(element))).getAttribute("class");
 
		}catch(ElementNotVisibleException err){
 
			err.printStackTrace();

		 }
 
		return getAttribute;
 
	}

	public void select_list(String element, String etext) throws Exception{
		try{
 
			List <WebElement> myL = wd.findElements(By.xpath(props.getProperty(element)));
 
			for (WebElement lItem : myL) {

				if (etext.equals(lItem.getText())){

				lItem.click();

				 }
 
			}

		 }catch(NoSuchElementException err){
 
			err.printStackTrace();

		 }
 
	}

	public void linkClick(String linkname) throws Exception{

		try{
			wd.findElement(By.linkText(props.getProperty(linkname))).click();
 
		}catch(Exception err){
			 err.printStackTrace();
		}
	 }
 
	public void ajaxElementSelect(String parentelement, String childele, List<WebElement> element) throws Exception{
		try{
			
			wd.findElement(By.xpath(props.getProperty("Checkout_Zip_Code"))).sendKeys(" ");
			
			String city = props.getProperty("list_value");
			for(int i = 1; i <=element.size(); i++){
				
				String childpath = props.getProperty(childele) + "[" + i + "]";
				
				if(city.equals(wd.findElement(By.xpath(childpath)).getText())){
										
					WebElement menu = wd.findElement(By.xpath(childpath));
					WebElement parentmenu = wd.findElement(By.xpath(props.getProperty(parentelement)));
					Actions builder = new Actions(wd);
					builder.moveToElement(parentmenu).moveToElement(menu).click().build().perform();
					sync();
					break;
				}
			}
	
		}catch(Exception err){
			
 		 err.printStackTrace();
 
		}
		
	}
	
	public void closeOpenedDriver(){ 
		try{
			if(wd != null)
			{
				
				wd.quit();
			}	
		}catch(Exception err){
			
			err.printStackTrace();
			fail("Couldn't able to close all opened browsers"); 
			
		 }
	}

	public void search(String element,String product) throws Exception{

		type(element, product);
 	//	click("Home_search_button");
	//	Thread.sleep(3000);
		sync();
 
	}	

	public void browseProduct() throws Exception{

		//Clicking on the next arrow to click

//		String TotalPages = getText("TotalPageNo");

		for (int j=1; j<3; j++)

		 {
 
			click("Search_Results_Next_Page_Link_Arrow_Footer");
 
			Thread.sleep(5000);

		 }
 

		//Clicking on the previous arrow to click

		int PageLink = (int) (1 + (Math.random() * (3 - 1)));

		 for (int j=3; j > PageLink; j--)
 
		{

			 click("Search_Results_Next_Page_Link_Arrow_Header");
			 Thread.sleep(5000);
 
		}

	}

	public void viewProduct() throws Exception {

		String parent = null;

		do{

			 parent = getAttribute("view_product_detail_button");
 
			if(!parent.equals("sli_list_result not-available")){

				click("view_product_detail_button");
 
				sync();

			 }			
 
			} while (parent.equals("sli_list_result not-available"));	
 
	}
 
	public void addCart() throws Exception {
 
		sync();
		click("add_to_cart_button");
 		sync();

	 }
 
	public void checkOut() throws Exception {
		
		try{
			
			String zip = props.getProperty("checkout_zipcode");		
			type("Checkout_Zip_Code", zip);
			Thread.sleep(5000);
			wd.findElement(By.xpath(props.getProperty("Checkout_Zip_Code"))).sendKeys(" ");
			List<WebElement> myele = wd.findElements(By.xpath(props.getProperty("AJAX_zip_code")));
			ajaxElementSelect("Checkout_Zip_Code","AJAX_zip_code", myele);
			click("check_out_button");
			sync();
			linkClick("Shipping_clear_form");

		}catch(Exception err){
			
 		 err.printStackTrace();
 
		}
 
	}

}