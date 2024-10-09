package Perf_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Perf_package.Elements;
import Perf_package.RuntimeProperties;
import Perf_package.Selenium_Methods;
import Perf_package.Steps;

import java.util.List;

public class TestUI {

	public static void MethodUI(WebDriver driver, int iteration) throws InterruptedException {
		// load url
		driver.get("https://qa-ma.perf.infaqa.com/identity-service/home");

		// verify if username and password is available
		Selenium_Methods.isElementavailable(Elements.username, driver);
		Selenium_Methods.isElementavailable(Elements.wlogin, driver);
		WebElement wusername = driver.findElement(By.xpath(Elements.username));
		WebElement password = driver.findElement(By.xpath(Elements.password));

		Selenium_Methods.performanceTiming(driver, "landingpage", iteration, 1);

		WebElement loginButton = driver.findElement(By.xpath(Elements.wlogin));
		wusername.sendKeys("IICS_UI");
		password.sendKeys("infa@1234");
		loginButton.click();
		Selenium_Methods.isElementavailable(Elements.isAdminLoaded, driver);

		Selenium_Methods.performanceTiming(driver, "showProductsPage", iteration, 2);

		WebElement adminbutton = driver.findElement(By.xpath(Elements.isAdminLoaded));
		adminbutton.click();
		Selenium_Methods.isElementavailable(Elements.adminloadcheck, driver);

		Selenium_Methods.performanceTiming(driver, "Administrator", iteration, 3);

		Selenium_Methods.isElementavailable(Elements.logoutprofileButton, driver);
		WebElement logooutprifile = driver.findElement(By.xpath(Elements.logoutprofileButton));
		logooutprifile.click();
		Selenium_Methods.isElementavailable(Elements.logoutButton, driver);
		WebElement logoutbutton = driver.findElement(By.xpath(Elements.logoutButton));
		logoutbutton.click();
		Selenium_Methods.isElementavailable(Elements.wlogin, driver);

		Selenium_Methods.performanceTiming(driver, "logout", iteration, 4);

		Steps.finalcall(iteration);
	}

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\svali\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		for (int Count = 0; Count <= RuntimeProperties.TOTAL_ITERATIONS; Count++) {
			MethodUI(driver, Count);
		}
		driver.close();
	}
}
