import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class WebDriver2 {
	
	public String vIP1,vIP2;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("hello");
	//	WebDriver myD = new InternetExplorerDriver() ;
		WebDriver myD = new FirefoxDriver() ;
		String sPath, Text , value ,vTechnology ;
	
		
//		1	Open the browser and enter the URL	navigate_to
	
		navigate_to(myD,"http://www.dice.com/");
		
//		2	Enter technology	send_keys
		sPath = "//*[@id='FREE_TEXT']" ;
		Text = "Selenium" ;
		send_keys(myD,sPath,Text);

//		3	Enter City	send_keys
		sPath = "//*[@id='zipCodeCity']" ;
		Text = "charlotte, NC" ;
		send_keys(myD,sPath,Text);
		
		
//		4	Click Search button	clickElement
		sPath = "//*[@id='searchSubmit']" ;
		clickElementAndWait(myD,sPath,10000);
		
//		5	Verify text Search Results	verify_text
		
		sPath = "/html/body/div/div/div[2]/div[5]/div[2]/div/h2" ;
		Text = get_text(myD,sPath);
		System.out.println(verify_text(myD,sPath,Text));
		
//		6	Verify value in search field	verify_attribute
		sPath = "//*[@id='searchTerms']" ;
		value = "value";
		vTechnology = "Selenium" ;
		System.out.println(verify_attribute(myD,sPath,value,vTechnology));
		
	}
//	navigate_to
	
	public static void navigate_to(WebDriver mD, String sURL){
		mD.navigate().to(sURL);
	}
	
//	send_keys
	
	public static void send_keys(WebDriver mD, String sxPath, String sText){
		mD.findElement(By.xpath(sxPath)).sendKeys(sText);
	}
	
	//	clickElement
	
	public static void clickElement(WebDriver mD, String sxPath){
		mD.findElement(By.xpath(sxPath)).click();
		
	}
	
	public static void clickElementAndWait(WebDriver mD, String sxPath, int sWait) throws Exception{
		mD.findElement(By.xpath(sxPath)).click();
		Thread.sleep(sWait);
		
	}

// get_text
	public static String get_text(WebDriver mD, String sxPath){
		String getText = mD.findElement(By.xpath(sxPath)).getText();
		return getText;
	}
	
	
//	verify_text
	public static String verify_text(WebDriver mD, String sxPath, String sText){
		String getText = mD.findElement(By.xpath(sxPath)).getText();
		System.out.println(getText);
		if(sText.equals(getText))
		{
			return "Pass" ;
		}else
		{
			return "fail" ;
		}
	
	}
//	verify_attribute
		public static String verify_attribute(WebDriver mD, String sxPath, String value, String vTechnology ){
			
			if(mD.findElement(By.xpath(sxPath)).getAttribute(value).equals(vTechnology))
			{
				return "Pass" ;
			}else
			{
				return "fail" ;
			}
		
		}
	
	
	
}
