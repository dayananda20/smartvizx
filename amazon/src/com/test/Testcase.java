package com.test;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testcase {

	WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;

	@BeforeTest()
	public void browser() throws Exception {

		report = new ExtentReports("D:\\selenium\\reports\\amazonorder.html");//
		test = report.startTest("Amazon Test");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Pictures\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		test.log(LogStatus.PASS, "Driver Started .....");
		//System.out.println("Driver Started .....");

		driver.get("https://www.amazon.in/");
		test.log(LogStatus.PASS, "opening the amazon link....");
		//System.out.println("opening the amazon link....");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test()
	public void testRun() throws Exception {

		driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-flyout-anchor']/div[@id='nav-flyout-accountList']/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
		// Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("8123722893");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		test.log(LogStatus.PASS, "Clicking on continue..");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("daya123gowda");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		test.log(LogStatus.PASS, "Loging Inn....");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Redmi Note");
		test.log(LogStatus.PASS, "writing Redmi note");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		test.log(LogStatus.PASS, "searching Redmi Note in search");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"p_89/Redmi\"]/span/a/span")).click();
		test.log(LogStatus.PASS, "selecting Redmin in brands");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[contains(text(),'32 GB')]")).click();
		test.log(LogStatus.PASS, "selecting 32GB");
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@type='checkbox']//following::i[5]")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[contains(text(),'Redmi Note 7S (3GB, 32GB, Sapphire Blue)')]")).click();
		test.log(LogStatus.PASS, "Redmi Note 7S (3GB, 32GB, Sapphire Blue)");
		Thread.sleep(2000);

		String parent = driver.getWindowHandle();

		Set<String> allwindow = driver.getWindowHandles();

		Iterator<String> iterator = allwindow.iterator();

		while (iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!parent.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
				test.log(LogStatus.PASS, "Adding to cart");
				Thread.sleep(2000);
				String message = driver.findElement(By.xpath("//*[@id=\"attachDisplayAddBaseAlert\"]/div/h4")).getText();
				String actual = "Added to cart";
				if (message.equalsIgnoreCase(actual)) {
					System.out.println("PASS---->>Added to cart");
					test.log(LogStatus.PASS, "Added to cart");
				} else {
					System.out.println("FAIL--->>not added to cart");
					test.log(LogStatus.FAIL, "not added to cart");
				}

			}
		}

	}

	@AfterTest()
	public static void endTest() {
		report.endTest(test);
		report.flush();
	}
}
