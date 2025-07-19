package TestPkg;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandaloneCopy {

	public static void main(String[] args) {
		String prodname = "IPHONE 13 PRO";
		// WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		// Landingpage lp = new Landingpage(driver);
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("akk@test1.com");
		driver.findElement(By.id("userPassword")).sendKeys("Salu@0987");
		driver.findElement(By.name("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Automation Practice\"]")));
		System.out.println(driver.getTitle());
		System.out.println("Added new line here");

		List<WebElement> elements = driver.findElements(By.xpath("//button[@class='btn w-10 rounded']"));
		System.out.println(elements.size());
		for (WebElement webElement : elements) {
			webElement.click();
			Sleep(2500);
		}
		Sleep(2000);

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		Sleep(2000);

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(prodname));
		Assert.assertTrue(match);
		Sleep(300);

		JavascriptExecutor down = (JavascriptExecutor) driver;
		down.executeScript("window.scrollBy(0,500)");
		Sleep(1000);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		Sleep(1500);

		JavascriptExecutor up = (JavascriptExecutor) driver;
		up.executeScript("window.scrollBy(0,-200)");
		Sleep(1500);
		System.out.println("Scrolled");

		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//span[normalize-space(text())='India']")).click();
		Sleep(3000);

		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		Sleep(1500);
		String actualText = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
		Assert.assertEquals(actualText, "THANKYOU FOR THE ORDER.");
		Sleep(2000);
		System.out.println("HERE I ADDED NEW LINE FOR NEW PULL IN ANOTHER USER ");
		System.out.println("Have a great day");
		driver.quit();
	}

	public static void Sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
