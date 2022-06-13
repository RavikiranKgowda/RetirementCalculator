package retirementCalculator;

import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class calculator {
	WebDriver driver;
	
	
	//Initializing WebDriver and launching the Retirement calculator application
	@BeforeTest
	public void setUp() {
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/RAVI KIRAN/Desktop/Selenium Workspace/Demo/Drivers/chromedriver.exe");
		//Initializing the WebDriver
		driver = new ChromeDriver();
		//Launching the URL
		driver.get("https://www.securian.com/insights-tools/retirement-calculator.html");
		//Maximizing the window
		driver.manage().window().maximize();
		//Adding implicit wait for 3 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			
	}
	
	
	//This method is written to check the retirement calculator by filling only mandatory fields
	@Test
	public void testToCheckOnlyOnRequiredFields() throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		driver.findElement(By.xpath("//*[@id=\"current-age\"]")).sendKeys("40");
		driver.findElement(By.xpath("//*[@id=\"retirement-age\"]")).sendKeys("68");
		WebElement currentIncome = driver.findElement(By.xpath("//*[@id=\"current-income\"]"));
		WebElement spouseIncome =driver.findElement(By.xpath("//*[@id=\"spouse-income\"]"));
		WebElement savingsBalance =driver.findElement(By.xpath("//*[@id=\"current-total-savings\"]"));

		//Using Java Script Executer to send some values inside textBox when normal sendKeys doesn't work
		js.executeScript("arguments[0].value='100000'",currentIncome);
		js.executeScript("arguments[0].value='75000'",spouseIncome);
		js.executeScript("arguments[0].value='500000'",savingsBalance);

		driver.findElement(By.xpath("//*[@id=\"current-annual-savings\"]")).sendKeys("10");
		driver.findElement(By.xpath("//*[@id=\"savings-increase-rate\"]")).sendKeys("2");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement rad = driver.findElement(By.xpath("//*[@id=\"include-social-container\"]/ul/li[1]/label"));

		//used actions class to operate on radio button
		action.moveToElement(rad).click().build().perform();

		//Thread  should not be used more often unless on some testing purpose
		Thread.sleep(3000);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"marital-status-ul\"]/li[2]/label"))).click().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//button[contains(text(),'Calculate')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		String actual  = driver.findElement(By.xpath("//*[@id=\"calculator-results-container\"]/h3")).getAttribute("innerText");
		String Expecetd = "Results";

		//Asserting the actual and expected values and printing the successful message
		Assert.assertEquals(actual,Expecetd);
		System.out.println("Successfully submitted the form by filling all mandatory fields only");

	}
	
	
	//This method is written to check the retirement calculator by filling all mandatory fields along with Non-Mandatory fields
	@Test
	public void testToCheckByFillingAllFields() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);		
		driver.findElement(By.xpath("//*[@id=\"current-age\"]")).sendKeys("40");
		driver.findElement(By.xpath("//*[@id=\"retirement-age\"]")).sendKeys("68");
		WebElement currentIncome = driver.findElement(By.xpath("//*[@id=\"current-income\"]"));
		WebElement spouseIncome =driver.findElement(By.xpath("//*[@id=\"spouse-income\"]"));
		WebElement savingsBalance =driver.findElement(By.xpath("//*[@id=\"current-total-savings\"]"));
		
		//Using Java Script Executer to send some values inside textBox when normal sendKeys doesn't work
		js.executeScript("arguments[0].value='100000'",currentIncome);
		js.executeScript("arguments[0].value='75000'",spouseIncome);
		js.executeScript("arguments[0].value='500000'",savingsBalance);
		
		driver.findElement(By.xpath("//*[@id=\"current-annual-savings\"]")).sendKeys("10");
		driver.findElement(By.xpath("//*[@id=\"savings-increase-rate\"]")).sendKeys("2");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement rad = driver.findElement(By.xpath("//*[@id=\"include-social-container\"]/ul/li[1]/label"));
		
		//used actions class to operate on radio button
		action.moveToElement(rad).click().build().perform();
		
		//Thread  should not be used more often unless on some testing purpose
		Thread.sleep(3000);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"marital-status-ul\"]/li[2]/label"))).click().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement SSAmount =driver.findElement(By.xpath("//*[@id=\"spouse-income\"]"));
		js.executeScript("arguments[0].value='4000'",SSAmount);
		driver.findElement(By.xpath("//*[@id=\"retirement-form\"]/div[4]/div[1]/div/div/div/ul/li[2]/a")).click();
		Thread.sleep(3000);
		WebElement income =driver.findElement(By.xpath("//*[@id=\"additional-income\"]"));
		js.executeScript("arguments[0].value='500'",income);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//*[@id=\"retirement-duration\"]")).sendKeys("20");
		WebElement rad2 = driver.findElement(By.xpath("//*[@id=\"include-inflation-container\"]/ul/li[1]/label"));
		action.moveToElement(rad2).click().build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"expected-inflation-rate\"]")).sendKeys("1");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"retirement-annual-income\"]")).sendKeys("75");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"pre-retirement-roi\"]")).sendKeys("8");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"post-retirement-roi\"]")).sendKeys("5");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"default-values-form\"]/div[2]/div/div[1]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(),'Calculate')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		
		String actual  = driver.findElement(By.xpath("//*[@id=\"calculator-results-container\"]/h3")).getAttribute("innerText");
		String Expecetd = "Results";
		
		//Asserting the actual and expected values and printing the successful message
		Assert.assertEquals(actual,Expecetd);
		System.out.println("Successfully submitted the form by filling all mandatory and Non-Mandatory fileds fields");
		
		
		
		
	}
	
	
	//Closing the WebDriver
	@AfterTest
	public void tearDown() {
		
		driver.close();
		driver.quit();
		
	}
	

}

