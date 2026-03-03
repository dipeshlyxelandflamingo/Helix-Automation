package Base;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public JavascriptExecutor js;
	public Actions act;
	public WebDriverWait wait;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		boolean isLinux = System.getProperty("os.name", "").toLowerCase().contains("linux");
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--lang=en-US");
		options.addArguments("--remote-allow-origins=*");
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

		if (isLinux) {
			options.addArguments("--headless=new");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1920,1080");
		}

		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);

		driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));

		if (!isLinux)
			driver.manage().window().maximize();

		wait = new WebDriverWait(driver, Duration.ofSeconds(isLinux ? 35 : 20));
		js = (JavascriptExecutor) driver;
		act = new Actions(driver);

		driver.get("https://helix-watches.com");

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		js = (JavascriptExecutor) driver;
		act = new Actions(driver);

	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (driver != null) {
			driver.quit();
		}
	}
}