  package com.selenium.library;

import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import static junit.framework.Assert.*;
import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

import org.apache.poi.ss.usermodel.DataFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 

public class Driver {

 	static WebDriver wd;
 	String bro;
 	private String filepath;
 	static Properties properties = new Properties();
 	static Properties props = new Properties();

	public Driver(){
 		try{
 
		properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
 		
		bro = properties.getProperty("browser");
		int browe = 0;
 		if (bro.equalsIgnoreCase("firefox")){
			 browe = 1;
 		}
		 if (bro.equalsIgnoreCase("internet explorer")){
 			browe = 2;
		 }
 		if (bro.equalsIgnoreCase("safari")){
			 browe = 3;
 		}
		switch(browe){
		 case 1:
 			//wd = new FirefoxDriver();
			props.load(this.getClass().getClassLoader().getResourceAsStream("FFGUI.properties"));
			break;

		 case 2:
 			//wd = new InternetExplorerDriver();
			props.load(this.getClass().getClassLoader().getResourceAsStream("IEGUI.properties"));
 			break;

		 case 3:
 			//wd = new SafariDriver();
			props.load(this.getClass().getClassLoader().getResourceAsStream("safariGUI.properties"));
 			break;

		 default:
 			throw new RuntimeException("Doesn't support the selected Browser for Automation");
 
			}

		URL url=new URL(properties.getProperty("serverURL"));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setBrowserName(bro);
		capabilities.setPlatform(Platform.ANY);
		 wd = new RemoteWebDriver(url,capabilities);
		
 		}catch(Exception err){
 
			err.printStackTrace();
			wd.quit();
			fail("Driver couldn't invoke");
			
 
		}	
 
	}
 
	public void maximise()throws Exception{
 
		try{

			 wd.manage().window().maximize();
 
		}catch(Exception err){

			 err.printStackTrace();
			 wd.quit();
		}

	 }
 
	public void openURL() throws Exception{
 
		try{

			 wd.navigate().to(properties.getProperty("appURL"));
 
		}catch(Exception err){

			err.printStackTrace();
			wd.quit();
			fail("Couldn't open the given URL");

		 }
 
	}

	public void sync() throws Exception{

		 try{
 
			List<WebElement> myDynamicElement = (new WebDriverWait(wd,30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));
 
		}catch(NoSuchElementException err){

			err.printStackTrace();
			wd.quit();
			fail("Element does not exists on AUT");

		 }catch(TimeoutException err){
 
			err.printStackTrace();
			wd.quit();
			fail("Element does not exists with in 30s");

		 }
 
	}
 
	public void type(String element, String etext) throws Exception{
 
		try{
 
			wd.findElement(By.xpath(props.getProperty(element))).clear();

 			wd.findElement(By.xpath(props.getProperty(element))).sendKeys(etext);

		 }catch(NoSuchElementException err){
 
			err.printStackTrace();
			wd.quit();
		 }
 	}
 
	public void click(String element) throws Exception{
 
		try{
			
			if(element.startsWith("//")){
				sync();
				wd.findElement(By.xpath(element)).click();
				sync();
			}else {
			sync();
			wd.findElement(By.xpath(props.getProperty(element))).click();
			sync();
			}	

 		}catch(NoSuchElementException err){

			 err.printStackTrace();
			 wd.quit();
		}

	 }
 
	public String getText(String element)throws Exception{
		String text = null;
 
		try{

			 text = wd.findElement(By.xpath(props.getProperty(element))).getText();
 
			}catch(NoSuchElementException err){

				 err.printStackTrace();
				 wd.quit();
			}

		 return text;
 	}
 
	public String getAttribute(String element) throws Exception{
 
		String getAttribute = null;
		try{
 
			getAttribute = wd.findElement(By.xpath(props.getProperty(element))).getAttribute("class");
 
		}catch(ElementNotVisibleException err){
 
			err.printStackTrace();
			wd.quit();
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
			wd.quit();
		 }
 
	}
	
	public void select_option(String element, String etext) throws Exception{
		 try{
			
			 for(int i=1;i<=4;i++){
				 
				 	String cardpath = wd.findElement(By.xpath(props.getProperty(element))) + "[" + i + "]";
					String CardType = wd.findElement(By.xpath(cardpath)).getAttribute("value");
					if(CardType.equals(etext)){
						click(cardpath);
						break;
					}
			
			 }
			 
			 
			 
		 }catch(NoSuchElementException err){
			 
				err.printStackTrace();
				wd.quit();
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
			
			String city = props.getProperty("checkout_list_value");
			for(int i = 1; i <=element.size(); i++){
				
				String childpath = props.getProperty(childele) + "[" + i + "]";
				
				if(city.equals(wd.findElement(By.xpath(childpath)).getText())){
										
					WebElement menu = wd.findElement(By.xpath(childpath));
					WebElement parentmenu = wd.findElement(By.xpath(props.getProperty(parentelement)));
					//Actions builder = new Actions(wd);
					Actions builder = (new Actions(wd));
					builder.click(menu).perform();
					
					//builder.moveToElement(parentmenu).moveToElement(menu).click().build().perform();
					sync();
					break;
				}
			}
	
		}catch(Exception err){
			
 		 err.printStackTrace();
 		 wd.quit();
		}
		
	}
	
	public void baseState()throws Exception{
 
		try{
 
			wd.findElement(By.linkText(properties.getProperty("baseState"))).click();
 
			sync();

		 }catch(Exception err){
			 err.printStackTrace();
			 wd.quit();
			 fail("Couldn't return to base state");
		}

	}

	public void closeOpenedDriver(){

		try{
			wd.quit();
		}catch(Exception err){

			err.printStackTrace();
			wd.quit();
			fail("Couldn't able to close all opened browsers");
 		 }
 	}

	public void search() throws Exception{

		type("Home_search_textbox", "fridge");

 		click("Home_search_button");

		sync();
 
	}	

	public void browseProduct() throws Exception{

		//Clicking on the next arrow to click

		String TotalPages = getText("TotalPageNo");

		for (int j=1; j<3; j++)

		 {
 
			click("Search_Results_Next_Page_Link_Arrow_Footer");
 
			sync();

		 }
 

		//Clicking on the previous arrow to click

		int PageLink = (int) (1 + (Math.random() * (3 - 1)));

		 for (int j=3; j > PageLink; j--)
 
		{

			 click("Search_Results_Next_Page_Link_Arrow_Header");
 
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
			sync();
			click("check_out_button");
			sync();
			customerShippingInfo();
			selectCalendarDate();
			sync();
			paymentInfo();
			
			

		}catch(Exception err){
			
 		 err.printStackTrace();
 
		}
 
	}
	
	public void customerShippingInfo(){
				
		try{
			
			linkClick("Shipping_clear_form");
			type("shipping_first_name",props.getProperty("shipping_firstname"));
			type("shipping_last_name",props.getProperty("shipping_lastname"));
			
			if(props.getProperty("shipping_mobile") != null)
			{
				type("shipping_mobile_number",props.getProperty("shipping_mobile"));
				
				}else if(props.getProperty("shipping_Alt_mobile") != null){
					
					type("shipping_Alt_mobile_number",props.getProperty("shipping_Alt_mobile"));
					
					}else{
						
						type("shipping_landline_number",props.getProperty("shipping_landline"));
			}
			
			type("shipping_address1",props.getProperty("shippingaddress1"));
			type("shipping_address2",props.getProperty("shippingaddress2"));
			type("delivery_instructions",props.getProperty("del_instructions"));
			
		
		}catch(Exception err){
			
	 		 err.printStackTrace();
	 
			}
		
		
	}
	
	public void selectCalendarDate() throws Exception{
		
		try{
			
			sync();
			int calendardate;
			String todayDate;
			do{
				calendardate = (int) (1 + (Math.random() * (31 - 1)));
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    todayDate = dateFormat.format(new Date());
								
			}while(String.valueOf(calendardate).equals(todayDate));
			
			outerloop:for(int i=1; i<=5;i++){
				for(int j=1;j<=7;j++){
					String delivery_date = props.getProperty("delivery_calendar_trow") + "[" + i + "]" + "/td" + "[" + j + "]";
					String cal_ele = wd.findElement(By.xpath(delivery_date)).getAttribute("class");
					if(cal_ele.equals("thisMonth selectable")){
						if(String.valueOf(calendardate).equals(wd.findElement(By.xpath(delivery_date)).getText())){
							click(delivery_date);
							break outerloop;
						}
					}
					
				}
						
			}
	
		}catch(Exception err){
			
	 		 err.printStackTrace();
	 
			}

	}
	
	public void paymentInfo() throws Exception{
		
		try{
			
			select_option("card_type",props.getProperty("cardtype"));
			type("name_on_card",props.getProperty("nameOnCard"));
			type("card_number",props.getProperty("cardNumber"));
			select_list("card_expiry_month",props.getProperty("cardexpirymonth"));
			select_list("card_expiry_year",props.getProperty("cardexpiryyear"));
			type("verification_code",props.getProperty("verifyCode"));
			
			
		}catch(Exception err){
			
	 		 err.printStackTrace();
	 
			}
	
	}
	
	public void homePagePostcode() throws Exception{
		
		try{
			
			
			
			
			
		}catch(Exception err){
			
	 		 err.printStackTrace();
	 
			}
	}

	
	
}