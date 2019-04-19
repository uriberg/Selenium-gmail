package emailSelenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class FileUploadWindowsAutoIT {
	private WebDriver driver;
	private String baseUrl;

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		baseUrl = "https://www.gmail.com/";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\123\\Documents\\eclipse-workspace\\SeleniumWD2Tutorial\\src\\tutorialselenium\\chromedriver.exe");//chrome driver path
		driver = new ChromeDriver();;

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}
	
	@Test
	public void fileUpload() throws Exception {
		driver.findElement(By.id("identifierId")).sendKeys("<your email>");//your email account
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span")).click();
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("<your email password>");//your email password
		try {
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();
		}
		catch(org.openqa.selenium.WebDriverException ex){
			driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();
		}
		
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3 T-I-KL']")).click();//compose email
		
		Thread.sleep(2000);
		
		driver.findElement(By.name("to")).sendKeys("<recipients mail>");//Recipients
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("<email's subject>");//email's subject
		//Thread.sleep(5000);
		String cvLetter=StoreTextFile.readFileAsString();//cover letter
		//email body
		WebElement gmailBody=driver.findElement(By.xpath("/html[1]/body[1]/div[25]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[4]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/div[1]"));
		gmailBody.sendKeys(<your cv letter's path or body's text>);
		
		WebElement fileInput = driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']"));
		fileInput.click();//browse a file to upload
		Thread.sleep(2000);
		
		/*upload a file using Autoit code:
		 * ControlFocus("Open", "", "Edit1")
		   ControlSetText("Open", "", "Edit1", "<file path>")
		   ControlClick("Open", "", "Button1") 
		 */
		Runtime.getRuntime().exec("<AutoIt script path>");
		
		Thread.sleep(2000); 
		
		gmailBody.sendKeys(Keys.TAB);//move to the 'Send' button
		
		Thread.sleep(1000);
		driver.switchTo().activeElement().click();//current element is now the 'Send' button; clicking it for sending your email
	}
}



class StoreTextFile {
	
	public static String readFileAsString()throws Exception {
	// The name of the file to open.
    String fileName = "<your text file to store in the result string>";

    // This will reference one line at a time
    String line = null;
    String result=null;
    StringBuilder sb = new StringBuilder();
    
    

    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
        	//System.out.println(line);
            sb.append(line + "\n");
        }   

        // Always close files.
        bufferedReader.close();   
        result=sb.toString();
       
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }
    return result;
}
	
	public static void main(String[]args) throws Exception {
		String file=readFileAsString();
		System.out.println(file);
	}
	 

}
