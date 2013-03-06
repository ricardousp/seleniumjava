import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class WebDriver3 {
	
	public static String sXL = "C:/Selenium/Eclipse Code/SelWebDriver/Keyword_Driven_Automation1.xls" ;
	public static String img = "C:/Selenium/Eclipse Code/SelWebDriver/img.jpeg";
	public static String res_TC = "C:/Selenium/Eclipse Code/SelWebDriver/Result_TC.xls" ;
	public static String res_TS = "C:/Selenium/Eclipse Code/SelWebDriver/Result_TS.xls" ;
	public static String vIP1,vIP2,vIP3, vKeyword ,Text, TCID , TSID;
	public static String xDataTC[][], xDataTS[][];
	public static int Rows,Cols , TSRows, TSCols;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("hello");
	//	WebDriver myD = new InternetExplorerDriver() ;
		WebDriver myD = new FirefoxDriver() ;
		
		xlReadTC();
		xlReadTS();
		
		for(int k=1; k<Rows;k++)
		{
			if(xDataTC[k][3].equalsIgnoreCase("y"))
				{
					TCID = xDataTC[k][0];
					for(int n=1;n<TSRows;n++)
					{
						TSID = xDataTS[n][0];
						if(TSID.equals(TCID))
						{
							try
							{
								System.out.println(TCID);
								System.out.println(TSID);
								vKeyword = xDataTS[n][4];
								vIP1 = xDataTS[n][6];
								vIP2 = xDataTS[n][7];
								vIP3 = xDataTS[n][8];
								keyword_executor(myD);	
							}catch(NoSuchElementException e)
							{
								System.out.println("Error is " + e);
								File err = ((TakesScreenshot)myD).getScreenshotAs(OutputType.FILE);
								FileUtils.copyFile(err,new File(img));
							}
							
						}
					}
				}
		}
	}
	
//keyword_executor
	public static void keyword_executor(WebDriver mD) throws Exception{
		switch (vKeyword)
		{
		case "navigate_to":
			navigate_to(mD,vIP1);
			break;

		case "send_keys":
			send_keys(mD,vIP1,vIP2);
			break;
		case "clickElementAndWait":
			int swait = Integer.parseInt(vIP2);
			clickElementAndWait(mD,vIP1,swait);
			break;
		case "get_text":
			get_text(mD,vIP1);
			break;
		case "verify_text":
			verify_text(mD,vIP1,vIP2);
			break;
		case "verify_attribute":
			verify_attribute(mD,vIP1,vIP2,vIP3);
			break;
		case "clickElement":
			clickElement(mD,vIP1);
			break;
		case "close":
			mD.close();
			break;
		default:
			System.out.println("Keyword specified is not defined, please define the method first");
		}

	}
	
//Write to excel
	public static void xlWriteTC() throws Exception{
		File myTC_res = new File(res_TC);
		HSSFWorkbook mybook = new HSSFWorkbook();
		HSSFSheet mySheet = mybook.createSheet("Results");
		for(int i=0;i<Rows;i++)
		{
			HSSFRow rows = mySheet.createRow(i);
			for(int j =0;j<Cols;j++)
			{
				HSSFCell cell = rows.createCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(xDataTC[i][j]);
			}
			
			FileOutputStream Fout = new FileOutputStream(myTC_res);
			mybook.write(Fout);
			Fout.flush();
			Fout.close();
		}
		
	}
	
	
	public static void xlWriteTS() throws Exception{
		File myTC_res = new File(res_TS);
		HSSFWorkbook mybook = new HSSFWorkbook();
		HSSFSheet mySheet = mybook.createSheet("Results");
		for(int i=0;i<TSRows;i++)
		{
			HSSFRow rows = mySheet.createRow(i);
			for(int j =0;j<TSCols;j++)
			{
				HSSFCell cell = rows.createCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(xDataTS[i][j]);
			}
			
			FileOutputStream Fout = new FileOutputStream(myTC_res);
			mybook.write(Fout);
			Fout.flush();
			Fout.close();
		}
		
	}	
	
	
//Read from excel
	public static void xlReadTC() throws Exception{
		File myXl = new File(sXL);
		FileInputStream iStream = new FileInputStream(myXl);
		HSSFWorkbook mybook = new HSSFWorkbook(iStream);
		HSSFSheet mySheet = mybook.getSheet("TestCases");
		Rows = mySheet.getLastRowNum()+1 ;
		Cols = mySheet.getRow(0).getLastCellNum();
		xDataTC = new String[Rows][Cols];
		for (int i = 1; i<Rows ; i++){
			HSSFRow sRows = mySheet.getRow(i);
			for (int j = 0;j<Cols;j++)
			{
				HSSFCell cell = sRows.getCell(j);
				String value = cellToString(cell);
				xDataTC[i][j] = value;
			}
		}
	}
	
	public static void xlReadTS() throws Exception{
		File myXl = new File(sXL);
		FileInputStream iStream = new FileInputStream(myXl);
		HSSFWorkbook mybook = new HSSFWorkbook(iStream);
		HSSFSheet mySheet = mybook.getSheet("TestSteps");
		TSRows = mySheet.getLastRowNum()+1 ;
		TSCols = mySheet.getRow(0).getLastCellNum();
		xDataTS = new String[TSRows][TSCols];
		for (int i = 1; i<TSRows ; i++){
			HSSFRow sRows = mySheet.getRow(i);
			for (int j = 0;j<TSCols;j++)
			{
				HSSFCell cell = sRows.getCell(j);
				String value = cellToString(cell);
				xDataTS[i][j] = value;
			}
		}
	}
	
	public static String cellToString(HSSFCell cell) {
		// This function will convert an object of type excel cell to a string value
		    int type = cell.getCellType();
		    Object result;
		    switch (type) {
		       	case HSSFCell.CELL_TYPE_NUMERIC: //0
		            result = cell.getNumericCellValue();
		            break;
		        case HSSFCell.CELL_TYPE_STRING: //1
		            result = cell.getStringCellValue();
		            break;
		        case HSSFCell.CELL_TYPE_FORMULA: //2
		            throw new RuntimeException("We can't evaluate formulas in Java");
		        case HSSFCell.CELL_TYPE_BLANK: //3
		            result = "-";
		            break;
		        case HSSFCell.CELL_TYPE_BOOLEAN: //4
		            result = cell.getBooleanCellValue();
		            break;
		        case HSSFCell.CELL_TYPE_ERROR: //5
		            throw new RuntimeException ("This cell has an error");
		        default:
		            throw new RuntimeException("We don't support this cell type: " + type);
		    }
		    return result.toString();
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
		
		switch(sText)
		{
		case "-":
			Text = get_text(mD,vIP1);
			if(Text.equals(mD.findElement(By.xpath(sxPath)).getText()))
			{
				return(Text + "Exist") ;
			}else
			{
				return (Text + "does not exist") ;
			}
		default:
			if(sText.equals(mD.findElement(By.xpath(sxPath)).getText()))
			{
				return (sText + "Exist");
			}else
			{
				return (sText + "does not exist");
			}
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
